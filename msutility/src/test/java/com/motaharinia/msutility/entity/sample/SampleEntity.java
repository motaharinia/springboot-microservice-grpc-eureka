package com.motaharinia.msutility.entity.sample;

import com.motaharinia.msutility.entity.GenericEntity;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:27:46<br>
 * Description:<br>
 * انتیتی نمونه جهت تست EntityToolsTests
 */
public class SampleEntity extends GenericEntity {
    /**
     * شناسه
     */
    private Integer id;

    /**
     * متد سازنده پیش فرض
     */
    public SampleEntity() {
    }

    /**
     * متد سازنده که با شناسه انتیتی را مقدارهی میکند
     * @param id شناسه
     */
    public SampleEntity(Integer id) {
        this.id = id;
    }

    //getter-setter

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
