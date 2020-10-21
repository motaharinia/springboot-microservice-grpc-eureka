package com.motaharinia.msutility.customvalidation.stringpattern;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی الگوی رشته ها<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class StringPatternValidator implements ConstraintValidator<StringPattern, String> {
    
    private String pattern;

    @Override
    public void initialize(StringPattern a) {
        pattern = a.pattern();
        
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(string)) {
            return true;
        }
        
        return string.matches(pattern);
    }
    
}
