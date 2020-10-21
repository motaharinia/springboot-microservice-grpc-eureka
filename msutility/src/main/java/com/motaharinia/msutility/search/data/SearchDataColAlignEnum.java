package com.motaharinia.msutility.search.data;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:14:13<br>
 * Description:<br>
 *     اینام مقادیر ثابت جهت نمایش افقی مطالب
 */
public enum SearchDataColAlignEnum {
    /**
     *چپ چین
     */
    LEFT("LEFT"),
    /**
     *راست چین
     */
    RIGHT("RIGHT"),
    /**
     *وسط چین
     */
    CENTER("CENTER")
    ;

    //جهت نمایش افقی مطالب
    private String value;

    /**
     * مقدار جهت نمایش افقی مطالب را خروجی میدهد
     *
     * @return خروجی: مقدارجهت نمایش افقی مطالب
     */
    public String getValue() {
        return this.value;
    }

    /**
     * تابع سازنده ثابت جهت نمایش افقی مطالب از روی مقدار جهت نمایش افقی مطالب آن
     *
     * @param value مقدار ثابت جهت نمایش افقی مطالب
     */
    SearchDataColAlignEnum(String value) {
        this.value = value;
    }

}
