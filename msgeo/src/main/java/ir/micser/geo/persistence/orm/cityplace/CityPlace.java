package ir.micser.geo.persistence.orm.cityplace;


import com.motaharinia.msjpautility.entity.GenericEntity;
import ir.micser.geo.persistence.orm.city.City;

import javax.persistence.*;
import java.io.Serializable;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس انتیتی لوکیشن شهری<br>
 * هر ادمین در هر شهر میتواند یک یا چند لوکیشن شهری برای خودش ثبت نماید
 */
@Entity
@Table(name = "city_place")
public class CityPlace extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * عنوان لوکیشن شهری
     */
    @Column(name = "title")
    private String title;

    /**
     * عرض جغرافیایی لوکیشن شهری
     */
    @Column(name = "latitude")
    private String latitude;

    /**
     * طول جغرافیایی لوکیشن شهری
     */
    @Column(name = "longitude")
    private String longitude;

    /**
     * شهر
     */
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    /**
     * شناسه ادمین
     *
     * @MSFK
     */
    @Column(name = "admin_user_id")
    private Integer adminUserId;

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

    public void setTitle(String username) {
        this.title = username;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
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
}
