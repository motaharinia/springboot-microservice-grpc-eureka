package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.required.Required;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس جستجو توسط مدل در کلاینت است<br>
 */
public class FindByModel {

    /**
     * پارامتر
     */
    @Required
    private String param;

    /**
     * مقدار شرط
     */
    @Required
    private Object conditionValue;

    //getter-setter:
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public Object getConditionValue() {
        return conditionValue;
    }

    public void setConditionValue(Object conditionValue) {
        this.conditionValue = conditionValue;
    }

}
