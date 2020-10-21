package com.motaharinia.msutility.customvalidation.required;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی الزامی بودن فیلد <br>
 * برای همه فیلدها میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = RequiredValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
    
    String message() default "customValidation.required";
      
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
    
}
