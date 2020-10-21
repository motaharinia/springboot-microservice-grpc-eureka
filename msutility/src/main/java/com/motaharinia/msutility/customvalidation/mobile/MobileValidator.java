package com.motaharinia.msutility.customvalidation.mobile;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی تلفن همراه <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public void initialize(Mobile a) {

    }

    @Override
    public boolean isValid(String mobile, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(mobile)) {
            return true;
        }

        String pattern = "^(09|\\+989)[0-9]{9}$";
        return mobile.matches(pattern);
    }


}
