package com.motaharinia.msutility.customexception;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     مقادیر ثابت کلید اکسپشنهای یوتیلیتی که در کلاینت ساید پروژه ترجمه آنها طبق زبان انتخاب شده کاربر نمایش داده خواهد شد
 */
public enum UtilityExceptionKeyEnum implements  CustomExceptionKey{

    /**
     * یکی از ورودی های متد مقدار null و یا خالی دارد
     */
    METHOD_PARAMETER_IS_NULL_OR_EMPTY("METHOD_PARAMETER_IS_NULL_OR_EMPTY"),
    /**
     * یکی از ورودی های متد مقدار صفر و یا منفی دارد
     */
    METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE("METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE"),
    /**
     * فرمت تاریخ رشته ورودی صحیح نمیباشد
     */
    INCORRECT_STRING_DATE_FORMAT("INCORRECT_STRING_DATE_FORMAT"),
    /**
     *  تاریخ معتبر نمیباشد
     */
    DATE_VALIDATION_FAILED("DATE_VALIDATION_FAILED"),
    /**
     *  تاریخ-زمان معتبر نمیباشد
     */
    DATE_TIME_VALIDATION_FAILED("DATE_TIME_VALIDATION_FAILED"),
    /**
     * مقدار انتیتی تهی میباشد
     */
    CHECK_ENTITY_IS_NULL("CHECK_ENTITY_IS_NULL"),
    /**
     *  انتیتی غیرفعال میباشد
     */
    CHECK_ENTITY_IS_INVALID("CHECK_ENTITY_IS_INVALID"),
    /**
     *رفلکشن: فیلد مورد نظر مطابق نام ورودی در کلاس وجود ندارد
     */
    REFLECTION_FIELD_IS_NOT_EXISTED("REFLECTION_FIELD_IS_NOT_EXISTED"),
    /**
     *رفلکشن: متد مورد نظر مطابق نام ورودی در کلاس وجود ندارد
     */
    REFLECTION_METHOD_IS_NOT_EXISTED("REFLECTION_METHOD_IS_NOT_EXISTED"),

    /**
     *ابزار فایل: فرمت نام فایل ورودی صحیح نمیباشد
     */
    FSO_FILE_NAME_FORMAT_IS_INVALID("FSO_FILE_NAME_FORMAT_IS_INVALID"),
    /**
     *ابزار فایل: مسیر داده شده در فایل سیستم وجود ندارد
     */
    FSO_PATH_IS_NOT_EXISTED("FSO_PATH_IS_NOT_EXISTED"),
    /**
     *ابزار فایل: مسیر داده شده در فایل سیستم از نوع دایرکتوری نیست
     */
    FSO_PATH_IS_NOT_DIRECTORY("FSO_PATH_IS_NOT_DIRECTORY"),
    /**
     *ابزار فایل: مسیر داده شده در فایل سیستم از نوع فایل نیست
     */
    FSO_PATH_IS_NOT_FILE("FSO_PATH_IS_NOT_FILE"),
    /**
     *ابزار فایل: مسیر مقصد  از قبل در فایل سیستم وجود دارد
     */
    FSO_DESTINATION_PATH_EXISTED("FSO_DESTINATION_PATH_EXISTED"),
    /**
     *ابزار فایل: مسیر فایل داده شده صحیح نمیباشد
     */
    FSO_MIMETYPE_NOT_VALID_FILE_PATH("FSO_MIMETYPE_NOT_VALID_FILE_PATH"),
    /**
     *ابزار تصویر: تصویر ورودی قابل خواندن نیست
     */
    IMAGE_ORGINAL_READ_PROBLEM("IMAGE_ORGINAL_READ_PROBLEM"),
    /**
     *مقدار داخل پارامتر مد در سرچ دیتا مدل باید مسیر یک کلاس اینترفیس ستونهای داده باشد
     */
    SEARCH_DATA_MODEL_PARAMETER_MODE_SHOULD_BE_CLASS_PATH("SEARCH_DATA_MODEL_PARAMETER_MODE_SHOULD_BE_CLASS_PATH"),




    TOKEN_IS_INVALID("TOKEN_IS_INVALID"),
    SMS_SEND_BATCH_FAILED("SMS_SEND_BATCH_FAILED"),
    SMS_SEND_CONNECTION_REFUSED("SMS_SEND_CONNECTION_REFUSED"),
    DATETIME_VALIDATION_FAILED("DATETIME_VALIDATION_FAILED"),

    ORIGINAL_IMAGE_IS_NULL_OR_INCORRECT("ORIGINAL_IMAGE_IS_NULL_OR_INCORRECT");

    private final String value;

    UtilityExceptionKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return super.toString();
    }
}
