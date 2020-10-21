package ir.micser.login.business.service.authentication;

public class LoginExceptionModel {
    private LoginExceptionEnum loginException;
    private Integer loginFailureCount;
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
