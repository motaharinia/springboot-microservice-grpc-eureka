package ir.micser.statemachine.business.service.state;



import com.motaharinia.msjpautility.entity.GenericEntity;

import java.io.Serializable;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس مدل وضعیت
 */

public class StateModel extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    private Integer id;

    /**
     *کد مرجع
     */
    private String referenceCode;

    /**
     * نام سرویس
     */
    private ServiceNameEnum serviceNameEnum;

    /**
     * نام متد
     */
    private MethodTypeEnum methodTypeEnum;

    /**
     *تاریخ شروع
     */
    //private Date dateStart;

    /**
     *آیا انجام عملیات با خطا موجه شده است؟
     */
    private Boolean isFailed;

    /**
     *آیا رول بک صورت گرفته است؟
     */
    private Boolean isRolledBack;

    /**
     * لیست جزییات
     */
    private StateDetailModel stateDetailModel;

    //-------------getter,setter
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public ServiceNameEnum getServiceNameEnum() {
        return serviceNameEnum;
    }

    public void setServiceNameEnum(ServiceNameEnum serviceNameEnum) {
        this.serviceNameEnum = serviceNameEnum;
    }

    public MethodTypeEnum getMethodTypeEnum() {
        return methodTypeEnum;
    }

    public void setMethodTypeEnum(MethodTypeEnum methodTypeEnum) {
        this.methodTypeEnum = methodTypeEnum;
    }

    public Boolean getFailed() {
        return isFailed;
    }

    public void setFailed(Boolean failed) {
        isFailed = failed;
    }

    public Boolean getRolledBack() {
        return isRolledBack;
    }

    public void setRolledBack(Boolean rolledBack) {
        isRolledBack = rolledBack;
    }

    public StateDetailModel getStateDetailModel() {
        return stateDetailModel;
    }

    public void setStateDetailModel(StateDetailModel stateDetailModel) {
        this.stateDetailModel = stateDetailModel;
    }
}
