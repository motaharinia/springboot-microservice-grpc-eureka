package ir.micser.login.business.service;

public enum BusinessExceptionEnum {
    ID_NOT_FOUND("BUSINESS_EXCEPTION.ID_NOT_FOUND"),
    DUPLICATE_EMAIL("BUSINESS_EXCEPTION.DUPLICATE_EMAIL");

    private String messageKey;

    BusinessExceptionEnum(String value) {
        this.messageKey = value;
    }

    public String getValue() {
        return messageKey;
    }

}
