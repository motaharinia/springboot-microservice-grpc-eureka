package com.motaharinia.msutility.customvalidation.companyphone;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 *     انوتیشن اعتبارسنجی شماره تلفن اداره و شرکت که میتواند بین 4 تا 8 رقم باشد<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = CompanyPhoneValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CompanyPhone {

    String message() default "customValidation.companyPhone";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
