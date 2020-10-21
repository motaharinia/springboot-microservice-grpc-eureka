package com.motaharinia.msutility.jstree;

import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس خصوصیات هر گره درخت jstree میباشد
 */
public class JstreeNodeAAttrModel implements Serializable {


    /**
     * نشانی وب لینک
     */
    private String href;

    /**
     * هدف لینک
     * که در صفحه فعلی باز شود یا درپنجره دیگر و میتواند یکی از موارد زیر باشد
     * _blank , _self , _parent , _top
     */
    private String target;

    /**
     * متد سازنده پیش فرض
     */
    public JstreeNodeAAttrModel() {

    }

    /**
     * متد سازنده که با دریافت اطلاعات لینک مدل را میسازد
     * @param href نشانی وب لینک
     * @param target هدف لینک
     */
    public JstreeNodeAAttrModel(String href, String target) {
        this.href = href;
        this.target = target;
    }

    //getter-setter:
    public String getHref() {
        return href;
    }


    public void setHref(String href) {
        this.href = href;
    }


    public String getTarget() {
        return target;
    }


    public void setTarget(String target) {
        this.target = target;
    }


}
