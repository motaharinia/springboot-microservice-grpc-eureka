package com.motaharinia.msjpautility.entity.sample.model;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     کلاس مدل آ
 */
public class ModelA {

    /**
     * شناسه
     */
    private Integer id;

    private String stringA;
    /**
     * نام شهرستان شهر
     */
    private String entityCity_entityEparchy_eName;
    /**
     * تایید
     */
    private Boolean isOk;

    /**
     * متد سازنده پیش فرض
     */
    public ModelA() {
    }

    /**
     * متد سازنده که با دریافت اطلاعات مدل را میسازد
     * @param id شناسه
     * @param stringA رشته آ
     * @param entityCity_entityEparchy_eName نام شهرستان شهر
     * @param isOk تایید
     */
    public ModelA(Integer id, String stringA, String entityCity_entityEparchy_eName, Boolean isOk) {
        this.id = id;
        this.stringA = stringA;
        this.entityCity_entityEparchy_eName = entityCity_entityEparchy_eName;
        this.isOk = isOk;
    }

    @Override
    public String toString() {
        return "ModelA{" + "id=" + id + ", stringA=" + stringA + ", entityCity_entityEparchy_eName=" + entityCity_entityEparchy_eName + ", isOk=" + isOk + '}';
    }

    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStringA() {
        return stringA;
    }

    public void setStringA(String StringA) {
        this.stringA = StringA;
    }

    public String getEntityCity_entityEparchy_eName() {
        return entityCity_entityEparchy_eName;
    }

    public void setEntityCity_entityEparchy_eName(String entityCity_entityEparchy_eName) {
        this.entityCity_entityEparchy_eName = entityCity_entityEparchy_eName;
    }

    public Boolean getIsOk() {
        return isOk;
    }

    public void setIsOk(Boolean isOk) {
        this.isOk = isOk;
    }
}
