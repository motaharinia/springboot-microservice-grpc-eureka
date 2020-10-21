package com.motaharinia.msutility.customvalidation.listnoduplicate;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی عدم وجود عنصر تکراری در لیست<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
public class ListNoDuplicateValidator implements ConstraintValidator<ListNoDuplicate, List> {

    @Override
    public void initialize(ListNoDuplicate a) {
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(list)) {
            return true;
        }
        boolean result = true;
        Set set = new HashSet(list);
        if (set.size() < list.size()) {
            result = false;
        }
        return result;
    }

}
