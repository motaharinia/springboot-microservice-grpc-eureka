package com.motaharinia.msutility.customvalidation.integerrange;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 *     کلاس بررسی کننده انوتیشن اعتبارسنجی محدوده اعداد صحیح<br>
 * فقط برای فیلدهای از نوع Integer میتوان از این اعتبارسنجی استفاده کرد
 */
public class IntegerRangeValidator implements ConstraintValidator<IntegerRange, Integer> {

    private String message;
    private Integer min;
    private Integer max;

    @Override
    public void initialize(IntegerRange a) {
        min = a.min();
        max = a.max();
        message=a.message();
    }

    @Override
    public boolean isValid(Integer number, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(number)) {
            return true;
        }

        boolean result = true;

        if (number < min) {
            result = false;
            message += "[min=" + min + "]";
        } else if (number > max) {
            result = false;
            message += "[max=" + max + "]";
        }

        cvc.disableDefaultConstraintViolation();
        cvc.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return result;
    }


}
