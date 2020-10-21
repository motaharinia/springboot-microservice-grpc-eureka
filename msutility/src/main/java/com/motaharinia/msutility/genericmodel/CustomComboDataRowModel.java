package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.required.Required;

import java.util.HashMap;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس سطر کاستوم کامبو در کلاینت است<br>
 */
public class CustomComboDataRowModel {

    /**
     * شناسه
     */
    @Required
    private String value;
    /**
     * عنوان
     */
    private String caption;
    /**
     * توضیحات
     */
    private String elementStr;
    /**
     * اطلاعات اضافه
     */
    private HashMap<String, Object> extMap = new HashMap<>();

    /**
     * متد سازنده پیش فرض
     */
    public CustomComboDataRowModel() {
    }

    /**
     * متد سازنده ای که با دریافت مشخصات ورودی مدل را میسازد
     *
     * @param value      شناسه
     * @param caption    عنوان
     * @param elementStr اطلاعات اضافه
     */
    public CustomComboDataRowModel(String value, String caption, String elementStr) {
        this.value = value;
        this.caption = caption;
        this.elementStr = elementStr;
    }

    /**
     * متد سازنده ای که با دریافت مشخصات ورودی مدل را میسازد
     *
     * @param value   شناسه
     * @param caption عنوان
     */
    public CustomComboDataRowModel(String value, String caption) {
        this.value = value;
        this.caption = caption;
    }

    //getter-setter:
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getElementStr() {
        return elementStr;
    }

    public void setElementStr(String elementStr) {
        this.elementStr = elementStr;
    }

    public HashMap<String, Object> getExtMap() {
        return extMap;
    }

    public void setExtMap(HashMap<String, Object> extMap) {
        this.extMap = extMap;
    }


}
