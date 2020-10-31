package ir.micser.login.presentation.loguploadedfile;

import ir.micser.login.business.service.loguploadedfile.LogUploadedFsoEnum;


public class LogUploadedFileHandleFsoModel {

    private LogUploadedFsoEnum logUploadedFsoEnum;
    private Boolean isSingle;
    private Integer width;
    private Integer height;

    //getter-setter:
    public LogUploadedFsoEnum getLogUploadedFsoEnum() {
        return logUploadedFsoEnum;
    }

    public void setLogUploadedFsoEnum(LogUploadedFsoEnum logUploadedFsoEnum) {
        this.logUploadedFsoEnum = logUploadedFsoEnum;
    }

    public Boolean getIsSingle() {
        return isSingle;
    }

    public void setIsSingle(Boolean isSingle) {
        this.isSingle = isSingle;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public LogUploadedFileHandleFsoModel() {
    }

    public LogUploadedFileHandleFsoModel(LogUploadedFsoEnum logUploadedFsoEnum,Boolean isSingle, Integer width, Integer height) {
        this.logUploadedFsoEnum = logUploadedFsoEnum;
        this.isSingle = isSingle;
        this.width = width;
        this.height = height;
    }

}
