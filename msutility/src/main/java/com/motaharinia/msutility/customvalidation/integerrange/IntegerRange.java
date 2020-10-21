package com.motaharinia.msutility.customvalidation.integerrange;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی محدوده اعداد صحیح<br>
 * فقط برای فیلدهای از نوع Integer میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = IntegerRangeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IntegerRange {

    String message() default "customValidation.integerRange";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * ابتدای محدوده عددی
     * @return خروجی:
     */
    int min() default -2147483648;

    /**
     * انتهای محدوده عددی
     * @return خروجی:
     */
    int max() default 2147483647;

}
