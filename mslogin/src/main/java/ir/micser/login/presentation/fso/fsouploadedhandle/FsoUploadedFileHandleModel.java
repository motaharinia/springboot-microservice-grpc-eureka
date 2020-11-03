package ir.micser.login.presentation.fso.fsouploadedhandle;


import com.motaharinia.msutility.fso.view.FileViewModel;
import ir.micser.login.business.service.fso.FsoUploadedFileHandleActionEnum;

import java.util.ArrayList;
import java.util.List;



public class FsoUploadedFileHandleModel {

    /**
     * شناسه انتیتی
     */
    private Integer entityId;
    /**
     * نوع عملیات فایل که میتواند ثبت ، ویرایش یا حذف باشد
     */
    private FsoUploadedFileHandleActionEnum fsoUploadedFileHandleActionEnum;
    /**
     * لیست مدل مشاهده فایلها
     */
    private List<FileViewModel> fileViewModelList = new ArrayList<>();
    /**
     * لیست مدل آپلود هندل فایلها<br>
     *     که نشان میدهد لیست فایلها در چه مسیرهایی با چه ابعادی و به صورت تکی یا چند تایی ذخیره شود
     */
    private List<FsoUploadedFileHandleDetailModel> fsoUploadedFileHandleDetailModelList = new ArrayList<>();



    public FsoUploadedFileHandleModel() {
    }

    public FsoUploadedFileHandleModel(Integer entityId, FsoUploadedFileHandleActionEnum fsoUploadedFileHandleActionEnum, List<FileViewModel> fileViewModelList, List<FsoUploadedFileHandleDetailModel> fsoUploadedFileHandleDetailModelList) {
        this.entityId = entityId;
        this.fsoUploadedFileHandleActionEnum = fsoUploadedFileHandleActionEnum;
        this.fileViewModelList = fileViewModelList;
//        this.fileKeySet = fileViewModelList.stream().filter(fileViewModel -> fileViewModel.getStatusEnum().equals(FileViewModelStatusEnum.ADDED)).map(FileViewModel::getFileKey).collect(Collectors.toSet());
        this.fsoUploadedFileHandleDetailModelList = fsoUploadedFileHandleDetailModelList;
    }

    //getter-setter:
    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public FsoUploadedFileHandleActionEnum getFsoUploadedFileHandleActionEnum() {
        return fsoUploadedFileHandleActionEnum;
    }

    public void setFsoUploadedFileHandleActionEnum(FsoUploadedFileHandleActionEnum fsoUploadedFileHandleActionEnum) {
        this.fsoUploadedFileHandleActionEnum = fsoUploadedFileHandleActionEnum;
    }

    public List<FileViewModel> getFileViewModelList() {
        return fileViewModelList;
    }

    public void setFileViewModelList(List<FileViewModel> fileViewModelList) {
        this.fileViewModelList = fileViewModelList;
    }


    public List<FsoUploadedFileHandleDetailModel> getFsoUploadedFileHandleDetailModelList() {
        return fsoUploadedFileHandleDetailModelList;
    }

    public void setFsoUploadedFileHandleDetailModelList(List<FsoUploadedFileHandleDetailModel> fsoUploadedFileHandleDetailModelList) {
        this.fsoUploadedFileHandleDetailModelList = fsoUploadedFileHandleDetailModelList;
    }



}
