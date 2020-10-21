package ir.micser.statemachine.business.service.state;

public enum EntityNameEnum {
    /**
     *  انتیتی adminUser
     */
    ADMIN_USER("ADMIN_USER"),
    /**
     * انتیتی cityPlace
     */
    CITY_PLACE("CITY_PLACE");

    private final String value;

    private EntityNameEnum(String value) {
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
