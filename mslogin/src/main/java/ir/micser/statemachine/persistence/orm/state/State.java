package ir.micser.statemachine.persistence.orm.state;


import com.motaharinia.msutility.entity.GenericEntity;
import com.motaharinia.msutility.entity.OracleUtility;
import ir.micser.statemachine.business.service.state.MethodTypeEnum;
import ir.micser.statemachine.business.service.state.ServiceNameEnum;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس انتیتی وضعیت
 */
@Entity
@Table(name = "state")
public class State extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     *کد مرجع
     */
    @Column(name = "reference_code")
    private String referenceCode;

    /**
     *آیدی انتیتی شروع کننده عملیات
     */
    @Column(name = "reference_id")
    private Integer referenceId;

    /**
     * نام سرویس
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "service_name_enum")
    private ServiceNameEnum serviceNameEnum;

    /**
     * نام متد
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "method_type_enum")
    private MethodTypeEnum methodTypeEnum;

    /**
     *تاریخ شروع
     */
    @Column(name = "date_start", columnDefinition = OracleUtility.COLUMN_DEFINITION_DATE)
    private Date dateStart;

    /**
     *آیا انجام عملیات با خطا موجه شده است؟
     */
    @Column(name = "is_failed")
    private Boolean isFailed = false;

    /**
     *آیا رول بک صورت گرفته است؟
     */
    @Column(name = "is_rolled_back")
    private Boolean isRolledBack = false;

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

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
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

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }
}
