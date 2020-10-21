package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.required.Required;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل بررسی به همراه شناسه والد به صورت الزامی است<br>
 */
public class CheckWithParentIdRequiredModel extends GeneralCheckModel {

    /**
     * شناسه والد
     */
    @Required
    private Integer parentId;

    /**
     * اگر شاخه کالایی بدون والد باشد در چک کردن نام باید شناسه ماهیت به عنوان والد ارسال شود.
     */
    private Boolean withoutParentProductCategory = Boolean.FALSE;

    //getter-setter:
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Boolean getWithoutParentProductCategory() {
        return withoutParentProductCategory;
    }

    public void setWithoutParentProductCategory(Boolean withoutParentProductCategory) {
        this.withoutParentProductCategory = withoutParentProductCategory;
    }
}
