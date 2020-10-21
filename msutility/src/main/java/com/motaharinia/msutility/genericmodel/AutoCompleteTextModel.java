package com.motaharinia.msutility.genericmodel;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل AutoCompleteText است<br>
 * کلاس AutoCompleteText برای جستجو در کلاینت استفاده میشود که با ورود چند حرف ورودی لیستی بر مبنای آن جستجو میشود<br>
 * تفاوت آن با کلاس AutoComplete در این است که کاربر در کلاینت میتواند بجای جستجو و انتخاب از گزینه ها یک پیشنهاد متنی برای سرور ارسال نماید
 */
public class AutoCompleteTextModel {
    /**
     * شناسه
     */
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
     * پیشنهاد متنی
     */
    private String suggestion;


    /**
     * متد سازنده پیش فرض
     */
    public AutoCompleteTextModel() {
    }

    /**
     * متد سازنده ای که با دریافت مشخصات ورودی مدل را میسازد
     *
     * @param id         شناسه
     * @param text       عنوان
     * @param elementStr توضیحات
     * @param suggestion پیشنهاد متنی
     */
    public AutoCompleteTextModel(String id, String text, String elementStr, String suggestion) {
        this.id = id;
        this.text = text;
        this.elementStr = elementStr;
        this.suggestion = suggestion;
    }


    //getter-setter:
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

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public static Boolean isSet(AutoCompleteTextModel autoCompleteTextModel) {
        if (autoCompleteTextModel == null) {
            return false;
        }
        if (((autoCompleteTextModel.getId() == null) || ("".equals(autoCompleteTextModel.getId()))) && ((autoCompleteTextModel.getSuggestion() == null) || ("".equals(autoCompleteTextModel.getSuggestion())))) {
            return false;
        }
        return true;
    }

    @JsonIgnore
    public Integer getIdASInteger() {
        return Integer.parseInt("".equals(this.getId()) ? "0" : this.getId());
    }

    //never Remove hashCode and equals method
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.suggestion);
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
        final AutoCompleteTextModel other = (AutoCompleteTextModel) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.suggestion, other.suggestion)) {
            return false;
        }
        return true;
    }

}
