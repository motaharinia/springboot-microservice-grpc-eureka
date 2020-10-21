package ir.micser.geo.business.service.cityplace;

import ir.micser.geo.business.service.city.CitySearchViewTypeBrief;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 *     مقادیر ثابت نوع جستجو<br>
 * مقادیر ثابت نوع جستجوی ماژولهای دیگر از این کلاس اکستند میشوند
 */
public enum CityPlaceSearchViewTypeEnum {
    /**
     *نوع جستجوی خلاصه<br>
     */
    CITY_PLACE_BRIEF(CitySearchViewTypeBrief.class.getName()),
    ;

    private final String value;

    CityPlaceSearchViewTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String toString() {
        return super.toString();
    }
}
