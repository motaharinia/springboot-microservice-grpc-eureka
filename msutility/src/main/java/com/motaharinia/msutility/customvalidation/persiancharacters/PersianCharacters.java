package com.motaharinia.msutility.customvalidation.persiancharacters;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی رشته با کارکترهای پارسی و حرف فاصله <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PersianCharactersValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface PersianCharacters {
    
    String message() default "customValidation.persianCharacters";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
    
}
