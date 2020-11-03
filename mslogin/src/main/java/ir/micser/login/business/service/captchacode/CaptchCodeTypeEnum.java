package ir.micser.login.business.service.captchacode;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 * مقادیر ثابت انواع سرویس های کد کپچا<br>
 */
public enum CaptchCodeTypeEnum {
    /**
     * کد کپچا برای سرویس احراز هویت
     */
    LOGIN_CHECK_BY_USERNAME("LOGIN_CHECK_BY_USERNAME");

    private final String value;

     CaptchCodeTypeEnum(String value) {
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
