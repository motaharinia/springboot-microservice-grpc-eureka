package com.motaharinia.msutility.customvalidation.organizationregistrationno;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی شماره ثبت سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class OrganizationRegistrationNoValidator implements ConstraintValidator<OrganizationRegistrationNo, String> {

    @Override
    public void initialize(OrganizationRegistrationNo a) {
        
    }

    @Override
    public boolean isValid(String registrationNo, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(registrationNo)) {
            return true;
        }
        
        String pattern = "[0-9]+";
        return registrationNo.matches(pattern);
    }
    
}
