/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.micser.login.business.service.captchacode;

/**
 *
 * @author Administrator
 */
public enum CaptchCodeTypeEnum {
    USER_GIFT_CHECK_BY_LOGGED_IN_USER_ID("USER_GIFT_CHECK_BY_LOGGED_IN_USER_ID"),
    LOGIN_CHECK_BY_USERNAME("LOGIN_CHECK_BY_USERNAME");

    private final String value;

    private CaptchCodeTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
