package com.motaharinia.msutility.genericmodel;

import com.motaharinia.msutility.customvalidation.required.Required;

import java.util.List;


public class PasswordModel {

    @Required
    private List<Integer> idList;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }
}
