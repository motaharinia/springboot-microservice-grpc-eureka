package ir.micser.login.business.service.authentication;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 * مقادیر ثابت خطاهای احراز هویت<br>
 */
public enum LoginExceptionEnum {
    /**
     * کلمه کاربری یا رمز عبور صحیح نمیباشد
     */
    USERNAME_OR_PASSWORD_INVALID("loginException.usernameOrPasswordInvalid"),
    /**
     * ورود کد کپچا الزامی است
     */
    CAPTCHA_REQUIRED("loginException.captchaRequired"),
    /**
     * کد کپچا وارد شده صحبح نمیباشد
     */
    CAPTCHA_INVALID("loginException.captchaInvalid"),
    /**
     * به علت ورود بیش از حد کد کپچا غلط تا اطلاع ثانوی ورود امکان پذیر نمیباشد. لطفا با ادمین سامانه تماس بگیرید
     */
    CAPTCHA_IGNORED("loginException.captchaIgnored"),
    ;
    private final String value;

    LoginExceptionEnum(String value) {
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
