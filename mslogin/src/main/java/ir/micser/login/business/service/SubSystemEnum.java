package ir.micser.login.business.service;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 * مقادیر ثابت زیر سیستمهای پروژه<br>
 */
public enum SubSystemEnum {

    /**
     * زیرسیستم عمومی
     */
    COMMON("COMMON"),
    /**
     * زیرسیستم فروشگاه آنلاین
     */
    ESHOP("ESHOP"),
    /**
     * زیر سیستم فرانت سایت
     */
    WWW("WWW"),
    /**
     * زیرسیستم پشتیبانی فنی
     */
    SUPPORT("SUPPORT");

    private String value;

    private SubSystemEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
