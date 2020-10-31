package com.motaharinia.msjpautility.search.data;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:14:13<br>
 * Description:<br>
 *     اینام مقادیر ثابت نوع جستجو مطالب
 */
public enum SearchDataColSearchTypeEnum {
    /**
     *متنی
     */
    TEXT("TEXT"),
    /**
     *انتخابی
     */
    SELECT("SELECT")
    ;

    //نوع جستجو مطالب
    private String value;

    /**
     * مقدار نوع جستجو مطالب را خروجی میدهد
     *
     * @return خروجی: مقدارنوع جستجو مطالب
     */
    public String getValue() {
        return this.value;
    }

    /**
     * تابع سازنده ثابت نوع جستجو مطالب از روی مقدار نوع جستجو مطالب آن
     *
     * @param value مقدار ثابت نوع جستجو مطالب
     */
    SearchDataColSearchTypeEnum(String value) {
        this.value = value;
    }

}
