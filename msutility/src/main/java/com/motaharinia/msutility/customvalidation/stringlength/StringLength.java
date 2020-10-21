package com.motaharinia.msutility.customvalidation.stringlength;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی محدوده و تعداد دقیق طول رشته ها<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = StringLengthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface StringLength {
    
    String message() default "customValidation.stringLength";
      
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};

    /**
     * حداقل طول رشته. پیش فرض غیر فعال است
     * @return خروجی:
     */
    int min() default 0;

    /**
     * حداکثر طول رشته. پیش فرض غیر فعال است
     * @return خروجی:
     */
    int max() default 0;

    /**
     *  طول دقیق رشته. پیش فرض غیر فعال است
     * @return خروجی:
     */
    int exact() default 0;
    
}
