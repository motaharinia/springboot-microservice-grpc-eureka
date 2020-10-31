package ir.micser.geo.business.service.cityplace;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.motaharinia.msjpautility.search.SearchRowView;
import com.motaharinia.msjpautility.search.annotation.SearchDataColumn;
import ir.micser.geo.persistence.orm.cityplace.CityPlace;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  اینترفیس گرید لوکیشن شهری
 */
@JsonDeserialize(as = CityPlace.class)
public interface CityPlaceSearchViewTypeBrief extends SearchRowView {

    @SearchDataColumn(index = 1, name = "id")
    Integer getId();

    @SearchDataColumn(index = 2, name = "title")
    String getTitle();

    @SearchDataColumn(index = 3, name = "latitude")
     String getLatitude();

    @SearchDataColumn(index = 4, name = "longitude")
     String getLongitude();

    @SearchDataColumn(index = 5, name = "admin_user_id")
    Integer getAdminUserId();

    @SearchDataColumn(index = -1,name = "city_id")
    City getCity();

    interface City {
        @SearchDataColumn(index = 6,name = "cityId")
        String getId();
    }

    @Override
    default String toOut() {
        return this.getId() + "," + this.getTitle()+ "," + this.getTitle()+ "," + this.getLatitude()+ "," + this.getLongitude()+ "," + this.getAdminUserId()+ "," + this.getCity().getId();
    }
}
