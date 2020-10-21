package ir.micser.login.presentation.captchacode;

public class CaptchaCodeCheckModel {
    /**
     * تعداد دفعات تلاش برای کپچا
     */
    private Integer captchaRetry;
    /**
     * کد کپچا منقضی شده و استفاده شده است
     */
    private Boolean captchaExpired;
    /**
     * اکسپشن کپچا
     */
    private Exception captchaException;

    //getter-setter:

    public Exception getCaptchaException() {
        return captchaException;
    }

    public void setCaptchaException(Exception captchaException) {
        this.captchaException = captchaException;
    }

    public Integer getCaptchaRetry() {
        return captchaRetry;
    }

    public void setCaptchaRetry(Integer captchaRetry) {
        this.captchaRetry = captchaRetry;
    }

    public Boolean getCaptchaExpired() {
        return captchaExpired;
    }

    public void setCaptchaExpired(Boolean captchaExpired) {
        this.captchaExpired = captchaExpired;
    }

}
