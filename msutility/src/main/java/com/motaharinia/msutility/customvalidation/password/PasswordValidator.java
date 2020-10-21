package com.motaharinia.msutility.customvalidation.password;

import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * کلاس بررسی کننده انوتیشن اعتبارسنجی رمز عبور با تعیین حداقل و یا پیچیده بودن رمز <br>
 * فقط برای فیلدهای از نوع String میتوان از این اعتبارسنجی استفاده کرد
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private String message;
    private Integer minLength;
    private Boolean complicated;

    @Override
    public void initialize(Password a) {
        minLength = a.minLength();
        complicated = a.complicated();
        message=a.message();
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext cvc) {
        if (ObjectUtils.isEmpty(password)) {
            return true;
        }

        if (minLength <= 0) {
            return false;
        }

        boolean result = true;
        if (password.length() < minLength) {
            message += "[minLength=" + minLength + "]";
            cvc.disableDefaultConstraintViolation();
            cvc.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        if (complicated.equals(true)) {
            if (!validateComplicatedPassword(password)) {
                message += "[complicated=true]";
                cvc.disableDefaultConstraintViolation();
                cvc.buildConstraintViolationWithTemplate(message).addConstraintViolation();
                result = false;
            }
        }

        return result;

    }

    private static boolean validateComplicatedPassword(String password) {
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasLowercase = !password.equals(password.toUpperCase());
        char[] passwordLetters = password.toCharArray();
        return hasUppercase && hasLowercase && hasPunctuation(passwordLetters) && hasDigit(passwordLetters);

    }

    private static boolean hasPunctuation(char[] passwordLetters) {
        boolean result = false;
        String[] punctuations = new String[]{"!", "?", "'", ".", "(", ")", "-", ":", ";", ",", "\"", "%", "@", "$", "^", "&", "*", "+", "="};
        for (int i = 0; i < passwordLetters.length; i++) {
            if (Arrays.asList(punctuations).contains(passwordLetters[i] + "")) {
                result = true;
                break;
            }
        }
        return result;
    }

    private static boolean hasDigit(char[] passwordLetters) {
        boolean result = false;
        String[] numbers = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        for (int i = 0; i < passwordLetters.length; i++) {
            if (Arrays.asList(numbers).contains(passwordLetters[i] + "")) {
                result = true;
                break;
            }
        }
        return result;
    }

}
