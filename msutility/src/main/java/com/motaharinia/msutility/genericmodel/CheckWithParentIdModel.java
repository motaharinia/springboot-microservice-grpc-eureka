package com.motaharinia.msutility.genericmodel;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل بررسی به همراه شناسه والد است<br>
 */
public class CheckWithParentIdModel extends GeneralCheckModel {

    /**
     * شناسه والد
     */
    private Integer parentId;

    //getter-setter:
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
