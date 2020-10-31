package ir.micser.geo.persistence.orm.city;


import com.motaharinia.msjpautility.entity.GenericEntity;

import javax.persistence.*;
import java.io.Serializable;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس انتیتی شهر
 */
@Entity
@Table(name = "city", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"title"})})
public class City extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * عنوان
     */
    @Column(name = "title")
    private String title;


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


}
