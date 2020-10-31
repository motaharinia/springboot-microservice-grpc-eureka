package com.motaharinia.msjpautility.entity.sample.entity;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 * کلاس انتیتی تحصیلات
 */
public class EntityEducation {

    /**
     * شناسه
     */
    private Integer id;
    /**
     * سطح تحصیلات
     */
    private String level;

    @Override
    public String toString() {
        return "EntityEducation{" + "id=" + id + ", level=" + level + '}';
    }


    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }


}
