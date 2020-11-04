package com.motaharinia.msutility.fso.upload;


import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-13<br>
 * Time: 14:52:37<br>
 * Description:<br>
 * کلاس مدل فایل جهت دانلود
 */
public class FileUploadedModel {
    /**
     * نام فایل
     * example:"2019-06-12_10-39-29_dsa - Copy (2)"
     */
    protected String name;
    /**
     * پسوند فایل
     * example:"png"
     */
    protected String extension;
    /**
     * نام کامل فایل با پسوند
     * example:"2019-06-12_10-39-29_dsa - Copy (2).png"
     */
    protected String fullName;
    /**
     * حجم فایل
     * example:12109
     */
    protected Long size;
    /**
     * نوع mimeType فایل
     * example:"image/png"
     */
    protected String mimeType;

    /**
     * درصورتی که فایل جدید آپلود شده باشد این فیلد پر میشود
     */
    protected String key;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

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
