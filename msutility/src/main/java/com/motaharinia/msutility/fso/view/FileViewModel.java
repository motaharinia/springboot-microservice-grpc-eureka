package com.motaharinia.msutility.fso.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.motaharinia.msutility.customfield.CustomDate;
import io.leangen.graphql.annotations.GraphQLIgnore;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:43:42<br>
 * Description:<br>
 * کلاس مدل مشاهده فایل جهت ارسال به کلاینت
 */
public class FileViewModel {
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
     * مسیر کامل دایرکتوری به همراه نام فایل
     * example:"/70755/personality/2019-06-12_10-39-29_dsa - Copy (2).png"
     */
    @JsonIgnore
    private String fullPath;
    /**
     * قسمت هش شده ای از مسیر کامل دایرکتوری به همراه نام فایل
     * example:"LzcwNzU1L3BlcnNvbmFsaXR5LzIwMTktMDYtMTJfMTAtMzktMjlfZHNhIC0gQ29weSAoMikucG5n"
     */
    private String hashedPath;
    /**
     * تاریخ آخرین تغییر فایل
     * example:{year:1398,month:10,day:25}
     */
    private CustomDate lastModifiedDate;
    /**
     * وضعیت فایل
     * example: ADDED,DELETED,EXISTED
     */
    private FileViewModelStatusEnum statusEnum = FileViewModelStatusEnum.EXISTED;


    @Override
    public String toString() {
        return "FileViewModel{" + "name=" + name + ", extension=" + extension + ", fullName=" + fullName + ", realPath=" + fullPath + ", hashedPath=" + hashedPath + ", lastModifiedDate=" + lastModifiedDate + ", size=" + size + ", mimeType=" + mimeType + ", key=" + key + '}';
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

    @GraphQLIgnore
    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getHashedPath() {
        return hashedPath;
    }

    public void setHashedPath(String hashedPath) {
        this.hashedPath = hashedPath;
    }

    public CustomDate getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(CustomDate lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public FileViewModelStatusEnum getStatusEnum() {
        return statusEnum;
    }

    public void setStatusEnum(FileViewModelStatusEnum statusEnum) {
        this.statusEnum = statusEnum;
    }


}
