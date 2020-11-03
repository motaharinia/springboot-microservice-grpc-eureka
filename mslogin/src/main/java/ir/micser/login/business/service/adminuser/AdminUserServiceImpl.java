package ir.micser.login.business.service.adminuser;



import com.motaharinia.msutility.calendar.CalendarTools;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.filter.SearchFilterModel;
import ir.micser.config.graphql.GraphQLCustomException;
import ir.micser.geo.business.service.city.stub.CityGrpc.*;
import ir.micser.geo.business.service.city.stub.CityMicro.*;
import ir.micser.geo.business.service.cityplace.stub.CityPlaceGrpc.*;
import ir.micser.geo.business.service.cityplace.stub.CityPlaceMicro.*;
import ir.micser.login.business.service.BusinessExceptionEnum;
import ir.micser.login.business.service.adminuserskill.AdminUserSkillService;
import ir.micser.login.business.service.authentication.PasswordEncoderGenerator;
import ir.micser.login.business.service.etcitem.EtcItemService;
import ir.micser.login.business.service.etcitem.GenderEnum;
import ir.micser.login.business.service.fso.FsoService;
import ir.micser.login.business.service.hibernatesearch.HibernateSearchService;
import ir.micser.login.business.service.fso.FsoModuleEnum;
import ir.micser.login.persistence.orm.adminuser.AdminUser;
import ir.micser.login.persistence.orm.adminuser.AdminUserRepository;
import ir.micser.login.persistence.orm.adminuser.AdminUserSpecification;
import ir.micser.login.persistence.orm.adminusercontact.AdminUserContact;
import ir.micser.login.persistence.orm.adminusercontact.AdminUserContactRepository;
import ir.micser.login.presentation.adminuser.AdminUserModel;
import ir.micser.login.presentation.adminuserskill.AdminUserSkillModel;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.lucene.search.Query;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس پیاده سازی اینترفیس مدیریت ادمین ها
 */
@Service
@Transactional(rollbackFor=Exception.class)
public class AdminUserServiceImpl implements AdminUserService {


    /**
     * ریپازیتوری ادمین
     */
    private AdminUserRepository adminUserRepository;
    /**
     * ریپازیتوری اطلاعات تماس ادمین
     */
    private AdminUserContactRepository adminUserContactRepository;

    /**
     * سرویس مهارتها
     */
    private AdminUserSkillService adminUserSkillService;

    /**
     * سرویس مقادیر ثابت
     */
    private EtcItemService etcItemService;

    /**
     * مشخصات جستجوی ادمین
     */
    private AdminUserSpecification adminUserSpecification;

    /**
     * سرویس Hibernate search
     */
    private HibernateSearchService hibernateSearchService;

    /**
     * سرویس مدیریت فایل
     */
    private FsoService fsoService;

    @GrpcClient("grpcClientCity")
    private CityBlockingStub cityStub;

    @GrpcClient("grpcClientCityPlace")
    private CityPlaceBlockingStub cityPlaceStub;

    /**
     * متد سازنده پیش فرض
     */
    public AdminUserServiceImpl() {
    }

    /**
     * متد سازنده
     */
    @Autowired
    public AdminUserServiceImpl(AdminUserRepository adminUserRepository, AdminUserContactRepository adminUserContactRepository, AdminUserSkillService adminUserSkillService,EtcItemService etcItemService, AdminUserSpecification adminUserSpecification,HibernateSearchService hibernateSearchService,FsoService fsoService) {
        this.adminUserRepository = adminUserRepository;
        this.adminUserContactRepository = adminUserContactRepository;
        this.adminUserSkillService = adminUserSkillService;
        this.etcItemService=etcItemService;
        this.adminUserSpecification = adminUserSpecification;
        this.hibernateSearchService=hibernateSearchService;
        this.fsoService=fsoService;
    }

    /**
     * متد ثبت
     *
     * @param adminUserModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @Override
    @NotNull
    public AdminUserModel create(@NotNull AdminUserModel adminUserModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException,Exception {

        if(adminUserModel.getUsername().equalsIgnoreCase("eng.motahari@gmail.com")){
            throw new GraphQLCustomException(BusinessExceptionEnum.DUPLICATE_EMAIL, "sample description");
        }

        //بررسی شناسه شهر
        ReadByIdRequestModel readByIdRequestModel =  ReadByIdRequestModel.newBuilder().setId(adminUserModel.getDefaultAdminUserContact_city_id()).build();
        final ReadByIdResponseModel cityReadOneResponse= this.cityStub.grpcReadById(readByIdRequestModel);

        //ثبت اطلاعات تماس ادمین
        AdminUserContact adminUserContact = new AdminUserContact();
        adminUserContact.setCityId(cityReadOneResponse.getId());
        adminUserContact.setAddress(adminUserModel.getDefaultAdminUserContact_address());
        adminUserContact = adminUserContactRepository.save(adminUserContact);

        //ثبت ادمین
        AdminUser adminUser = new AdminUser();
        adminUser.setFirstName(adminUserModel.getFirstName());
        adminUser.setLastName(adminUserModel.getLastName());
        adminUser.setPassword(PasswordEncoderGenerator.generate(adminUserModel.getPassword()));
        adminUser.setUsername(adminUserModel.getUsername());
        if (!ObjectUtils.isEmpty(adminUserModel.getDateOfBirth())) {
            adminUser.setDateOfBirth(CalendarTools.getDateFromCustomDate(adminUserModel.getDateOfBirth()));
        }
        adminUser.setGender(etcItemService.findByIdAndCheckEntity(adminUserModel.getGender_id(), GenderEnum.class,null,true));
        //ثبت مهارتهای ادمین
        adminUser = adminUserSkillService.createByAdminUser(adminUser, adminUserModel.getSkillList());


        adminUser = adminUserRepository.save(adminUser);
        adminUser.setDefaultAdminUserContact(adminUserContact);


        //اضافه کردن لوکیشن شهری برای ادمین
        CreateRequestModel createRequestModel =  CreateRequestModel.newBuilder()
                .setAdminUserId(adminUser.getId())
                .setCityId(cityReadOneResponse.getId())
                .setLatitude("35.791354")
                .setLongitude("51.356406")
                .setTitle("OfficePlace")
                .build();
        final CreateResponseModel createResponseModel= this.cityPlaceStub.grpcCreate(createRequestModel);
        System.out.println("AdminUserServiceImpl.create cityPlace.getId:" + createResponseModel.getId());

        //create state and stateDetail in new transaction

        adminUserModel.setId(adminUser.getId());
        return adminUserModel;
    }

    /**
     * متد ویرایش
     *
     * @param adminUserModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @CacheEvict(value = "AdminUser", key = "#adminUserModel.id")
    @Override
    @NotNull
    public AdminUserModel update(@NotNull AdminUserModel adminUserModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException,Exception {
        AdminUser adminUser = adminUserRepository.findById(adminUserModel.getId()).get();
        adminUser.setFirstName(adminUserModel.getFirstName());
        adminUser.setLastName(adminUserModel.getLastName());
        adminUser.setPassword(PasswordEncoderGenerator.generate(adminUserModel.getPassword()));
        adminUser.setUsername(adminUserModel.getUsername());
        if (!ObjectUtils.isEmpty(adminUserModel.getDateOfBirth())) {
            adminUser.setDateOfBirth(CalendarTools.getDateFromCustomDate(adminUserModel.getDateOfBirth()));
        }
        adminUser.setGender(etcItemService.findByIdAndCheckEntity(adminUserModel.getGender_id(), GenderEnum.class,null,true));
        if (ObjectUtils.isEmpty(adminUser.getDefaultAdminUserContact())) {
            AdminUserContact adminUserContact = new AdminUserContact();
            adminUserContact.setAddress(adminUserModel.getDefaultAdminUserContact_address());
            adminUserContact = adminUserContactRepository.save(adminUserContact);
            adminUser.setDefaultAdminUserContact(adminUserContact);
        } else {
            adminUser.getDefaultAdminUserContact().setAddress(adminUserModel.getDefaultAdminUserContact_address());
        }

        //ویرایش مهارتهای ادمین
        adminUser = adminUserSkillService.updateByAdminUser(adminUser, adminUserModel.getSkillList());
        adminUserRepository.save(adminUser);
        return adminUserModel;
    }

    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     */
    @Cacheable(value = "AdminUser", key = "#id")
    @Override
    @NotNull
    public AdminUserModel readById(@NotNull Integer id) throws Exception {
        AdminUser adminUser = adminUserRepository.findById(id).get();
        AdminUserModel adminUserModel = new AdminUserModel();
        adminUserModel.setId(adminUser.getId());
        adminUserModel.setUsername(adminUser.getUsername());
        adminUserModel.setFirstName(adminUser.getFirstName());
        adminUserModel.setLastName(adminUser.getLastName());
        adminUserModel.setPassword(adminUser.getPassword());
        adminUserModel.setGender_id(adminUser.getGender().getId());
        if (!ObjectUtils.isEmpty(adminUser.getDateOfBirth())) {
            adminUserModel.setDateOfBirth(new CustomDate(adminUser.getDateOfBirth()));
        }
        if (!ObjectUtils.isEmpty(adminUser.getDefaultAdminUserContact())) {
            adminUserModel.setDefaultAdminUserContact_address(adminUser.getDefaultAdminUserContact().getAddress());
        }
        if (!ObjectUtils.isEmpty(adminUser.getSkillSet())) {
            adminUser.getSkillSet().stream().forEach(item -> {
                adminUserModel.getSkillList().add(new AdminUserSkillModel(item.getId(), item.getTitle()));
            });
        }
        adminUserModel.setImageFileList(fsoService.getFileViewModelList(FsoModuleEnum.ADMIN_USER_PROFILE_IMAGE, adminUserModel.getId()));
        return adminUserModel;
    }

    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModel        مدل فیلتر جستجو
     * @param searchViewTypeInterface        کلاس اینترفیس نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws UtilityException
     */
    @Override
    @NotNull
    public SearchDataModel readGrid(@NotNull SearchFilterModel searchFilterModel, @NotNull Class searchViewTypeInterface, @NotNull List<Object> searchValueList) throws UtilityException {
        //تعریف فیلترهای جستجو
        adminUserSpecification = (AdminUserSpecification) searchFilterModel.makeSpecification(new AdminUserSpecification());
        //جستجو بر طبق فیلترهای جستجو و کلاس اینترفیس نوع نمایش و صفحه بندی دلخواه کلاینت ساید
        Page<AdminUserSearchViewTypeBrief> viewPage = adminUserRepository.findAll(adminUserSpecification, searchViewTypeInterface, searchFilterModel.makePageable());
        //تعریف خروجی بر اساس جستجو
        SearchDataModel searchDataModel = new SearchDataModel(viewPage, searchFilterModel, searchViewTypeInterface, "");
        return searchDataModel;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @Override
    @NotNull
    @CacheEvict(value = "AdminUser", key = "#id")
    public AdminUserModel delete(@NotNull Integer id) throws Exception {
        AdminUserModel adminUserModel = this.readById(id);
        AdminUser adminUser = adminUserRepository.findById(adminUserModel.getId()).get();
        //حذف اطلاعات تماس ادمین
        adminUserContactRepository.deleteById(adminUser.getDefaultAdminUserContact().getId());
        //حذف مهارتهای ادمین
        adminUser = adminUserSkillService.deleteByAdminUser(adminUser);
        adminUserRepository.save(adminUser);
        adminUserRepository.delete(adminUser);
        return adminUserModel;
    }

    /**
     *  این متد نام را از ورودی دریافت میکند ولیستی از شناسه های جستجو شده را برمی گرداند
     * @param name نام
     * @return خروجی: لیستی از شناسه های جستجو شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @Override
    public List<Integer> hchFindByName(String name) throws Exception {
        QueryBuilder queryBuilder = hibernateSearchService.getQueryBuilder(AdminUser.class);
        Query query = queryBuilder.keyword().onField("firstName").matching(name).createQuery();
        List<Integer> adminUserIdList = hibernateSearchService.indexIdListBy(AdminUser.class,query,"firstName DESC");
        return adminUserIdList;
    }

    /**
     * این متد شناسه جنسیت را از ورودی دریافت میکند ولیستی از شناسه های جستجو شده را برمی گرداند
     * @param genderId شناسه جنسیت
     * @return خروجی: لیستی از شناسه های جستجو شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @Override
    public List<Integer> hchFindByGender(Integer genderId) throws Exception {

        QueryBuilder queryBuilder = hibernateSearchService.getQueryBuilder(AdminUser.class);
        Query query = queryBuilder.keyword().onField("gender.id").matching(genderId).createQuery();
        List<Integer> adminUserIdList = hibernateSearchService.indexIdListBy(AdminUser.class,query,"");

        return adminUserIdList;
    }
 @Override
    public List<Integer> hchFindBySkill(String skillTitle) throws Exception {

        QueryBuilder queryBuilder = hibernateSearchService.getQueryBuilder(AdminUser.class);
        Query query = queryBuilder.keyword().onField("skillSet.title").matching(skillTitle).createQuery();
        List<Integer> adminUserIdList = hibernateSearchService.indexIdListBy(AdminUser.class,query,"");

        return adminUserIdList;
    }

    @Override
    public Long hchCount() throws Exception {

        QueryBuilder queryBuilder = hibernateSearchService.getQueryBuilder(AdminUser.class);
        Query query = queryBuilder.all().createQuery();
        List<Integer> adminUserIdList = hibernateSearchService.indexIdListBy(AdminUser.class,query,"");
        if(ObjectUtils.isEmpty(adminUserIdList)){
            return 0l;
        }else  return (long) adminUserIdList.size();
    }

    @Override
    public Long count(){
        return adminUserRepository.count();
    }


}
