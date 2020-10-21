package ir.micser.login.business.service.captchacode;

import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس مدل کد کپچا
 */
public class CaptchaCodeModel {

    /**
     * شناسه
     */
    private Integer id;
    /**
     * کلید کپچا
     */
    private String captchaKey;
    /**
     * مقدار کپچا
     */
    private String captchaValue;
    /**
     * تصویر کپچا
     */
    private byte[] captchaImageByteArray;
    /**
     * تاریخ تصویر کپچا
     */
    private Date date;
    /**
     * مقدار کپچا
     */
    private String captchValue;
    /**
     * نوع کد کپچا
     */
    private CaptchCodeTypeEnum captchCodeTypeEnum;

    /**
     * متد سازنده پیش فرض
     */
    public CaptchaCodeModel() {
    }

    /**
     * متد سازنده که با ورودی مدل را میسازد
     * @param date تاریخ
     * @param captchValue مقدار کپچا
     */
    public CaptchaCodeModel(Date date, String captchValue) {
        this.date = date;
        this.captchValue = captchValue;
    }



    //getter-setter:
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCaptchValue() {
        return captchValue;
    }

    public void setCaptchValue(String captchValue) {
        this.captchValue = captchValue;
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

    public byte[] getCaptchaImageByteArray() {
        return captchaImageByteArray;
    }

    public void setCaptchaImageByteArray(byte[] captchaImageByteArray) {
        this.captchaImageByteArray = captchaImageByteArray;
    }

    public CaptchCodeTypeEnum getCaptchCodeTypeEnum() {
        return captchCodeTypeEnum;
    }

    public void setCaptchCodeTypeEnum(CaptchCodeTypeEnum captchCodeTypeEnum) {
        this.captchCodeTypeEnum = captchCodeTypeEnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
