package ir.micser.geo.presentation.cityplace;

import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس مدل لوکیشن شهری ادمین
 */
public class CityPlaceModel implements Serializable {
    /**
     * شناسه
     */
    private Integer id;

    /**
     * عنوان
     */
    private String title;

    /**
     * عرض جغرافیایی لوکیشن شهری
     */
    private String latitude;

    /**
     * طول جغرافیایی لوکیشن شهری
     */
    private String longitude;

    /**
     * شناسه شهر
     */
    private Integer city_id;

    /**
     * شناسه ادمین
     */
    private Integer adminUserId;


    @Override
    public String toString() {
        return "CityPlaceModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", city_id=" + city_id +
                ", adminUserId=" + adminUserId +
                '}';
    }

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }
}
