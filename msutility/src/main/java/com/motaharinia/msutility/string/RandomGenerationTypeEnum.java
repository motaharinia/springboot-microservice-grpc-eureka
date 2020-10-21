package com.motaharinia.msutility.string;


/**
 * Created by IntelliJ IDEA.
 * User: https://github.com/motaharinia
 * Date: 2020-06-11
 * Time: 01:22:13
 * Description: مقادیر ثابت نوع ترکیب رشته تصادفی
 */
public enum RandomGenerationTypeEnum {

    /**
     *ترکیبی از حروف الفبا
     */
    CHARACTER_ALL("CHARACTER_ALL"),
    /**
     *ترکیبی از حروف الفبای کوچک
     */
    CHARACTER_LOWER("CHARACTER_LOWER"),
    /**
     * ترکیبی از حروف الفبای بزرگ
     */
    CHARACTER_UPPER("CHARACTER_UPPER"),
    /**
     * ترکیبی از اعداد
     */
    NUMBER("NUMBER"),
    /**
     * ترکیبی از حروف الفبا و اعداد
     */
    CHARACTER_NUMBER("CHARACTER_NUMBER"),
    /**
     *ترکیبی از علائم نگارشی مانند کاما و ...
     */
    PUNCTUATION("PUNCTUATION"),
    /**
     * ترکیبی از حروف الفبا و اعداد و علائم نگارشی
     */
    CHARACTER_NUMBER_PUNCTUATION("CHARACTER_NUMBER_PUNCTUATION"),
    /**
     * ترکیبی از حروف الفبا و اعداد و آندرلاین
     */
    CHARACTER_NUMBER_UNDERLINE("CHARACTER_NUMBER_UNDERLINE"),
    /**
     *  ترکیبی از اعداد و آندرلاین
     */
    NUMBER_UNDERLINE("CHARACTER_UPPER")
    ;
    private final String value;

    private RandomGenerationTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return super.toString();
    }
}
