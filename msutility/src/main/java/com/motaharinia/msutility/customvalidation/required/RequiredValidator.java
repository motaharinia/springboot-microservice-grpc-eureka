package com.motaharinia.msutility.customvalidation.required;

import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customfield.CustomDateTime;
import com.motaharinia.msutility.customfield.CustomHtmlString;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی الزامی بودن فیلد <br>
 * برای همه فیلدها میتوان از این اعتبارسنجی استفاده کرد
 */
public class RequiredValidator implements ConstraintValidator<Required, Object> {

    @Override
    public void initialize(Required a) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext cvc) {

        //بررسی نال بودن شیی یا خالی بودن شیی اگر نوع آن موارد زیر باشد
        //Optional , Array , CharSequence , Collection , Map
        if (ObjectUtils.isEmpty(obj)) {
            return false;
        }

        //بررسی نال یا خالی بودن شیی اگر نوع آن CustomDate باشد
        if ((obj instanceof CustomDate) && CustomDate.isEmpty((CustomDate) obj)) {
            return false;
        }

        //بررسی نال یا خالی بودن شیی اگر نوع آن CustomDateTime باشد
        if ((obj instanceof CustomDateTime) && (CustomDateTime.isEmpty((CustomDateTime) obj))) {
            return false;
        }

        //بررسی نال یا خالی بودن شیی اگر نوع آن CustomHtmlString باشد
        if ((obj instanceof CustomHtmlString) && (CustomHtmlString.isEmpty((CustomHtmlString) obj))) {
            return false;
        }
        return true;
    }
}
