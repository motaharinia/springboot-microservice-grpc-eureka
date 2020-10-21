package ir.micser.statemachine.business.service.state;

import ir.micser.login.business.service.etcitem.EtcItemEnum;

public enum ServiceNameEnum  {

    ADMIN_USER_SERVICE_IMP("ADMIN_USER_SERVICE_IMP");


    private final String value;

    private ServiceNameEnum(String value) {
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