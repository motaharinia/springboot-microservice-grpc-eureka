package com.motaharinia.msutility.customvalidation.personphone;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 *     کلاس بررسی کننده انوتیشن اعتبارسنجی شماره تلفن منزل که میتواند 8 رقم باشد<br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class PersonPhoneValidator implements ConstraintValidator<PersonPhone, String>  {

    @Override
    public void initialize(PersonPhone a) {
        
    }

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(phone)) {
            return true;
        }

        String pattern = "[0-9]{8}";
        return phone.matches(pattern);
        
    }
  
    
}
