package com.motaharinia.msutility.customvalidation.password;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی رمز عبور با تعیین حداقل و یا پیچیده بودن رمز <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "customValidation.password";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * حداقل طول رمز عبور
     * @return خروجی:
     */
    int minLength();

    /**
     * بررسی رمز عبور پیچیده انجام شود؟
     * @return خروجی:
     */
    boolean complicated() default false;

}
