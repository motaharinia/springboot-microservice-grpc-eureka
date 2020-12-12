package ir.micser.login.presentation.adminuser;


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
import ir.micser.login.business.service.fso.CrudFileHandleActionEnum;
import ir.micser.login.business.service.fso.FsoService;
import ir.micser.login.business.service.fso.FsoModuleEnum;
import ir.micser.login.presentation.fso.crudfilehandle.CrudFileHandleDetailModel;
import ir.micser.login.presentation.fso.crudfilehandle.CrudFileHandleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final FsoService fsoService;

    @Autowired
    public AdminUserController(AdminUserService adminUserService, FsoService fsoService) {
        this.adminUserService = adminUserService;
        this.fsoService = fsoService;
    }

    /**
     * متد ثبت
     *
     * @param adminUserModel مدل ثبت
     * @return خروجی: مدل ثبت حاوی شناسه
     */
    @GraphQLMutation(name = "create")
    @PostMapping("/v1/adminUser")
    public AdminUserModel create(@RequestBody @Validated AdminUserModel adminUserModel) throws Exception {
        adminUserModel = adminUserService.create(adminUserModel);

        //ثبت فایلها بعد از اطمینان از ثبت انتیتی در دیتابیس
        CrudFileHandleModel crudFileHandleModel = new CrudFileHandleModel(adminUserModel.getId(), CrudFileHandleActionEnum.ENTITY_CREATE, adminUserModel.getImageFileList(), Arrays.asList(new CrudFileHandleDetailModel(FsoModuleEnum.ADMIN_USER_PROFILE_IMAGE, false, null, null)));
        fsoService.crudHandle(crudFileHandleModel);

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
    @GetMapping("/v1/adminUser/{id}")
    public AdminUserModel readById(@PathVariable Integer id) throws Exception {
        if (id.equals(0)) {
            throw new GraphQLCustomException(BusinessExceptionEnum.ID_NOT_FOUND, "sample description");
        }
        AdminUserModel adminUserModel = adminUserService.readById(id);
        return adminUserModel;
    }

    /**
     * متد جستجوی با کلمه کاربری برای تست مبدل اطلاعات بانک
     *
     * @param username کلمه کاربری
     * @return خروجی: مدل حاوی جنسیت تغییر داده شده مطابق با شرایط بانک
     */
    @GetMapping("/v1/adminUser/readBriefByUsername/{username}")
    public AdminUserModel readBriefByUsername(@PathVariable String username) {
        return adminUserService.readBriefByUsername(username);
    }

    /**
     * متد جستجو با رشته مدل فیلتر جستجو
     *
     * @param searchFilterModelJson رشته جیسون مدل فیلتر جستجو
     * @param searchViewTypeEnum    نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param searchValueList       لیست مقادیر مورد نیاز جهت جستجو
     * @return خروجی: مدل داده جستجو
     * @throws Exception خطا
     */
    @GraphQLQuery(name = "readGrid")
    @GetMapping("/v1/adminUser")
    public SearchDataModel readGrid(@RequestParam(name = "searchFilterModel") Optional<String> searchFilterModelJson, @RequestParam(name = "searchViewTypeEnum") AdminUserSearchViewTypeEnum searchViewTypeEnum, @RequestParam(name = "searchValueList") List<Object> searchValueList) throws Exception {
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
     * @throws Exception خطا
     */
    @GraphQLQuery(name = "readGridByModel")
    public SearchDataModel readGridByModel(@Validated @RequestParam(name = "searchFilterModel") SearchFilterModel searchFilterModel,
                                           @RequestParam(name = "searchViewTypeEnum") AdminUserSearchViewTypeEnum searchViewTypeEnum,
                                           @RequestParam(name = "searchValueList", defaultValue = "", required = false) List<Object> searchValueList) throws Exception {
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
     * @throws Exception خطا
     */
    @GraphQLMutation(name = "update")
    @PutMapping("/v1/adminUser")
    public AdminUserModel update(@RequestBody @Validated AdminUserModel adminUserModel) throws Exception {
        adminUserModel = adminUserService.update(adminUserModel);

        //ویرایش فایلها بعد از اطمینان از ویرایش انتیتی در دیتابیس
        CrudFileHandleModel crudFileHandleModel = new CrudFileHandleModel(adminUserModel.getId(), CrudFileHandleActionEnum.ENTITY_UPDATE, adminUserModel.getImageFileList(), Arrays.asList(new CrudFileHandleDetailModel(FsoModuleEnum.ADMIN_USER_PROFILE_IMAGE, false, null, null)));
        fsoService.crudHandle(crudFileHandleModel);

        return adminUserModel;
    }

    /**
     * متد حذف با شناسه
     *
     * @param id شناسه
     * @return خروجی: مدل حذف شده
     * @throws Exception خطا
     */
    @GraphQLMutation(name = "delete")
    @DeleteMapping("/v1/adminUser/{id}")
    public AdminUserModel delete(@PathVariable Integer id) throws Exception {
        AdminUserModel adminUserModel = adminUserService.delete(id);

        //حذف فایلها بعد از اطمینان از حذف انتیتی در دیتابیس
        CrudFileHandleModel crudFileHandleModel = new CrudFileHandleModel(adminUserModel.getId(), CrudFileHandleActionEnum.ENTITY_DELETE, adminUserModel.getImageFileList(), Arrays.asList(new CrudFileHandleDetailModel(FsoModuleEnum.ADMIN_USER_PROFILE_IMAGE, false, null, null)));
        fsoService.crudHandle(crudFileHandleModel);

        return adminUserModel;
    }

    /**
     * این متد نام را از ورودی دریافت میکند ولیستی از شناسه های جستجو شده را برمی گرداند
     *
     * @param name نام
     * @return خروجی: لیستی از شناسه های جستجو شده
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @GraphQLQuery(name = "hchFindByName")
    @GetMapping("/v1/adminUser/hchFindByName/{name}")
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
    @GetMapping("/v1/adminUser/hchFindByGender/{genderId}")
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
    @GetMapping("/v1/adminUser/hchFindBySkill/{skillTitle}")
    public List<Integer> hchFindBySkill(@PathVariable String skillTitle) throws Exception {
        return adminUserService.hchFindBySkill(skillTitle);
    }


}
