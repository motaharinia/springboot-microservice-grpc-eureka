package ir.micser.login.business.service.authentication;

public enum LoginExceptionEnum {
    USERNAME_OR_PASSWORD_INVALID("loginException.usernameOrPasswordInvalid"),
    CAPTCHA_REQUIRED("loginException.captchaRequired"),
    CAPTCHA_INVALID("loginException.captchaInvalid"),
    CAPTCHA_IGNORED("loginException.captchaIgnored"),;
    private final String value;

    private LoginExceptionEnum(String value) {
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
