package ir.micser.statemachine.business.service.state;

public enum GRPCNameEnum {

    MS_GEO("MS_GEO");

    private final String value;

    private GRPCNameEnum(String value) {
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
