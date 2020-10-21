package com.motaharinia.msutility.customvalidation.organizationeconomiccode;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی کد اقتصادی سازمان <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class OrganizationEconomicCodeValidator implements ConstraintValidator<OrganizationEconomicCode, String> {

    @Override
    public void initialize(OrganizationEconomicCode a) {

    }

    @Override
    public boolean isValid(String economicCode, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(economicCode)) {
            return true;
        }

        String pattern = "[0-9]{14}";
        return economicCode.matches(pattern);
    }

}
