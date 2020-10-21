package com.motaharinia.msutility.fso.view;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:43:42<br>
 * Description:<br>
 *     مقادیر ثابت وضعیت فایل
 */
public enum FileViewModelStatusEnum {

    /**
     * فایل جدید آپلود شده است
     */
    ADDED("ADDED"),
    /**
     * فایل حذف شده است
     */
    DELETED("DELETED"),
    /**
     * فایل از قبل وجود داشته و بدون تغییر مانده است
     */
    EXISTED("EXISTED");

    private final String value;

    private FileViewModelStatusEnum(String value) {
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
