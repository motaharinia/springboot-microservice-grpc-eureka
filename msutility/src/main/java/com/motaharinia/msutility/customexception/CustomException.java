package com.motaharinia.msutility.customexception;

import io.grpc.StatusRuntimeException;
import org.jetbrains.annotations.NotNull;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 02:31:29<br>
 * Description:<br>
 * کلاس اکسپشن پدر پروژه که در دیتابیس لاگ میشود و انواع دیگر اکسپشنها از آن گسترش میابند
 */
public class CustomException extends RuntimeException {

    /**
     * نوع اکسپشن
     */
    @NotNull
    private CustomExceptionTypeEnum exceptionType;
    /**
     * کلاسی که در آن اکسپشن اتفاق افتاده است
     */
    @NotNull
    private Class exceptionOccurredClass;
    /**
     * کلید خطای اکسپشن که در کلاینت ترجمه خواهد شد
     * در این کلاس کلید ارسالی توسط نوع اکسپشن (بیزینس یا یوتیلیتی یا لیست) و نام کلاسی که خطا در آن اتفاق افتاده کامل میشود
     * تا در کلاینت کلیدهای خطا دسته بندی شده در یک رشته دریافت گردد
     * نمونه:
     * BUSINESS_EXCEPTION.MEMBER.NATIONAL_CODE_DUPLICATE
     */
    @NotNull
    private String exceptionMessage;
    /**
     * توضیحات خطا که میخواهیم به کلاینت ارسال نماییم
     */
    private String exceptionDescription;

    /**
     * متد سازنده کاستوم اکسپشن
     *
     * @param exceptionType          نوع اکسپشن
     * @param exceptionOccurredClass کلاسی که در آن اکسپشن اتفاق افتاده است
     * @param customExceptionKey     کلید خطای اکسپشن که در کلاینت ترجمه خواهد شد
     * @param exceptionDescription   توضیحات خطا که میخواهیم به کلاینت ارسال نماییم
     */
    public CustomException(@NotNull CustomExceptionTypeEnum exceptionType, @NotNull Class exceptionOccurredClass, @NotNull CustomExceptionKey customExceptionKey, String exceptionDescription) {
//        super(exceptionType.getValue().toUpperCase() + "." + exceptionOccurredClass.getSimpleName().toUpperCase() + "." + customExceptionKey.getValue().toUpperCase());
        this.exceptionType = exceptionType;
        this.exceptionOccurredClass = exceptionOccurredClass;
        this.exceptionMessage = exceptionType.getValue().toUpperCase() + "." + exceptionOccurredClass.getSimpleName().toUpperCase() + "." + customExceptionKey.getValue().toUpperCase();
        this.exceptionDescription = exceptionDescription;
    }

    /**
     * متد سازنده ای که از خطای runtime مربوط به فراخوانی مایکروسرویس این مدل را میسازد
     *
     * @param statusRuntimeException خطای دریافتی از grpc
     */
    public CustomException(@NotNull StatusRuntimeException statusRuntimeException) {
        String description = statusRuntimeException.getStatus().getDescription();
        String[] descriptionArray = description.split(":::");
        if (descriptionArray.length > 1) {
            if (descriptionArray[0].split("\\.").length > 0) {
                this.exceptionType = CustomExceptionTypeEnum.valueOf(descriptionArray[0].split("\\.")[0]);
            }
            this.exceptionMessage = descriptionArray[0];
            this.exceptionDescription = descriptionArray[1];
            this.setStackTrace(statusRuntimeException.getStackTrace());
        }
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "exceptionType=" + exceptionType +
                ", exceptionOccurredClass=" + exceptionOccurredClass +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", exceptionDescription='" + exceptionDescription + '\'' +
                '}';
    }


    //getter-setter:

    @NotNull
    public CustomExceptionTypeEnum getExceptionType() {
        return exceptionType;
    }

    public void setExceptionType(@NotNull CustomExceptionTypeEnum exceptionType) {
        this.exceptionType = exceptionType;
    }

    @NotNull
    public Class getExceptionOccurredClass() {
        return exceptionOccurredClass;
    }

    public void setExceptionOccurredClass(@NotNull Class exceptionOccurredClass) {
        this.exceptionOccurredClass = exceptionOccurredClass;
    }

    @NotNull
    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(@NotNull String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionDescription() {
        return exceptionDescription;
    }

    public void setExceptionDescription(String exceptionDescription) {
        this.exceptionDescription = exceptionDescription;
    }
}
