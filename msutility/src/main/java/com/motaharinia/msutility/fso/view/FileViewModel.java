package com.motaharinia.msutility.fso.view;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.fso.FileBaseModel;
import io.leangen.graphql.annotations.GraphQLIgnore;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:43:42<br>
 * Description:<br>
 * کلاس مدل مشاهده فایل جهت ارسال به کلاینت
 */
public class FileViewModel extends FileBaseModel {


    /**
     * مسیر کامل دایرکتوری به همراه نام فایل
     * example:"/70755/personality/2019-06-12_10-39-29_dsa - Copy (2).png"
     */
    @GraphQLIgnore
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
