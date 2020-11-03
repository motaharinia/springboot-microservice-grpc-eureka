package ir.micser.login.presentation.fso.fsouploadedhandle;

import ir.micser.login.business.service.fso.FsoModuleEnum;


public class FsoUploadedFileHandleDetailModel {

    private FsoModuleEnum fsoModuleEnum;
    private Boolean isSingle;
    private Integer width;
    private Integer height;

    //getter-setter:
    public FsoModuleEnum getFsoModuleEnum() {
        return fsoModuleEnum;
    }

    public void setFsoModuleEnum(FsoModuleEnum fsoModuleEnum) {
        this.fsoModuleEnum = fsoModuleEnum;
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

    public FsoUploadedFileHandleDetailModel() {
    }

    public FsoUploadedFileHandleDetailModel(FsoModuleEnum fsoModuleEnum, Boolean isSingle, Integer width, Integer height) {
        this.fsoModuleEnum = fsoModuleEnum;
        this.isSingle = isSingle;
        this.width = width;
        this.height = height;
    }

}
