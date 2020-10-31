package com.motaharinia.msjpautility.entity.sample.entity;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     کلاس انتیتی آ
 */
public class EntityA {

    /**
     * شناسه
     */
    private int id;
    /**
     * شهر
     */
    private EntityCity entityCity;
    /**
     * تایید
     */
    private Boolean isOk;


    @Override
    public String toString() {
        return "EntityA{" + "id=" + id + ", entityCity=" + entityCity + ", isOk=" + isOk + '}';
    }


    //getter-setter:
    public EntityCity getEntityCity() {
        return entityCity;
    }

    public void setEntityCity(EntityCity entityCity) {
        this.entityCity = entityCity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(Boolean isOk) {
        this.isOk = isOk;
    }


}
