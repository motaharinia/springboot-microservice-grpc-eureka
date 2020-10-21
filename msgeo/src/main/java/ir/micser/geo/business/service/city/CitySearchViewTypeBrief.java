package ir.micser.geo.business.service.city;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motaharinia.msutility.search.SearchRowView;
import com.motaharinia.msutility.search.annotation.SearchDataColumn;
import ir.micser.geo.persistence.orm.city.City;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  اینترفیس گرید شهر ها
 */
@JsonDeserialize(as = City.class)
public interface CitySearchViewTypeBrief extends SearchRowView {

    @SearchDataColumn(index = 1, name = "id")
    Integer getId();


    @SearchDataColumn(index = 2, name = "title")
    String getTitle();


    @Override
    default String toOut() {
        return this.getId() + "," + this.getTitle();
    }
}
