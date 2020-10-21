package com.motaharinia.msutility.customvalidation.stringlength;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی محدوده و تعداد دقیق طول رشته ها<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class StringLengthValidator implements ConstraintValidator<StringLength, String> {

    private String message;
    private Integer min;
    private Integer max;
    private Integer exact;

    @Override
    public void initialize(StringLength a) {
        min = a.min();
        max = a.max();
        exact = a.exact();
        message = a.message();
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(string)) {
            return true;
        }


        boolean result = true;
        if (exact > 0) {
            if (!exact.equals(string.length())) {
                result = false;
                message += "[exact=" + exact + "]";
            }
        } else {
            if (min <= 0 && max <= 0) {
                result = false;
                message += "[min<=0 || max<=0]";
            } else if (min > 0 && max > 0 && min > max) {
                result = false;
                message += "[min>max]";
            } else if (min > 0 && string.length() < min) {
                result = false;
                message += "[min=" + min + "]";
            } else if (max > 0 && string.length() > max) {
                result = false;
                message += "[max=" + max + "]";
            }
        }

        cvc.disableDefaultConstraintViolation();
        cvc.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return result;
    }


}
