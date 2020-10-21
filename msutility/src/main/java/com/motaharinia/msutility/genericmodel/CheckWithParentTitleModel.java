package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.required.Required;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل بررسی به همراه عنوان والد است<br>
 */
public class CheckWithParentTitleModel extends GeneralCheckModel {
    /**
     * عنوان والد
     */
    @Required
    private String parentTitle;


    //getter-setter:
    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

}
