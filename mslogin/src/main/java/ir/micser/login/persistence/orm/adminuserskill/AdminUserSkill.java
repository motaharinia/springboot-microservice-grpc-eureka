package ir.micser.login.persistence.orm.adminuserskill;


import com.motaharinia.msjpautility.entity.GenericEntity;
import ir.micser.login.persistence.orm.adminuser.AdminUser;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس انتیتی مهارت ادمین
 */
@Entity
@Table(name = "admin_user_skill")
public class AdminUserSkill extends GenericEntity implements Serializable {
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
    @Field
    @Column(name = "title")
    private String title;


    /**
     * ادمین های متصل به این مهارت
     */
    @ContainedIn
    @ManyToMany(mappedBy = "skillSet", fetch = FetchType.LAZY)
    private Collection<AdminUser> userCollection;

    @Override
    public String toString() {
        return "AdminUserJob{" +
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

    public Collection<AdminUser> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<AdminUser> adminUserCollection) {
        this.userCollection = adminUserCollection;
    }
}
