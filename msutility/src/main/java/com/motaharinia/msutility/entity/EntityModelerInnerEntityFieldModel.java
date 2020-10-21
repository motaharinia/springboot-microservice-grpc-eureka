package com.motaharinia.msutility.entity;

import java.lang.reflect.Field;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل انتیتی داخلی و فیلد متناظر آن است که از روی آندرلاینهای نام فیلد مدل به دست می آید<br>
 */
public class EntityModelerInnerEntityFieldModel {
    /**
     * شیی انتیتی داخلی
     */
    private Object innerEntity;
    /**
     * شیی فیلد انتیتی داخلی
     */
    private Field innerEntityField;


    /**
     * متد سازنده که با دریافت اطلاعات مدل را پر میکند
     *
     * @param innerEntity      شیی انتیتی داخلی
     * @param innerEntityField شیی فیلد انتیتی داخلی
     */
    public EntityModelerInnerEntityFieldModel(Object innerEntity, Field innerEntityField) {
        this.innerEntity = innerEntity;
        this.innerEntityField = innerEntityField;
    }

    //getter-setter:
    public Object getInnerEntity() {
        return innerEntity;
    }

    public void setInnerEntity(Object innerEntity) {
        this.innerEntity = innerEntity;
    }

    public Field getInnerEntityField() {
        return innerEntityField;
    }

    public void setInnerEntityField(Field innerEntityField) {
        this.innerEntityField = innerEntityField;
    }


}
