package com.motaharinia.msutility.entity.sample.entity;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 * کلاس انتیتی شهرستان
 */
public class EntityEparchy {
    /**
     * شناسه
     */
    private Integer id;
    /**
     * نام
     */
    private String eName;

    @Override
    public String toString() {
        return "EntityEparchy{" + "id=" + id + ", eName=" + eName + '}';
    }


    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }


}
