package com.motaharinia.msutility.customvalidation.price;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی قیمت (عدد بالای صفر و بدون مقدار اعشار) <br>
 * فقط برای فیلدهای از نوع BigDecimal میتوان از این اعتبارسنجی استفاده کرد
 */
public class PriceValidator implements ConstraintValidator<Price, BigDecimal>  {

    @Override
    public void initialize(Price a) {
        
    }

    @Override
    public boolean isValid(BigDecimal price, ConstraintValidatorContext cvc) {
        if(ObjectUtils.isEmpty(price)){
            return true;
        }
        //اگر عدد مثبت است و حاوی اعداد اعشار نیست
        if((price.compareTo(BigDecimal.ZERO) > 0) && (price.remainder(BigDecimal.ONE).compareTo(BigDecimal.ZERO)==0)){
            return true;
        }else{
            return false;
        }
    }
    
}
