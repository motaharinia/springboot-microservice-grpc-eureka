package com.motaharinia.msutility.fso.upload;

import com.motaharinia.msutility.fso.FileBaseModel;

import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-13<br>
 * Time: 14:52:37<br>
 * Description:<br>
 * کلاس مدل فایل جهت دانلود
 */
public class FileUploadedModel extends FileBaseModel {
    /**
     * تاریخ و زمان آپلود
     */
    private Date uploadDateTime;
    /**
     * مسیر آپلود شده
     */
    private String uploadedPath;
    /**
     * نام انتیتی فایل آپلود شده
     */
    private String entity;
    /**
     * نام زیرسیستم فایل آپلود شده
     */
    private String subSystem;
    /**
     * آرایه بایت داده فایل
     */
    private byte[] dataByteArray;
    /**
     * مسیر پوشه
     */
    private String directoryRealPath;
    /**
     * مسیر هش شده پوشه
     */
    private String directoryHashedPath;

    //getter-setter:
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Date getUploadDateTime() {
        return uploadDateTime;
    }

    public void setUploadDateTime(Date uploadDateTime) {
        this.uploadDateTime = uploadDateTime;
    }

    public String getUploadedPath() {
        return uploadedPath;
    }

    public void setUploadedPath(String uploadedPath) {
        this.uploadedPath = uploadedPath;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(String subSystem) {
        this.subSystem = subSystem;
    }

    public byte[] getDataByteArray() {
        return dataByteArray;
    }

    public void setDataByteArray(byte[] dataByteArray) {
        this.dataByteArray = dataByteArray;
    }

    public String getDirectoryRealPath() {
        return directoryRealPath;
    }

    public void setDirectoryRealPath(String directoryRealPath) {
        this.directoryRealPath = directoryRealPath;
    }

    public String getDirectoryHashedPath() {
        return directoryHashedPath;
    }

    public void setDirectoryHashedPath(String directoryHashedPath) {
        this.directoryHashedPath = directoryHashedPath;
    }

}
