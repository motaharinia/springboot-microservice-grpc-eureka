package ir.micser.login.presentation.adminuser;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msjpautility.search.data.SearchDataModel;
import com.motaharinia.msjpautility.search.filter.SearchFilterModel;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import ir.micser.config.graphql.GraphQLCustomException;
import ir.micser.login.business.service.BusinessExceptionEnum;
import ir.micser.login.business.service.adminuser.AdminUserSearchViewTypeEnum;
import ir.micser.login.business.service.adminuser.AdminUserService;
import ir.micser.login.business.service.adminuser.AdminUserSearchViewTypeBrief;
import ir.micser.login.business.service.fso.FsoUploadedFileHandleActionEnum;
import ir.micser.login.business.service.fso.FsoUploadedFileService;
import ir.micser.login.business.service.fso.FsoModuleEnum;
import ir.micser.login.presentation.fso.fsouploadedhandle.FsoUploadedFileHandleDetailModel;
import ir.micser.login.presentation.fso.fsouploadedhandle.FsoUploadedFileHandleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس کنترلر ادمین
 */
@RestController
//@Component
@GraphQLApi
public class AdminUserController {

    private final AdminUserService adminUserService;

    private final FsoUploadedFileService fsoUploadedFileService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService, FsoUploadedFileService fsoUploadedFileService) {
        this.adminUserService = adminUserService;
        this.fsoUploadedFileService = fsoUploadedFileService;
    }

    /**
     * متد ثبت
     *
     * @param adminUserModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @GraphQLMutation(name = "create")
    //@PostMapping("/v1/adminUser")
    public AdminUserModel create(@RequestBody @Validated AdminUserModel adminUserModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException, Exception {
        adminUserModel=  adminUserService.create(adminUserModel);

        FsoUploadedFileHandleModel fsoUploadedFileHandleModel = new FsoUploadedFileHandleModel(adminUserModel.getId(), FsoUploadedFileHandleActionEnum.ENTITY_CREATE, adminUserModel.getImageFileList(), Arrays.asList(new FsoUploadedFileHandleDetailModel(FsoModuleEnum.ADMIN_USER_PROFILE_IMAGE, false, null, null)));
        fsoUploadedFileService.logUploadedFileHandle(fsoUploadedFileHandleModel);

        return adminUserModel;
    }


    /**
     * متد جستجوی با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل جستجو شده
     * @throws Exception خطا
     */
    @GraphQLQuery(name = "common_adminUser_readById")
    //@GetMapping("/v1/adminUser/{id}")
    public AdminUserModel readById(@PathVariable Integer id) throws Exception {
        if (id.equals(0)) {
            throw new GraphQLCustomException(BusinessExceptionEnum.ID_NOT_FOUND, "sample description");
        }
        AdminUserModel adminUserModel=adminUserService.readById(id);
        return adminUserModel;
    }

    /**
     * متد جستجو با رشته مدل فیلتر جستجو
     *
     * @param searchFilterModelJson رشته جیسون مدل فیلتر جستجو
     * @param searchViewTypeEnum    نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList       لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws UtilityException خطا
     */
    @GraphQLQuery(name = "readGrid")
    //@GetMapping("/v1/adminUser")
    public SearchDataModel readGrid(@RequestParam(name = "searchFilterModel") Optional<String> searchFilterModelJson, @RequestParam(name = "searchViewTypeEnum") AdminUserSearchViewTypeEnum searchViewTypeEnum, @RequestParam(name = "searchValueList") List<Object> searchValueList) throws JsonProcessingException, UtilityException, ClassNotFoundException {
        CustomObjectMapper customObjectMapper = new CustomObjectMapper();
        SearchFilterModel searchFilterModel = customObjectMapper.readValue(searchFilterModelJson.get(), SearchFilterModel.class);
        //تعیین اینترفیس ستونهای(فیلدهای خروجی) داده
        Class searchViewTypeInterface = AdminUserSearchViewTypeBrief.class;
        if (!ObjectUtils.isEmpty(searchViewTypeEnum)) {
            searchViewTypeInterface = Class.forName(searchViewTypeEnum.getValue());
        }
        //در صورت نال بودن باید new شود
        if (ObjectUtils.isEmpty(searchValueList)) {
            searchValueList = new ArrayList<>();
        }

        SearchDataModel searchDataModel = adminUserService.readGrid(searchFilterModel, searchViewTypeInterface, searchValueList);
        return searchDataModel;
    }

    /**
     * متد جستجو با مدل فیلتر جستجو
     *
     * @param searchFilterModel  مدل فیلتر جستجو
     * @param searchViewTypeEnum نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList    لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws JsonProcessingException
     * @throws UtilityException
     */
    @GraphQLQuery(name = "readGridByModel")
    public SearchDataModel readGridByModel(@Validated @RequestParam(name = "searchFilterModel") SearchFilterModel searchFilterModel,
                                           @RequestParam(name = "searchViewTypeEnum") AdminUserSearchViewTypeEnum searchViewTypeEnum,
                                           @RequestParam(name = "searchValueList", defaultValue = "", required = false) List<Object> searchValueList) throws JsonProcessingException, UtilityException, ClassNotFoundException {
        //تعیین اینترفیس ستونهای(فیلدهای خروجی) داده
        Class searchViewTypeInterface = AdminUserSearchViewTypeBrief.class;
        if (!ObjectUtils.isEmpty(searchViewTypeEnum)) {
            searchViewTypeInterface = Class.forName(searchViewTypeEnum.getValue());
        }
        //در صورت نال بودن باید new شود
        if (ObjectUtils.isEmpty(searchValueList)) {
            searchValueList = new ArrayList<>();
        }

        SearchDataModel searchDataModel = adminUserService.readGrid(searchFilterModel, searchViewTypeInterface, searchValueList);
        return searchDataModel;
    }

    /**
     * متد ویرایش
     *
     * @param adminUserModel مدل ویرایش
     * @return خروجی: مدل ویرایش شده
     */
    @GraphQLMutation(name = "update")
    //@PutMapping("/v1/adminUser")
    public AdminUserModel update(@RequestBody @Validated AdminUserModel adminUserModel) throws UtilityException, IllegalAccessException, BusinessException, InvocationTargetException, Exception {
        adminUserModel= adminUserService.update(adminUserModel);

        FsoUploadedFileHandleModel fsoUploadedFileHandleModel = new FsoUploadedFileHandleModel(adminUserModel.getId(), FsoUploadedFileHandleActionEnum.ENTITY_UPDATE, adminUserModel.getImageFileList(), Arrays.asList(new FsoUploadedFileHandleDetailModel(FsoModuleEnum.ADMIN_USER_PROFILE_IMAGE, false, null, null)));
        fsoUploadedFileService.logUploadedFileHandle(fsoUploadedFileHandleModel);

        return adminUserModel;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     */
    @GraphQLMutation(name = "delete")
    //@DeleteMapping("/v1/adminUser/{id}")
    public AdminUserModel delete(@PathVariable Integer id) throws Exception {
        return adminUserService.delete(id);
    }

    /**
     * این متد نام را از ورودی دریافت میکند ولیستی از شناسه های جستجو شده را برمی گرداند
     *
     * @param name نام
     * @return خروجی: لیستی از شناسه های جستجو شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @GraphQLQuery(name = "hchFindByName")
    //@GetMapping("/v1/adminUser/hchFindByName/{name}")
    public List<Integer> hchFindByName(@PathVariable String name) throws Exception {
        return adminUserService.hchFindByName(name);
    }

    /**
     * این متد شناسه جنسیت را از ورودی دریافت میکند ولیستی از شناسه های جستجو شده را برمی گرداند
     *
     * @param genderId شناسه جنسیت
     * @return خروجی: لیستی از شناسه های جستجو شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @GraphQLQuery(name = "hchFindByGender")
    //@GetMapping("/v1/adminUser/hchFindByGender/{genderId}")
    public List<Integer> hchFindByGender(@PathVariable Integer genderId) throws Exception {
        return adminUserService.hchFindByGender(genderId);
    }

    /**
     * این متد عنوان مهارت را از ورودی دریافت میکند ولیستی از شناسه های جستجو شده را برمی گرداند
     *
     * @param skillTitle عنوان مهارت
     * @return خروجی: لیستی از شناسه های جستجو شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @GraphQLQuery(name = "hchFindBySkill")
    //@GetMapping("/v1/adminUser/hchFindBySkill/{skillTitle}")
    public List<Integer> hchFindBySkill(@PathVariable String skillTitle) throws Exception {
        return adminUserService.hchFindBySkill(skillTitle);
    }



}
