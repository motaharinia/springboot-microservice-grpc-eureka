package ir.micser.login.presentation.fso.crudfilehandle;


import com.motaharinia.msutility.fso.view.FileViewModel;
import ir.micser.login.business.service.fso.CrudFileHandleActionEnum;

import java.util.ArrayList;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:کلاس مدل فایلها در کراد ماژولها
 *
 */
public class CrudFileHandleModel {

    /**
     * شناسه انتیتی
     */
    private Integer entityId;
    /**
     * نوع عملیات فایل که میتواند ثبت ، ویرایش یا حذف باشد
     */
    private CrudFileHandleActionEnum crudFileHandleActionEnum;
    /**
     * لیست مدل مشاهده فایلها
     */
    private List<FileViewModel> fileViewModelList = new ArrayList<>();
    /**
     * لیست مدل شرح فایلها در کراد ماژولها<br>
     *     که نشان میدهد لیست فایلها در چه مسیرهایی با چه ابعادی و به صورت تکی یا چند تایی ذخیره شود
     */
    private List<CrudFileHandleDetailModel> crudFileHandleDetailModelList = new ArrayList<>();



    public CrudFileHandleModel() {
    }

    public CrudFileHandleModel(Integer entityId, CrudFileHandleActionEnum crudFileHandleActionEnum, List<FileViewModel> fileViewModelList, List<CrudFileHandleDetailModel> crudFileHandleDetailModelList) {
        this.entityId = entityId;
        this.crudFileHandleActionEnum = crudFileHandleActionEnum;
        this.fileViewModelList = fileViewModelList;
//        this.fileKeySet = fileViewModelList.stream().filter(fileViewModel -> fileViewModel.getStatusEnum().equals(FileViewModelStatusEnum.ADDED)).map(FileViewModel::getFileKey).collect(Collectors.toSet());
        this.crudFileHandleDetailModelList = crudFileHandleDetailModelList;
    }

    //getter-setter:
    public Integer getEntityId() {
        return entityId;
    }

    public void setEntityId(Integer entityId) {
        this.entityId = entityId;
    }

    public CrudFileHandleActionEnum getCrudFileHandleActionEnum() {
        return crudFileHandleActionEnum;
    }

    public void setCrudFileHandleActionEnum(CrudFileHandleActionEnum crudFileHandleActionEnum) {
        this.crudFileHandleActionEnum = crudFileHandleActionEnum;
    }

    public List<FileViewModel> getFileViewModelList() {
        return fileViewModelList;
    }

    public void setFileViewModelList(List<FileViewModel> fileViewModelList) {
        this.fileViewModelList = fileViewModelList;
    }


    public List<CrudFileHandleDetailModel> getCrudFileHandleDetailModelList() {
        return crudFileHandleDetailModelList;
    }

    public void setCrudFileHandleDetailModelList(List<CrudFileHandleDetailModel> crudFileHandleDetailModelList) {
        this.crudFileHandleDetailModelList = crudFileHandleDetailModelList;
    }



}
