
package com.motaharinia.msjpautility.entity;

import org.hibernate.search.annotations.Field;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

//https://vladmihalcea.com/prepersist-preupdate-embeddable-jpa-hibernate/
//https://stackoverflow.com/questions/49954812/how-can-you-make-a-created-at-column-generate-the-creation-date-time-automatical/49954903

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *     این کلاس به عنوان کلاس پدر تمام کلاسهای انتیتی آماده شده است و به تمام انتیتی ها فیلدهای استانداردی را اضافه میکتد
 */
@MappedSuperclass
public class GenericEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * آی پی کاربری که انتیتی را ایجاد کرده است
     */
//    @CustomEntityDeleteArchive
    @Column(name = "create_user_ip")
    private String createUserIp;
//    @CustomEntityDeleteArchive
    /**
     * آی پی کاربری که انتیتی را آخرین بار ویرایش کرده است
     */
    @Column(name = "update_user_ip")
    private String updateUserIp;

    /**
     * عدم نمایش انتیتی در بیزینس نرم افزار
     */
    @Field
//    @CustomEntityDeleteArchive
    @Column(name = "hidden")
    private Boolean hidden = false;
    /**
     * غیر فعال کردن انتیتی در بیزینس نرم افزار
     */
    @Field
//    @CustomEntityDeleteArchive
    @Column(name = "invalid")
    private Boolean invalid = false;

    /**
     * تاریخی که انتیتی ایجاد شده است
     */
//    @CustomEntityDeleteArchive
    @CreatedDate
    @Column(name = "create_at", columnDefinition = OracleUtility.COLUMN_DEFINITION_DATE)
    private Date createAt;
    /**
     * تاریخی که انتیتی آخرین بار ویرایش شده است
     */
//    @CustomEntityDeleteArchive
    @Column(name = "update_at", columnDefinition = OracleUtility.COLUMN_DEFINITION_DATE)
    private Date updateAt;

    /**
     * آی دی کاربری که انتیتی را ایجاد کرده است
     */
    @CreatedBy
    @Column(name = "create_user_id")
    private Integer createUserId;
    /**
     * آی دی کاربری که انتیتی را آخرین بار ویرایش کرده است
     */
    @Column(name = "update_user_id")
    private Integer updateUserId;


//    @PrePersist
//    public void prePersist() {
//        createAt = new Date();
//        createUserId = LoggedUser.getId();
//    }
//
//    @PreUpdate
//    public void preUpdate() {
//        updateAt = new Date();
//        updateUserId = LoggedUser.getId();
//    }
//




    //getter-setter:
    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getCreateUserIp() {
        return createUserIp;
    }

    public void setCreateUserIp(String createUserIp) {
        this.createUserIp = createUserIp;
    }

    public String getUpdateUserIp() {
        return updateUserIp;
    }

    public void setUpdateUserIp(String updateUserIp) {
        this.updateUserIp = updateUserIp;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public Integer getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Integer updateUserId) {
        this.updateUserId = updateUserId;
    }


}
