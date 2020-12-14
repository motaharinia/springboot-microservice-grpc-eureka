package ir.micser.login.business.service.authorization;


import com.motaharinia.msutility.customexception.CustomExceptionKey;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *    مقادیر ثابت خطاهای مجوز دسترسی که در کلاینت ساید پروژه ترجمه آنها طبق زبان انتخاب شده کاربر نمایش داده خواهد شد
 */
public enum AuthorizationBusinessExceptionKeyEnum implements CustomExceptionKey {


    /**
     *نشانی وب دریافتی خالی است
     */
    URL_IS_EMPTY("URL_IS_EMPTY"),

;

    private final String value;
    private final String moduleName = "async";

    AuthorizationBusinessExceptionKeyEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return moduleName + "." + value;
    }

    public String toString() {
        return super.toString();
    }
}
