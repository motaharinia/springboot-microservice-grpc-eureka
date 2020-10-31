package ir.micser.login.business.service;

import com.motaharinia.msutility.customexception.CustomExceptionKey;

public enum BusinessExceptionEnum implements CustomExceptionKey {
    ID_NOT_FOUND("BUSINESS_EXCEPTION.ID_NOT_FOUND"),
    DUPLICATE_EMAIL("BUSINESS_EXCEPTION.DUPLICATE_EMAIL"),
    INVALID_FILE_KEY("BUSINESS_EXCEPTION.INVALID_FILE_KEY"),
    INVALID_ID("BUSINESS_EXCEPTION.INVALID_ID"),

    //خطاهای مربوط به آپلود فایل
    LOG_UPLOADED_FILE_HANDLE_ENTITY_ID_INVALID("LOG_UPLOADED_FILE_HANDLE_ENTITY_ID_INVALID"),
    LOG_UPLOADED_FILE_HANDLE_ACTION_ENUM_INVALID("LOG_UPLOADED_FILE_HANDLE_ACTION_ENUM_INVALID")
    ;

    private String messageKey;

    BusinessExceptionEnum(String value) {
        this.messageKey = value;
    }

    public String getValue() {
        return messageKey;
    }

}
