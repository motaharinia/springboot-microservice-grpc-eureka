package com.motaharinia.msjpautility.entity.sample.entity;

import java.util.Date;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 01:22:13<br>
 * Description:<br>
 *     کلاس انتیتی ب
 */
public class EntityB extends EntityA {

    /**
     * رشته آ
     */
    private String stringA;
    /**
     * اعشار بی
     */
    private Double doubleB;
    /**
     * لیست تحصیلات
     */
    private List<EntityEducation> entityEducationList;
    /**
     * لیست اعداد صحیح
     */
    private List<Integer> integerList;
    /**
     * تاریخ تولد
     */
    private Date dateOfBorn;
    /**
     * رشته اچ تی ام ال
     */
    private String customHtmlString;

    @Override
    public String toString() {
        return "EntityB{" + "id=" + getId() + ", entityCity=" + getEntityCity() + "stringA=" + stringA + ", doubleB=" + doubleB + ", integerList=" + integerList + ", entityEducationList=" + entityEducationList + '}';
    }

    //getter-setter:
    public String getStringA() {
        return stringA;
    }

    public void setStringA(String stringA) {
        this.stringA = stringA;
    }

    public Double getDoubleB() {
        return doubleB;
    }

    public void setDoubleB(Double doubleB) {
        this.doubleB = doubleB;
    }

    public List<EntityEducation> getEntityEducationList() {
        return entityEducationList;
    }

    public void setEntityEducationList(List<EntityEducation> entityEducationList) {
        this.entityEducationList = entityEducationList;
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void setIntegerList(List<Integer> integerList) {
        this.integerList = integerList;
    }

    public Date getDateOfBorn() {
        return dateOfBorn;
    }

    public void setDateOfBorn(Date dateOfBorn) {
        this.dateOfBorn = dateOfBorn;
    }

    public String getCustomHtmlString() {
        return customHtmlString;
    }

    public void setCustomHtmlString(String customHtmlString) {
        this.customHtmlString = customHtmlString;
    }

}
