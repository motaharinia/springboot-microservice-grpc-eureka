package ir.micser.login.business.service.authentication;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 * مدل خطا احراز هویت<br>
 */
public class LoginExceptionModel {
    /**
     * نوع خطا
     */
    private LoginExceptionEnum loginException;
    /**
     * تعداد ورود ناصحیح کلمه کاربری یا رمز عبور
     */
    private Integer loginFailureCount;
    /**
     * توضیحات خطا
     */
    private String exceptionDescription;

    //getter-setter:
    public LoginExceptionEnum getLoginException() {
        return loginException;
    }

    public void setLoginException(LoginExceptionEnum loginException) {
        this.loginException = loginException;
    }

    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }

    public LoginExceptionModel() {
    }

    public LoginExceptionModel(LoginExceptionEnum loginExceptionEnum, Integer loginFailureCount) {
        this.loginException = loginExceptionEnum;
        this.loginFailureCount = loginFailureCount;
    }
}
