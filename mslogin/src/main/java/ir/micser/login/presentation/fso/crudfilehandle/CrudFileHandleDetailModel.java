package ir.micser.login.presentation.fso.crudfilehandle;

import ir.micser.login.business.service.fso.FsoModuleEnum;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:کلاس مدل شرح فایلها در کراد ماژولها
 */
public class CrudFileHandleDetailModel {

    /**
     * مقدار ثابت فایل که شامل آدرس محل قرارگیری فایل میباشد
     */
    private FsoModuleEnum fsoModuleEnum;
    /**
     * آیا فایل تکی است؟
     */
    private Boolean isSingle;
    /**
     * اگر فایل تصویر است، عرض تصویر بندانگشتی
     */
    private Integer width;
    /**
     * اگر فایل تصویر است، طول تصویر بندانگشتی
     */
    private Integer height;

    //getter-setter:
    public FsoModuleEnum getFsoModuleEnum() {
        return fsoModuleEnum;
    }

    public void setFsoModuleEnum(FsoModuleEnum fsoModuleEnum) {
        this.fsoModuleEnum = fsoModuleEnum;
    }

    public Boolean getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Boolean isSingle) {
        this.isSingle = isSingle;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public CrudFileHandleDetailModel() {
    }

    public CrudFileHandleDetailModel(FsoModuleEnum fsoModuleEnum, Boolean isSingle, Integer width, Integer height) {
        this.fsoModuleEnum = fsoModuleEnum;
        this.isSingle = isSingle;
        this.width = width;
        this.height = height;
    }

}
