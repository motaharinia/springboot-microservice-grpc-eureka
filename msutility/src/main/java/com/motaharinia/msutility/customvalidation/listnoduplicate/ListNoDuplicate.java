package com.motaharinia.msutility.customvalidation.listnoduplicate;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی عدم وجود عنصر تکراری در لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = ListNoDuplicateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ListNoDuplicate {

    String message() default "customValidation.listNoDuplicate";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
