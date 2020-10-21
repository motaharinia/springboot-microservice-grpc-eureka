package com.motaharinia.msutility.calendar;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     مقادیر ثابت واحدهای زمانی
 */
public enum DateTimeUnitEnum {

    /**
     * واحد زمانی سال
     */
    YEAR("YEAR"),
    /**
     * واحد زمانی ماه
     */
    MONTH("MONTH"),
    /**
     * واحد زمانی روز
     */
    DAY("DAY"),
    /**
     * واحد زمانی ساعت
     */
    HOUR("HOUR"),
    /**
     * واحد زمانی دقیقه
     */
    MINUTE("MINUTE"),
    /**
     * واحد زمانی ثانیه
     */
    SECOND("SECOND"),
    /**
     * واحد زمانی میلی ثانیه
     */
    MILLISECOND("MILLISECOND"),
    ;
    private final String value;

    DateTimeUnitEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return super.toString();
    }
}
