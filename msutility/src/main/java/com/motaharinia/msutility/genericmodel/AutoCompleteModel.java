package com.motaharinia.msutility.genericmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.motaharinia.msutility.customvalidation.required.Required;

import java.io.Serializable;
import java.util.Objects;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل اتو کامپلیت است<br>
 * کلاس AutoComplete برای جستجو در کلاینت استفاده میشود که با ورود چند حرف ورودی لیستی بر مبنای آن جستجو میشود
 */
public class AutoCompleteModel implements Serializable {

    /**
     * شناسه
     */
    @Required
    private String id;
    /**
     * عنوان
     */
    private String text;
    /**
     * توضیحات
     */
    private String elementStr;


    /**
     * متد سازنده پیش فرض
     */
    public AutoCompleteModel() {
    }

    /**
     * متد سازنده ای که با دریافت مشخصات ورودی مدل را میسازد
     *
     * @param id          شناسه
     * @param title       عنوان
     * @param description توضیحات
     */
    public AutoCompleteModel(String id, String title, String description) {
        this.id = id;
        this.text = title;
        this.elementStr = description;
    }

    public AutoCompleteModel(String value, String title) {

        this.id = value;
        this.text = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getElementStr() {
        return elementStr;
    }

    public void setElementStr(String elementStr) {
        this.elementStr = elementStr;
    }

    @JsonIgnore
    public Integer getIdASInteger() {
        return Integer.parseInt("".equals(this.getId()) ? "0" : this.getId());
    }

    public AutoCompleteModel(String id) {
        this.id = id;
    }

    /*@Override
    public boolean equals(Object obj) {
        if (this.getId().equals("") && this.getText().equals("")) {
            return false;
        }
        return super.equals(obj); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int hashCode() {
        return super.hashCode(); //To change body of generated methods, choose Tools | Templates.
    }*/
    @Override
    public String toString() {
        return "AutoCompleteModel{" + "id=" + getId() + ", text=" + getText() + ", elementStr=" + getElementStr() + '}';
    }

    public static Boolean isSet(AutoCompleteModel autoCompleteModel) {
        if (autoCompleteModel == null) {
            return false;
        }
        if ((autoCompleteModel.getId() == null) || ("".equals(autoCompleteModel.getId()))) {
            return false;
        }
        return true;
    }

    //never Remove hashCode and equals method
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AutoCompleteModel other = (AutoCompleteModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
