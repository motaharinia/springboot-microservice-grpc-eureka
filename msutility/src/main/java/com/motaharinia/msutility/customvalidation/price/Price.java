package com.motaharinia.msutility.customvalidation.price;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی قیمت (عدد بالای صفر و بدون مقدار اعشار) <br>
 * فقط برای فیلدهای از نوع BigDecimal میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = PriceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    
    String message() default "customValidation.price";
      
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
    
}
