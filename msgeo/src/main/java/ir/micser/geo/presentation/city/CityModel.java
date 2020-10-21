package ir.micser.geo.presentation.city;

import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس مدل شهر
 */
public class CityModel implements Serializable {
    /**
     * شناسه
     */
    private Integer id;

    /**
     * عنوان
     */
    private String title;


    @Override
    public String toString() {
        return "CityModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
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
}
