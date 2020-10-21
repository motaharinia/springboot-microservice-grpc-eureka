package ir.micser.statemachine.business.service.state;

import ir.micser.login.business.service.etcitem.EtcItemEnum;
import ir.micser.login.persistence.orm.etcitem.EtcItem;

public enum MethodTypeEnum  {
    /**
     * ثبت انتیتی
     */

    CREATE("CREATE");


    private final String value;

    private MethodTypeEnum(String value) {
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
