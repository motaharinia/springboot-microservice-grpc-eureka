package com.motaharinia.msutility.json.sample;

import com.motaharinia.msutility.customfield.CustomDate;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-10-06 <br>
 * Time: 20:40:03 <br>
 * Description: <br>
 */
public class JsonModel {
    CustomDate customDate;
    String etcItemGender;

    public JsonModel() {
    }

    public JsonModel(CustomDate customDate, String etcItemGender) {
        this.customDate = customDate;
        this.etcItemGender = etcItemGender;
    }

    //getter-setter:
    public CustomDate getCustomDate() {
        return customDate;
    }

    public void setCustomDate(CustomDate customDate) {
        this.customDate = customDate;
    }

    public String getEtcItemGender() {
        return etcItemGender;
    }

    public void setEtcItemGender(String etcItemGender) {
        this.etcItemGender = etcItemGender;
    }
}
