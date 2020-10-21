package com.motaharinia.msutility.customvalidation.listlength;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی محدوده اندازه لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
public class ListLengthValidator implements ConstraintValidator<ListLength, List> {

    private String message;
    private Integer min;
    private Integer max;
    private Integer exact;

    @Override
    public void initialize(ListLength a) {
        min = a.min();
        max = a.max();
        exact = a.exact();
        message = a.message();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(list)) {
            return true;
        }
        boolean result = true;
        if (exact > 0) {
            if (!exact.equals(list.size())) {
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
            } else if (min > 0 && list.size() < min) {
                result = false;
                message += "[min=" + min + "]";
            } else if (max > 0 && list.size() > max) {
                result = false;
                message += "[max=" + max + "]";
            }
        }
        cvc.disableDefaultConstraintViolation();
        cvc.buildConstraintViolationWithTemplate(message).addConstraintViolation();
        return result;
    }

}
