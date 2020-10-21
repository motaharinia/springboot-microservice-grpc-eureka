package com.motaharinia.msutility.customvalidation.listnoduplicatebyfields;


import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 *  کلاس بررسی کننده انوتیشن اعتبارسنجی عدم تکرار در لیست توسط فیلدهای مورد نظر<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
public class ListNoDuplicateByFieldsValidator implements ConstraintValidator<ListNoDuplicateByFields, List> {

    private String[] fields;

    @Override
    public void initialize(ListNoDuplicateByFields a) {
        fields = a.fields();
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext cvc) {
        if(ObjectUtils.isEmpty(list)){
            return true;
        }
        boolean repeat = false;
        if (fields == null || fields.length == 0) {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (list.get(i).equals(list.get(j))) {
                        repeat = true;
                        break;
                    }
                }
            }
        }
        else {
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if(checkTwoObjectEquality(list.get(i), list.get(j))){
                        repeat = true;
                        break;
                    }
                }
            }
        }
        return !repeat;
    }

    private Object getObjectFieldValue(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (Exception e) {
            return null;
        }
    }

    private Boolean checkTwoObjectEquality(Object obj1, Object obj2) {
        for(String field: fields){
            Object o1=getObjectFieldValue(obj1, field);
            Object o2=getObjectFieldValue(obj2, field);
            if(ObjectUtils.isEmpty(o1) || ObjectUtils.isEmpty(o2) ){
                if(ObjectUtils.isEmpty(o1) && ObjectUtils.isEmpty(o2) ){
                    return true;
                }else{
                    return false;
                }
            }else{
                if(!getObjectFieldValue(obj1, field).equals(getObjectFieldValue(obj2, field))){
                    return false;
                }else{
                    return true;
                }
            }
        }
        return true;
    }

}
