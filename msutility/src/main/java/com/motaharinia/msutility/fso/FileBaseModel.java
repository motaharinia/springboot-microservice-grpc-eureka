package com.motaharinia.msutility.fso;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-13<br>
 * Time: 15:58:12<br>
 * Description:کلاس مدل پایه اطلاعات فایل
 */
public class FileBaseModel {
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
    protected long size;
    /**
     * نوع mimeType فایل
     * example:"image/png"
     */
    protected String mimeType;

    /**
     * درصورتی که فایل جدید آپلود شده باشد این فیلد پر میشود
     */
    protected String key;


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

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
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
}
