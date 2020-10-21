package com.motaharinia.msutility.customfield;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description: <br>
 * این کلاس برای جابجا کردن داده های اپ تی ام ال استفاده میشود<br>
 * سامانه این فیلد را از فیلدهای معمولی رشته متمایز میکند و تگهای اچ تی ام ال داخل این فیلد را فیلتر نمیکند
 */
public class CustomHtmlString implements Serializable {

    private String customHtmlString;


    public CustomHtmlString() {

    }

    @JsonCreator
    public CustomHtmlString(@JsonProperty("customHtmlString") String customHtmlString) {
        this.setCustomHtmlString(customHtmlString);
    }

    @Override
    public String toString() {
        return "CustomHtmlString{" + "customHtmlString=" + customHtmlString + '}';
    }


    /**
     * بررسی میکند آیا کاستوم ورودی نال یا خالی هست
     *
     * @param customHtmlString کاستوم ورودی
     * @return خروجی: نتیجه بررسی
     */
    public static Boolean isEmpty(CustomHtmlString customHtmlString) {
        if (ObjectUtils.isEmpty(customHtmlString)) {
            return true;
        }
        if (ObjectUtils.isEmpty(customHtmlString.getCustomHtmlString())) {
            return true;
        }
        return false;
    }

    //getter-setter:
    public String getCustomHtmlString() {
        return customHtmlString;
    }

    public void setCustomHtmlString(String customHtmlString) {
        this.customHtmlString = customHtmlString;
    }
}
