package ir.micser.login.persistence.orm.adminuser;


import com.motaharinia.msjpautility.entity.GenericEntity;
import com.motaharinia.msjpautility.entity.OracleUtility;
import ir.micser.login.persistence.orm.adminusercontact.AdminUserContact;
import ir.micser.login.persistence.orm.adminuserskill.AdminUserSkill;
import ir.micser.login.persistence.orm.etcitem.EtcItem;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.SortableField;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس انتیتی ادمین
 */
@Entity
@Table(name = "admin_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"})})
@Indexed
public class AdminUser extends GenericEntity implements Serializable {
    /**
     * شناسه
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * نام کاربری
     */
    @Column(name = "username")
    private String username;

    /**
     * رمز عبور
     */
    @Column(name = "password")
    private String password;

    /**
     * نام
     */
    @Field
    @SortableField
    @Column(name = "first_name")
    private String firstName;

    /**
     * نام خانوادگی
     */
    @Field
    @Column(name = "last_name")
    private String lastName;


    /**
     *تاریخ تولد
     */
    @Column(name = "date_of_birth", columnDefinition = OracleUtility.COLUMN_DEFINITION_DATE)
    private Date dateOfBirth;


    /**
     * اطلاعات تماس پیش فرض
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "default_admin_user_contact_id", referencedColumnName = "id")
    private AdminUserContact defaultAdminUserContact;

    /**
     *جنسیت
     */
    @IndexedEmbedded(includeEmbeddedObjectId = true, includePaths = {"id"})
    @JoinColumn(name = "gender_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private EtcItem gender;

    /**
     * لیست مهارتها
     */
    @IndexedEmbedded(includeEmbeddedObjectId = true, includePaths = {"id","title"})
    @JoinTable(name = "admin_user_jt_admin_user_skill", joinColumns = {
            @JoinColumn(name = "admin_user_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "admin_user_skill_id", referencedColumnName = "id")})
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<AdminUserSkill> skillSet=new HashSet<>();

    /**
     * تعداد دفعات ورود رمز اشتباه برای کپچا
     */
    @Column(name = "login_failure_count")
    private Integer loginFailureCount;

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AdminUserContact getDefaultAdminUserContact() {
        return defaultAdminUserContact;
    }

    public void setDefaultAdminUserContact(AdminUserContact defaultAdminUserContact) {
        this.defaultAdminUserContact = defaultAdminUserContact;
    }

    public Set<AdminUserSkill> getSkillSet() {
        return skillSet;
    }

    public void setSkillSet(Set<AdminUserSkill> skillCollection) {
        this.skillSet = skillCollection;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EtcItem getGender() {
        return gender;
    }

    public void setGender(EtcItem gender) {
        this.gender = gender;
    }

    public Integer getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(Integer loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }
}
