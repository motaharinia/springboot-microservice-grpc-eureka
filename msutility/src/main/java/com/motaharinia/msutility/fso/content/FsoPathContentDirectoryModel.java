package com.motaharinia.msutility.fso.content;

import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:57:15<br>
 * Description:<br>
 * کلاس مدل اطلاعات دایرکتوری
 */
public class FsoPathContentDirectoryModel {
    /**
     * نام دایرکتوری
     */
    private String name;
    /**
     * مسیر  دایرکتوری والد
     */
    private String directoryPath;
    /**
     * مسیر کامل دایرکتوری به همراه نام
     */
    private String fullPath;
    /**
     * تاریخ آخرین تغییر
     */
    private Date lastModified;
    /**
     * رشته تاریخ آخرین تغییر
     */
    private String lastModifiedString;
    /**
     * حجم دایرکتوری
     */
    private Long size;


    /**
     * متد سازنده پیش فرض
     */
    public FsoPathContentDirectoryModel() {
    }

    /**
     * متد سازنده که با دریافت اطلاعات مدل را میسازد
     *
     * @param name               نام دایرکتوری
     * @param directoryPath      مسیر  دایرکتوری والد
     * @param fullPath  مسیر کامل دایرکتوری به همراه نام
     * @param lastModified       تاریخ آخرین تغییر
     * @param lastModifiedString رشته تاریخ آخرین تغییر
     * @param size               حجم دایرکتوری
     */
    public FsoPathContentDirectoryModel(String name, String directoryPath, String fullPath, Date lastModified, String lastModifiedString, Long size) {
        this.name = name;
        this.directoryPath = directoryPath;
        this.fullPath = fullPath;
        this.lastModified = lastModified;
        this.lastModifiedString = lastModifiedString;
        this.size = size;
    }

    @Override
    public String toString() {
        return "FsoPathContentDirectoryModel{" +
                "name='" + name + '\'' +
                ", directoryPath='" + directoryPath + '\'' +
                ", fullPath='" + fullPath + '\'' +
                ", lastModified=" + lastModified +
                ", lastModifiedString='" + lastModifiedString + '\'' +
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

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public String getLastModifiedString() {
        return lastModifiedString;
    }

    public void setLastModifiedString(String lastModifiedString) {
        this.lastModifiedString = lastModifiedString;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }
}
