package com.motaharinia.msutility.fso.mimetype;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     مقادیر ثابت نوع فایل
 */
public enum FsoMimeTypeEnum {

    /**
     *فایلهای عمومی
     */
    GENERAL("GENERAL"),
    /**
     *فایلهای آفیس
     */
    APPLICATION("APPLICATION"),
    /**
     *فایلهای تصویری
     */
    IMAGE("IMAGE");

    private final String value;

    FsoMimeTypeEnum(String value) {
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
