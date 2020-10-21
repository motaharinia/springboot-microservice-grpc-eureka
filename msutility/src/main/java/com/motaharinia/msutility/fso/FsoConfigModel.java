package com.motaharinia.msutility.fso;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-16<br>
 * Time: 11:00:18<br>
 * Description:کلاس مدل تنظیمات ابزار فایل
 */
public class FsoConfigModel {
    /**
     * ابعاد تصویر بندانگشتی  فایلهای تصویری
     */
    private Integer[] thumbSizeArray = new Integer[]{60, 120};
    /**
     * پسوند فایلهای بندانگشتی تصاویر
     */
    private String thumbExtension = "thumb";
    /**
     * حداکثر تعداد مجاز فایل در یک دایرکتوری
     */
    private Integer directoryFileLimit = 100;


    /**
     * متد سازنده پیش فرض
     */
    public FsoConfigModel() {
    }

    /**
     * متد سازنده که با دریافت مقادیر ورودی مدل را میسازد
     *
     * @param thumbSizeArray ابعاد تصویر بندانگشتی  فایلهای تصویری
     * @param thumbExtension پسوند فایلهای بندانگشتی تصاویر
     * @param directoryFileLimit    حداکثر تعداد مجاز فایل در یک دایرکتوری
     */
    public FsoConfigModel(Integer[] thumbSizeArray, String thumbExtension, Integer directoryFileLimit) {
        this.thumbSizeArray = thumbSizeArray;
        this.thumbExtension = thumbExtension;
        this.directoryFileLimit = directoryFileLimit;
    }

    //getter-setter:
    public Integer[] getThumbSizeArray() {
        return thumbSizeArray;
    }

    public void setThumbSizeArray(Integer[] thumbSizeArray) {
        this.thumbSizeArray = thumbSizeArray;
    }

    public String getThumbExtension() {
        return thumbExtension;
    }

    public void setThumbExtension(String thumbExtension) {
        this.thumbExtension = thumbExtension;
    }

    public Integer getDirectoryFileLimit() {
        return directoryFileLimit;
    }

    public void setDirectoryFileLimit(Integer directoryFileLimit) {
        this.directoryFileLimit = directoryFileLimit;
    }
}
