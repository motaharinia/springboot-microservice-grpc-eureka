/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.micser.login.presentation.loguploadedfile;


import com.motaharinia.msutility.fso.view.FileViewModel;
import ir.micser.login.business.service.loguploadedfile.LogUploadedFileHandleActionEnum;

import java.util.ArrayList;
import java.util.List;



public class LogUploadedFileHandleModel {

    /**
     * شناسه انتیتی
     */
    private Integer entityId;
    /**
     * نوع عملیات فایل که میتواند ثبت ، ویرایش یا حذف باشد
     */
    private LogUploadedFileHandleActionEnum logUploadedFileHandleActionEnum;
    /**
     * لیست مدل مشاهده فایلها
     */
    private List<FileViewModel> fileViewModelList = new ArrayList<>();
    /**
     * لیست مدل آپلود هندل فایلها<br>
     *     که نشان میدهد لیست فایلها در چه مسیرهایی با چه ابعادی و به صورت تکی یا چند تایی ذخیره شود
     */
    private List<LogUploadedFileHandleFsoModel> logUploadedFileHandleFsoModelList = new ArrayList<>();

    //getter-setter:
    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public LogUploadedFileHandleActionEnum getLogUploadedFileHandleActionEnum() {
        return logUploadedFileHandleActionEnum;
    }

    public void setLogUploadedFileHandleActionEnum(LogUploadedFileHandleActionEnum logUploadedFileHandleActionEnum) {
        this.logUploadedFileHandleActionEnum = logUploadedFileHandleActionEnum;
    }

    public List<FileViewModel> getFileViewModelList() {
        return fileViewModelList;
    }

    public void setFileViewModelList(List<FileViewModel> fileViewModelList) {
        this.fileViewModelList = fileViewModelList;
    }


    public List<LogUploadedFileHandleFsoModel> getLogUploadedFileHandleFsoModelList() {
        return logUploadedFileHandleFsoModelList;
    }

    public void setLogUploadedFileHandleFsoModelList(List<LogUploadedFileHandleFsoModel> logUploadedFileHandleFsoModelList) {
        this.logUploadedFileHandleFsoModelList = logUploadedFileHandleFsoModelList;
    }

    public LogUploadedFileHandleModel() {
    }

    public LogUploadedFileHandleModel(Integer entityId, LogUploadedFileHandleActionEnum logUploadedFileHandleActionEnum, List<FileViewModel> fileViewModelList, List<LogUploadedFileHandleFsoModel> logUploadedFileHandleFsoModelList) {
        this.entityId = entityId;
        this.logUploadedFileHandleActionEnum = logUploadedFileHandleActionEnum;
        this.fileViewModelList = fileViewModelList;
//        this.fileKeySet = fileViewModelList.stream().filter(fileViewModel -> fileViewModel.getStatusEnum().equals(FileViewModelStatusEnum.ADDED)).map(FileViewModel::getFileKey).collect(Collectors.toSet());
        this.logUploadedFileHandleFsoModelList = logUploadedFileHandleFsoModelList;
    }

}
