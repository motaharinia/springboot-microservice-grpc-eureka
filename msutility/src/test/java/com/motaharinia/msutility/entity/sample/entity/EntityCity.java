package com.motaharinia.msutility.entity.sample.entity;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     کلاس انتیتی شهر
 */
public class EntityCity {

    /**
     * شناسه
     */
    private Integer id;
    /**
     * نام
     */
    private String name;
    /**
     * شهرستان
     */
    private EntityEparchy entityEparchy;


    @Override
    public String toString() {
        return "EntityCity{" + "id=" + id + ", name=" + name + ", entityEparchy=" + entityEparchy + '}';
    }


    //getter-setter:

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityEparchy getEntityEparchy() {
        return entityEparchy;
    }

    public void setEntityEparchy(EntityEparchy entityEparchy) {
        this.entityEparchy = entityEparchy;
    }

}
