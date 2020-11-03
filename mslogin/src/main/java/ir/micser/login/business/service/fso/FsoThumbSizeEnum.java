
package ir.micser.login.business.service.fso;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:مقادیر ثابت اندازه تصویر بندانگشتی تصویر
 *
 */
public enum FsoThumbSizeEnum {

    /**
     * اندازه تصویر بندانگشتی 60 در 60 پیکسل
     */
    THUMB_60("60"),
    /**
     * اندازه تصویر بندانگشتی 120 در 120 پیکسل
     */
    THUMB_120("120");

    private final String value;

    FsoThumbSizeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
