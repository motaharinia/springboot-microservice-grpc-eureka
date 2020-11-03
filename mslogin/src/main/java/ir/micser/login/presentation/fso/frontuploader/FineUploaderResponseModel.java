package ir.micser.login.presentation.fso.frontuploader;


public class FineUploaderResponseModel {

    private Boolean success;
    private String error;
    private Boolean preventRetry;

    //getter-setter:
    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Boolean getPreventRetry() {
        return preventRetry;
    }

    public void setPreventRetry(Boolean preventRetry) {
        this.preventRetry = preventRetry;
    }

}
