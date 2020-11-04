package ir.micser.login.presentation.adminuser;

import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customvalidation.required.Required;
import com.motaharinia.msutility.fso.view.FileViewModel;
import io.leangen.graphql.annotations.GraphQLQuery;
import ir.micser.login.presentation.adminuserskill.AdminUserSkillModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس مدل ادمین
 */
public class AdminUserModel implements Serializable {
    /**
     * شناسه
     */
    @GraphQLQuery(name = "id", description = "شناسه")
    private Integer id;

    /**
     * نام کاربری
     */
    @GraphQLQuery(name = "username", description = "نام کاربری")
    private String username;

    /**
     * رمز عبور
     */
    @GraphQLQuery(name = "password", description = "رمز عبور")
    private String password;

    /**
     * نام
     */
    @GraphQLQuery(name = "firstName", description = "نام")
    private String firstName;

    /**
     * نام خانوادگی
     */
    @GraphQLQuery(name = "lastName", description = "نام خانوادگی")
    private String lastName;

    /**
     * تاریخ تولد
     */
    @GraphQLQuery(name = "dateOfBirth", description = "نام خانوادگی")
    private CustomDate dateOfBirth;


    /**
     * جنسیت
     */
    @GraphQLQuery(name = "gender_id", description = "جنسیت- الزامی است")
    @Required
    private Integer gender_id;

    /**
     * شناسه شهر اطلاعات تماس پیش فرض
     */
    @GraphQLQuery(name = "defaultAdminUserContact_city_id", description = " شناسه شهر اطلاعات تماس پیش فرض")
    private Integer defaultAdminUserContact_city_id;

    /**
     * نشانی اطلاعات تماس پیش فرض
     */
    @GraphQLQuery(name = "defaultAdminUserContact_address", description = "نشانی اطلاعات تماس پیش فرض")
    private String defaultAdminUserContact_address;
    /**
     * لیست مهارتها
     */
    @GraphQLQuery(name = "skillList", description = "لیست مهارتها")
    private List<AdminUserSkillModel> skillList = new ArrayList<>();

    //تصاویر پروفایل
    @GraphQLQuery(name = "imageFileList", description = "تصاویر پروفایل")
    private List<FileViewModel> imageFileList = new ArrayList<>();

    @Override
    public String toString() {
        return "AdminUserModel{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", defaultAdminUserContact_address='" + defaultAdminUserContact_address + '\'' +
                ", skillList=[" + skillList.stream().map(item -> item.toString()).collect(Collectors.joining(",")) + "]" +
                '}';
    }


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

    public String getDefaultAdminUserContact_address() {
        return defaultAdminUserContact_address;
    }

    public void setDefaultAdminUserContact_address(String defaultAdminUserContact_address) {
        this.defaultAdminUserContact_address = defaultAdminUserContact_address;
    }


    public List<AdminUserSkillModel> getSkillList() {
        return skillList;
    }

    public void setSkillList(List<AdminUserSkillModel> skillList) {
        this.skillList = skillList;
    }

    public CustomDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(CustomDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getGender_id() {
        return gender_id;
    }

    public void setGender_id(Integer gender_id) {
        this.gender_id = gender_id;
    }

    public Integer getDefaultAdminUserContact_city_id() {
        return defaultAdminUserContact_city_id;
    }

    public void setDefaultAdminUserContact_city_id(Integer defaultAdminUserContact_city_id) {
        this.defaultAdminUserContact_city_id = defaultAdminUserContact_city_id;
    }

    public List<FileViewModel> getImageFileList() {
        return imageFileList;
    }

    public void setImageFileList(List<FileViewModel> imageFileList) {
        this.imageFileList = imageFileList;
    }
}
