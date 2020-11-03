package ir.micser.login.business.service.fso;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 * مقادیر ثابت فعالیت ماژول که روی فایل بعد از آپلود اتفاق می افتد<br>
 */
public enum FsoUploadedFileHandleActionEnum {
    /**
     * ثبت فایل جدید برای ثبت انتیتی جدید
     */
    ENTITY_CREATE("ENTITY_CREATE"),
    /**
     *ویرایش فایلهای یک انتیتی
     */
    ENTITY_UPDATE("ENTITY_UPDATE"),
    /**
     * حذف فایلهای یک انتیتی
     */
    ENTITY_DELETE("ENTITY_DELETE");

    private final String value;

    private FsoUploadedFileHandleActionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
