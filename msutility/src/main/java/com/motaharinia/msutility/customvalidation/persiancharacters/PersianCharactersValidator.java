package com.motaharinia.msutility.customvalidation.persiancharacters;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی رشته با کارکترهای پارسی و حرف فاصله <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class PersianCharactersValidator implements ConstraintValidator<PersianCharacters, String> {

    @Override
    public void initialize(PersianCharacters a) {
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(string)) {
            return true;
        }
        //Use only persian characters and space
        String pattern = "^[(\\u0600-\\u06FF)|(\\u0020)]+$";
        return string.matches(pattern);

    }

}
