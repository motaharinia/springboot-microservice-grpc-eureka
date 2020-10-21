package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.listnoduplicatebyfields.ListNoDuplicateByFields;
import com.motaharinia.msutility.customvalidation.required.Required;

import java.util.List;


public class InvalidModel {

    @Required
    @ListNoDuplicateByFields
    private List<Integer> idList;
    @Required
    private Boolean invalid;

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

}
