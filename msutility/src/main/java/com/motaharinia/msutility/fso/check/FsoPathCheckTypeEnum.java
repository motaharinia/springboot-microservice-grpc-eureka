package com.motaharinia.msutility.fso.check;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     مقادیر ثابت نوع مسیر فایل یا دایرکتوری
 */
public enum FsoPathCheckTypeEnum {

    /**
     * مسیر داده شده یک فایل است
     */
    FILE("FILE"),
    /**
     * مسیر داده شده یک دایرکتوری است
     */
    DIRECTORY("DIRECTORY");

    private final String value;

    private FsoPathCheckTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
