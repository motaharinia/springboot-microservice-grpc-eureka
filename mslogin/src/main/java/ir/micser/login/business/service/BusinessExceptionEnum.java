package ir.micser.login.business.service;

import com.motaharinia.msutility.customexception.CustomExceptionKey;

public enum BusinessExceptionEnum implements CustomExceptionKey {
    ID_NOT_FOUND("BUSINESS_EXCEPTION.ID_NOT_FOUND"),
    DUPLICATE_EMAIL("BUSINESS_EXCEPTION.DUPLICATE_EMAIL"),
    INVALID_FILE_KEY("BUSINESS_EXCEPTION.INVALID_FILE_KEY"),
    INVALID_ID("BUSINESS_EXCEPTION.INVALID_ID");

    private String messageKey;

    BusinessExceptionEnum(String value) {
        this.messageKey = value;
    }

    public String getValue() {
        return messageKey;
    }

}
