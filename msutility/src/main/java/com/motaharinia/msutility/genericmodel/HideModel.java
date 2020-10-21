package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.listnoduplicatebyfields.ListNoDuplicateByFields;
import com.motaharinia.msutility.customvalidation.required.Required;

import java.util.List;


public class HideModel {

    @Required
    @ListNoDuplicateByFields
    private List<Integer> idList;

    @Required
    private Boolean hide;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Boolean getHide() {
        return hide;
    }

    public void setHide(Boolean hide) {
        this.hide = hide;
    }

}
