package ir.micser.login.business.service.captchacode;


import com.motaharinia.msutility.customexception.CustomExceptionKey;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 * مقادیر ثابت خطاهای بیزینسی که در کلاینت ساید پروژه ترجمه آنها طبق زبان انتخاب شده کاربر نمایش داده خواهد شد
 */
public enum CaptchaCodeBusinessExceptionKeyEnum implements CustomExceptionKey {


    /**
     * کد کپچا وارد شده صحیح نمیباشد
     */
    CAPTCHA_CODE_REJECT("CAPTCHA_CODE_REJECT"),
    /**
     * تعداد ورود کد کپچای غلط بیش از حد مجاز است. تا روز بعد امکان ورود به سامانه را ندارید
     */
    CAPTCHA_CODE_REFERENCE_IS_NULL("CAPTCHA_CODE_REFERENCE_IS_NULL"),
    /**
     * نوع کپچا کد خالی است
     */
    CAPTCHA_CODE_TYPE_IS_NULL("CAPTCHA_CODE_TYPE_IS_NULL"),
    /**
     * تعداد ورود کد کپچای غلط بیش از حد مجاز است. تا روز بعد امکان ورود به سامانه را ندارید
     */
    CAPTCHA_CODE_IGNORE("CAPTCHA_CODE_IGNORE"),

    /**
     * کلید کپچا یافت نشد
     */
    CAPTCHA_CODE_NOT_FOUND("CAPTCHA_CODE_NOT_FOUND"),

    ;

    private final String value;
    private final String moduleName = "test";

    CaptchaCodeBusinessExceptionKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return moduleName + "." + value;
    }

    public String toString() {
        return super.toString();
    }
}
