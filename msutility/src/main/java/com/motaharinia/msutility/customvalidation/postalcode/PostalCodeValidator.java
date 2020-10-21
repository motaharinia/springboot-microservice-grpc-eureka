package com.motaharinia.msutility.customvalidation.postalcode;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی کد پستی <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class PostalCodeValidator implements ConstraintValidator<PostalCode, String> {

    @Override
    public void initialize(PostalCode a) {
        
    }

    @Override
    public boolean isValid(String postalCode, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(postalCode)) {
            return true;
        }
        
        String pattern = "[0-9]{10}";
        return postalCode.matches(pattern);
    }
    
}
