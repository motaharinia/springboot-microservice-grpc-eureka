package com.motaharinia.msutility.customvalidation.listlength;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی محدوده اندازه لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = ListLengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ListLength {
    
    String message() default "customValidation.listLength";
      
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
    
    int min() default 0;
    
    int max() default 0;
    
    int exact() default 0;
    
}
