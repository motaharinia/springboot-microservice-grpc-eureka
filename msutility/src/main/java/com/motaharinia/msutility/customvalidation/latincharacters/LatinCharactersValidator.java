package com.motaharinia.msutility.customvalidation.latincharacters;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی رشته با کارکترهای لاتین و حرف فاصله <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class LatinCharactersValidator implements ConstraintValidator<LatinCharacters, String> {

    @Override
    public void initialize(LatinCharacters a) {

    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if(ObjectUtils.isEmpty(string)){
            return true;
        }
        //Use only latin characters and space
        String pattern = "^[(a-z)|(A-Z)(\\u0020)]+$";
        return string.matches(pattern);
    }

}
