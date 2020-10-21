package com.motaharinia.msutility.genericmodel;

import com.motaharinia.msutility.reflection.ReflectionTools;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس جستجو در کلاینت است<br>
 */
public class GenericModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Boolean invalid = false;
    private Boolean hidden = false;

    //getter-setter:
    public Boolean getInvalid() {
        return invalid;
    }

    public void setInvalid(Boolean invalid) {
        this.invalid = invalid;
    }

    public Boolean getHidden() {
        return hidden;
    }

    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    public void setNullFields(List<String> fieldNameList) throws Exception {

        Class modelClass = this.getClass();
        List<Field> fields = ReflectionTools.getAllFields(this.getClass(),new ArrayList<Field>());
        for (Field modelField : fields) {
            String modelFieldName = modelField.getName();
            if (AutoCompleteModel.class.equals(modelField.getType())) {
                String modelFieldGetterName = ReflectionTools.getFieldGetterMethodName(modelField, modelFieldName);
                Method modelFieldGetterMethod = ReflectionTools.getMethod(modelClass, modelFieldGetterName);
                modelFieldGetterMethod.setAccessible(true);
//                Object autoCompleteObjectValue = modelFieldGetterMethod.invoke(this, null);
                Object autoCompleteObjectValue = modelFieldGetterMethod.invoke(this);
                if (autoCompleteObjectValue != null) {
                    Field elementStrField = ReflectionTools.getField(AutoCompleteModel.class, "elementStr");
                    elementStrField.setAccessible(true);
                    elementStrField.set(autoCompleteObjectValue, null);
                }
            }
            if (fieldNameList.contains(modelFieldName)) {
                if (modelField.getType() == String.class) {
                    modelField.setAccessible(true);
                    modelField.set(this, "");
                } else {
                    modelField.setAccessible(true);
                    modelField.set(this, null);
                }
            }
        }

    }

}
