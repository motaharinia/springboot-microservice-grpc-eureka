package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.required.Required;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل بررسی است<br>
 */
public class CheckModel {

    /**
     * شناسه سازمان
     */
    private Integer organizationId;

    /**
     * پارامتر
     */
    @Required
    private String param;

    @Override
    public String toString() {
        return "CheckModel{" + "idList=" + getOrganizationId() + ", param=" + getParam() + '}';
    }

    //getter-setter:
    public Integer getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Integer organizationId) {
        this.organizationId = organizationId;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}
