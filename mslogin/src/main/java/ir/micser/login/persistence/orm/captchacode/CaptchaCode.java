package ir.micser.login.persistence.orm.captchacode;




import com.motaharinia.msutility.entity.GenericEntity;
import ir.micser.login.business.service.captchacode.CaptchCodeTypeEnum;

import javax.persistence.*;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس انتیتی کد کپچا
 */
@Entity
@Table(name = "captcha_code")
public class CaptchaCode extends GenericEntity {

    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * کلید کپچا
     */
    @Column(name = "captcha_key")
    private String captchaKey;

    /**
     * مقدار کپچا
     */
    @Column(name = "captcha_value")
    private String captchaValue;

    /**
     * نوع کد کپچا
     */
//    @CustomEntityDeleteArchive
    @Enumerated(EnumType.STRING)
    @Column(name = "type_enum")
    private CaptchCodeTypeEnum typeEnum;

    /**
     * منقضی شده است؟
     */
//    @CustomEntityDeleteArchive
    @Column(name = "expired")
    private Boolean expired = false;

    /**
     * تعداد دفعات چک شده
     */
//    @CustomEntityDeleteArchive
    @Column(name = "retry")
    private Integer retry = 0;

    /**
     * شناسه انتیتی مرجع
     */
//    @CustomEntityDeleteArchive
    @Column(name = "reference_id")
    private Integer referenceId;

    /**
     * شناسه کاربری مرجع
     */
//    @CustomEntityDeleteArchive
    @Column(name = "reference_username")
    private String referenceUsername;

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }

    public Integer getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Integer referenceId) {
        this.referenceId = referenceId;
    }

    public CaptchCodeTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(CaptchCodeTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }

    public String getCaptchaKey() {
        return captchaKey;
    }

    public void setCaptchaKey(String captchaKey) {
        this.captchaKey = captchaKey;
    }

    public String getCaptchaValue() {
        return captchaValue;
    }

    public void setCaptchaValue(String captchaValue) {
        this.captchaValue = captchaValue;
    }

    public String getReferenceUsername() {
        return referenceUsername;
    }

    public void setReferenceUsername(String referenceUsername) {
        this.referenceUsername = referenceUsername;
    }

}
