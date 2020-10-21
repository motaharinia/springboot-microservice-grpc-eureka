package ir.micser.geo.business.service.city;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 *     مقادیر ثابت نوع جستجو<br>
 * مقادیر ثابت نوع جستجوی ماژولهای دیگر از این کلاس اکستند میشوند
 */
public enum CitySearchViewTypeEnum {
    /**
     *نوع جستجوی خلاصه<br>
     */
    CITY_BRIEF(CitySearchViewTypeBrief.class.getName()),
    ;


    private final String value;

    CitySearchViewTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return super.toString();
    }
}
