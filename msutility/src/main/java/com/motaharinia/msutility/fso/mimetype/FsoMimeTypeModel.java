package com.motaharinia.msutility.fso.mimetype;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-07<br>
 * Time: 15:22:37<br>
 * Description:<br>
 */
public class FsoMimeTypeModel {
    /**
     * رشته mimeType فایل
     */
    String mimeType;

    /**
     * نوع فایل
     */
    FsoMimeTypeEnum type;

    /**
     * پسوند فایل
     */
    String extension;

    /**
     * متد سازنده پیش فرض
     */
    public FsoMimeTypeModel() {
    }

    /**
     * متد سازنده که با دریافت مقادیر مدل را میسازد
     * @param mimeType  رشته mimeType فایل
     * @param type  نوع فایل
     * @param extension  پسوند فایل
     */
    public FsoMimeTypeModel(String mimeType, FsoMimeTypeEnum type, String extension) {
        this.mimeType = mimeType;
        this.type = type;
        this.extension = extension;
    }

    //getter-setter:
    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public FsoMimeTypeEnum getType() {
        return type;
    }

    public void setType(FsoMimeTypeEnum type) {
        this.type = type;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }
}
