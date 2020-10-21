package com.motaharinia.msutility.genericmodel;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس نوع کامبو در کلاینت است<br>
 */
public enum ComboTypeEnum {
    /**
     * نوع کامبو
     * در این نوع کاربر کلاینت در ابتدا تمام گزینه های ممکن را در کامبو میبیند و میتواند یکی از آنها را انتخاب نماید
     */
    COMBO("COMBO"),
    /**
     * نوع اتوکامپلیت
     * در این نوع کاربر کلاینت در ابتدا گزینه ای نمیبیند و با تایپ بیش از دو حرف میتواند نتیجه جستجو را به عنوان گزینه انتخابی ببیند و یکی از آنها را انتخاب نماید
     */
    AUTOCOMPLETE("AUTOCOMPLETE");

    private final String value;

    ComboTypeEnum(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
