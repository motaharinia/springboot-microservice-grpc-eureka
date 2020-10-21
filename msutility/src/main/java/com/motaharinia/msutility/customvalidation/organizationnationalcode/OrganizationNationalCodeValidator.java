package com.motaharinia.msutility.customvalidation.organizationnationalcode;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی کد ملی سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class OrganizationNationalCodeValidator implements ConstraintValidator<OrganizationNationalCode, String>  {

    @Override
    public void initialize(OrganizationNationalCode a) {
        
    }

    @Override
    public boolean isValid(String nationalCode, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(nationalCode)) {
            return true;
        }
        
        String pattern = "[0-9]{11}";
        return nationalCode.matches(pattern);
    }
    
}
