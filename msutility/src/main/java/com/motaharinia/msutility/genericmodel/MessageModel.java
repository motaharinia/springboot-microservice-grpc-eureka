package com.motaharinia.msutility.genericmodel;

import com.motaharinia.msutility.customvalidation.required.Required;

import java.util.List;


public class MessageModel {

    @Required
    private List<Integer> idList;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
    @Required
    private String textSMS;

    public String getTextSMS() {
        return textSMS;
    }

    public void setTextSMS(String textSMS) {
        this.textSMS = textSMS;
    }

}
