package com.motaharinia.msutility.customvalidation.daterange;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی محدوده تاریخ<br>
 * فقط برای فیلدهای از نوع CustomDate میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = DateRangeValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateRange {
    
    String message() default "customValidation.dateRange";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /**
     * تاریخ جلالی ابتدای محدوده زمانی<br>
     * فرمتها<br>
     *YYYY-MM-DD <br>
     * unlimited <br>
     * today <br>
     * @return خروجی: ابتدای محدوده زمانی
     */
    String from() default  "unlimited";

    /**
     * تاریخ جلالی انتهای محدوده زمانی<br>
     * فرمتها<br>
     *YYYY-MM-DD <br>
     * unlimited <br>
     * today <br>
     * @return خروجی: انتهای محدوده زمانی
     */
    String to() default "unlimited";
    
}
