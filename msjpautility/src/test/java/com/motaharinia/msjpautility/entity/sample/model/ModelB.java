package com.motaharinia.msjpautility.entity.sample.model;


import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customfield.CustomHtmlString;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     کلاس مدل ب
 */
public class ModelB extends ModelA {

    /**
     * اعشار بی
     */
    private Double doubleB;
    /**
     * شناسه شهر
     */
    private int entityCity_id;
    /**
     * تاریخ تولد
     */
    private CustomDate dateOfBorn;
    /**
     * رشته اچ تی ام ال
     */
    private CustomHtmlString customHtmlString;

    /**
     * متد سازنده پیش فرض
     */
    public ModelB() {
    }

    /**
     * متد سازنده که با دریافت اطلاعات مدل را میسازد
     * @param id شناسه
     * @param stringA رشته آ
     * @param entityCity_entityEparchy_eName نام شهرستان شهر
     * @param doubleB اعشار بی
     * @param entityCity_id شناسه شهر
     */
    public ModelB(int id, String stringA, String entityCity_entityEparchy_eName, Double doubleB, int entityCity_id) {
        this.setId(id);
        this.setStringA(stringA);
        this.setEntityCity_entityEparchy_eName(entityCity_entityEparchy_eName);
        this.doubleB = doubleB;
        this.entityCity_id = entityCity_id;
    }

    @Override
    public String toString() {
        return "ModelB{" + "id=" + getId() + ", stringA=" + getStringA() + ", entityCity_entityEparchy_eName=" + getEntityCity_entityEparchy_eName() + ", doubleB=" + doubleB + ", entityCity_id=" + entityCity_id+ ", dateOfBorn=" + dateOfBorn+ ", htmlCustomString=" + customHtmlString + ", super.toString():=" + super.toString() + '}';
    }


    //getter-setter:
    public Double getDoubleB() {
        return doubleB;
    }

    public void setDoubleB(Double doubleB) {
        this.doubleB = doubleB;
    }

    public int getEntityCity_id() {
        return entityCity_id;
    }

    public void setEntityCity_id(int entityCity_id) {
        this.entityCity_id = entityCity_id;
    }

    public CustomDate getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(CustomDate dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public CustomHtmlString getCustomHtmlString() {
        return customHtmlString;
    }

    public void setCustomHtmlString(CustomHtmlString customHtmlString) {
        this.customHtmlString = customHtmlString;
    }
}
