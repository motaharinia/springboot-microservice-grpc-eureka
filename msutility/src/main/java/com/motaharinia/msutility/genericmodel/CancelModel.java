package com.motaharinia.msutility.genericmodel;

import com.motaharinia.msutility.customvalidation.required.Required;

import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل لغو است<br>
 *     تعدادی شناسه برای لغو را از کلاینت به سرور منتقل مینماید
 */
public class CancelModel {

    /**
     * لیستی از شناسه های لغو
     */
    @Required
    private List<Integer> idList;

    //getter-setter:
    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

}
