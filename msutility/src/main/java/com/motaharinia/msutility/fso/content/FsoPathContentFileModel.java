package com.motaharinia.msutility.fso.content;

import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:56:41<br>
 * Description:<br>
 * کلاس مدل اطلاعات فایل
 */
public class FsoPathContentFileModel {
    /**
     * نام فایل
     * example:"2019-06-12_10-39-29_dsa - Copy (2)"
     */
    private String name;
    /**
     * پسوند فایل
     * example:"png"
     */
    private String extension;
    /**
     * نام کامل فایل با پسوند
     * example:"2019-06-12_10-39-29_dsa - Copy (2).png"
     */
    private String fullName;
    /**
     * مسیر دایرکتوری والد
     */
    private String directoryPath;
    /**
     * مسیر کامل دایرکتوری به همراه نام فایل
     */
    private String fullPath;
    /**
     * تاریخ آخرین تغییر
     */
    private Date lastModifiedDate;
    /**
     * رشته تاریخ آخرین تغییر
     */
    private String lastModifiedDateString;
    /**
     * حجم فایل
     */
    private Long size;
    /**
     * نوع mimeType فایل
     * example:"image/png"
     */
    private String mimeType;

    /**
     * متد سازنده پیش فرض
     */
    public FsoPathContentFileModel() {
    }

    /**
     * متد سازنده که با دریافت مشخصات مدل را میسازد
     *
     * @param name                   نام فایل
     * @param extension              پسوند فایل
     * @param fullName               نام کامل فایل با پسوند
     * @param directoryPath          مسیر دایرکتوری والد
     * @param fullPath               مسیر کامل دایرکتوری به همراه نام فایل
     * @param lastModifiedDate       تاریخ آخرین تغییر
     * @param lastModifiedDateString رشته تاریخ آخرین تغییر
     * @param size                   حجم دایرکتوری
     * @param mimeType               نوع mimeType فایل
     */
    public FsoPathContentFileModel(String name, String extension, String fullName, String directoryPath, String fullPath, Date lastModifiedDate, String lastModifiedDateString, Long size, String mimeType) {
        this.name = name;
        this.extension = extension;
        this.fullName = fullName;
        this.directoryPath = directoryPath;
        this.fullPath = fullPath;
        this.lastModifiedDate = lastModifiedDate;
        this.lastModifiedDateString = lastModifiedDateString;
        this.size = size;
        this.mimeType = mimeType;
    }

    @Override
    public String toString() {
        return "FsoPathContentFileModel{" +
                "name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                ", fullName='" + fullName + '\'' +
                ", directoryPath='" + directoryPath + '\'' +
                ", fullPath='" + fullPath + '\'' +
                ", lastModified=" + lastModifiedDate +
                ", lastModifiedString='" + lastModifiedDateString + '\'' +
                ", size=" + size +
                '}';
    }

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

    public String getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(String directoryPath) {
        this.directoryPath = directoryPath;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedDateString() {
        return lastModifiedDateString;
    }

    public void setLastModifiedDateString(String lastModifiedDateString) {
        this.lastModifiedDateString = lastModifiedDateString;
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
}
