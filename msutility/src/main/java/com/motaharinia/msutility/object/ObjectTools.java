package com.motaharinia.msutility.object;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.reflection.ReflectionTools;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس متدهای ابزاری اشیاء
 */
public interface ObjectTools {

    /**
     * این متد یک شی مبدا و یک شیی مقصد و یک آرایه اضافه شونده و یک آرایه حذف شونده پیشوند نام فیلد از ورودی دریافت میکند و اطلاعات شیی مبدا را روی شیی مقصد کپی میکند
     *
     * @param objectSource                            شیی مبدا
     * @param objectDestination                       شیی مقصد
     * @param objectDestinationFieldPrefixRemoveArray آرایه پیشوند نام فیلد حذف شونده از فیلدهای شیی مبدا
     * @param objectDestinationFieldPrefixAddArray    آرایه پیشوند نام فیلد اضافه شونده به فیلدهای شیی مبدا
     * @return خروجی: شیی مقصد پر شده از شیی مبدا
     */
    static Object copy(Object objectSource, Object objectDestination, @NotNull String[] objectDestinationFieldPrefixRemoveArray, @NotNull String[] objectDestinationFieldPrefixAddArray) {
        if (ObjectUtils.isEmpty(objectSource)) {
            return null;
        }
        List<Field> objectToFieldList = ReflectionTools.getAllFields(objectDestination.getClass(), new ArrayList<>());
        String objectFromGetterName;
        String objectToFieldName = "", objectFromFieldName = "";
        Object value = null;
        Field objectFromField = null;
        Class objectFromClazz = objectSource.getClass();
        for (Field objectToField : objectToFieldList) {
            objectToFieldName = objectToField.getName();
            try {
                objectFromFieldName = objectToFieldName;

                //ابتدا بررسی میکنیم آیا مشابه نام فیلد مقصد در مبدا وجود دارد یا خیر و اگر وجود نداشت از آرایه های داده شده پیشوند استفاده میکنیم
                try {
                    objectFromField = ReflectionTools.getField(objectFromClazz, objectFromFieldName);
                } catch (Exception exception) {
                    //بررسی و اضافه کردن اولین پیشوند مناسب در آرایه پیشوندها به نام فیلد شیی مبدا
                    if (!ObjectUtils.isEmpty(objectDestinationFieldPrefixRemoveArray)) {
                        objectFromField = null;
                        for (String prefix : objectDestinationFieldPrefixAddArray) {
                            try {
                                if (ObjectUtils.isEmpty(objectFromField) && objectFromFieldName.startsWith(prefix)) {
                                    objectFromFieldName = objectFromFieldName.replaceFirst(prefix, "");
                                    objectFromField = ReflectionTools.getField(objectFromClazz, objectFromFieldName);
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                    //بررسی و حذف کردن اولین پیشوند مناسب در آرایه پیشوندها از نام فیلد شیی مبدا
                    if (!ObjectUtils.isEmpty(objectDestinationFieldPrefixAddArray)) {
                        objectFromField = null;
                        for (String prefix : objectDestinationFieldPrefixAddArray) {
                            try {
                                if (ObjectUtils.isEmpty(objectFromField) && !objectFromFieldName.startsWith(prefix)) {
                                    objectFromField = ReflectionTools.getField(objectFromClazz, prefix + objectFromFieldName);
                                    objectFromFieldName = prefix + objectFromFieldName;
                                }
                            } catch (Exception e) {

                            }
                        }
                    }
                }

                if (objectToField.getType().equals(objectFromField.getType())) {
                    objectFromGetterName = ReflectionTools.getFieldGetterMethodName(objectFromField, objectFromFieldName);
                    Method objectFromGetterMethod = ReflectionTools.getMethod(objectFromClazz, objectFromGetterName);
                    objectFromGetterMethod.setAccessible(true);
                    value = objectFromGetterMethod.invoke(objectSource);
                    objectToField.setAccessible(true);
                    objectToField.set(objectDestination, value);
                }
            } catch (Exception ex) {

            }
        }
        return objectDestination;
    }

    /**
     * این متد یک نام فیلد و یک آرایه از پیشوندها را از ورودی دریافت میکند و رشته های داخل آرایه پیشوند را از نام فیلد حذف میکند
     *
     * @param fieldName   نام فیلد
     * @param prefixArray رشته پیشوند جهت حذف از نام فیلد
     * @return خروجی: نام فیلد اصلاح شده
     */
    @NotNull
    private static String removePrefixFromFieldName(@NotNull String fieldName, @NotNull String[] prefixArray) {
        if (ObjectUtils.isEmpty(fieldName)) {
            throw new UtilityException(ObjectTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fieldName");
        }
        if (ObjectUtils.isEmpty(prefixArray)) {
            throw new UtilityException(ObjectTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "prefixArray");
        }
        for (String prefix : prefixArray) {
            if (fieldName.startsWith(prefix)) {
                fieldName = fieldName.replaceFirst(prefix, "");
                return fieldName;
            }
        }
        return fieldName;
    }

    /**
     * این متد یک نام فیلد و یک آرایه از پیشوندها را از ورودی دریافت میکند و رشته های داخل آرایه پیشوند را به نام فیلد اضافه میکند
     *
     * @param fieldName   نام فیلد
     * @param prefixArray رشته پیشوند جهت اضافه به نام فیلد
     * @return خروجی: نام فیلد اصلاح شده
     */
    @NotNull
    private static String addPrefixToFieldName(@NotNull String fieldName, @NotNull String[] prefixArray) {
        if (ObjectUtils.isEmpty(fieldName)) {
            throw new UtilityException(ObjectTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "input");
        }
        if (ObjectUtils.isEmpty(prefixArray)) {
            throw new UtilityException(ObjectTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "prefixArray");
        }
        for (String prefix : prefixArray) {
            if (!fieldName.startsWith(prefix)) {
                fieldName = prefix + fieldName;
                return fieldName;
            }
        }
        return fieldName;
    }
}
