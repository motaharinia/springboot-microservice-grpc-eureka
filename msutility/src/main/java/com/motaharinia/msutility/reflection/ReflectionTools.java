package com.motaharinia.msutility.reflection;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این اینترفیس متدهای ابزاری رفلکشن جاوا را دارا میباشد
 */
public interface ReflectionTools {

    /**
     * این متد یک کلاس و یک نام فیلد را از ورودی دریافت میکند و فیلد منطبق با کلاس و نام فیلد ورودی را خروجی میدهد
     * اگر آن نام فیلد در آن کلاس وجود نداشته باشد آن را از کلاس پدر جستجو و خروجی میدهد
     *
     * @param clazz     کلاس جاوا
     * @param fieldName نام فیلد
     * @return خروجی: فیلد منطبق با کلاس و نام فیلد ورودی
     */
    @NotNull
     static Field getField(@NotNull Class<?> clazz, @NotNull String fieldName) throws UtilityException {
        if (ObjectUtils.isEmpty(clazz)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "clazz");
        }
        if (ObjectUtils.isEmpty(fieldName)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fieldName");
        }
        try {
            return clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException exception) {
            if (!ObjectUtils.isEmpty(clazz.getSuperclass())) {
                return getField(clazz.getSuperclass(), fieldName);
            } else {
                throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.REFLECTION_FIELD_IS_NOT_EXISTED, "fieldName:" + fieldName);
            }
        }
    }

    /**
     * این متد یک فیلد و نام آن را از ورودی دریافت میکند و متدGetter آن فیلد راخروجی میدهد
     *
     * @param field     فیلد
     * @param fieldName نام فیلد
     * @return خروجی: متد Geter فیلد ورودی
     */
    @NotNull
     static String getFieldGetterMethodName(@NotNull Field field, @NotNull String fieldName) throws UtilityException {
        if (ObjectUtils.isEmpty(field)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "field");
        }
        if (ObjectUtils.isEmpty(fieldName)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fieldName");
        }
        String fn = fieldName;
        if (Character.isLowerCase(fieldName.charAt(1))) {
            fn = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        }
        String getterName = "get" + fn;
        if (field.getType().equals(boolean.class)) {
            getterName = "is" + fn;
        }
        return getterName;
    }

    /**
     * این متد یک کلاس و یک لیست از ورودی دریافت میکند و به صورت بازگشتی تمام فیلدهای خود کلاس و کلاسهای پدر آن را در لیست ورودی اضافه میکند و لیست را خروجی میدهد
     *
     * @param clazz     کلاس
     * @param fieldList لیست فیلدها که در ابتدا به صورت لیست خالی باید داده شود
     * @return خروجی: لیستی از فیلدهای کلاس و کلاسهای پدر
     */
    @NotNull
    static List<Field> getAllFields(@NotNull Class<?> clazz, @NotNull List<Field> fieldList) throws UtilityException {
        if (ObjectUtils.isEmpty(clazz)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "clazz");
        }
        fieldList.addAll(Arrays.asList(clazz.getDeclaredFields()));
        if (!ObjectUtils.isEmpty(clazz.getSuperclass())) {
            fieldList = getAllFields(clazz.getSuperclass(), fieldList);
        }
        return fieldList;
    }


    /**
     * این متد یک کلاس و یک نام متد از ورودی دریافت میکند و متد متناسب با نام آن در کلاس را خروجی میدهد
     *
     * @param clazz      کلاس
     * @param methodName نام متد
     * @return خروجی: متد متناسب با نام آن در کلاس
     */
    @NotNull
    static Method getMethod(@NotNull Class<?> clazz, @NotNull String methodName){
        if (ObjectUtils.isEmpty(clazz)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "clazz");
        }
        if (ObjectUtils.isEmpty(methodName)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "methodName");
        }
        try {
            return clazz.getDeclaredMethod(methodName);
        } catch (NoSuchMethodException exception) {
            if (!ObjectUtils.isEmpty(clazz.getSuperclass())) {
                return getMethod(clazz.getSuperclass(), methodName);
            } else {
                throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.REFLECTION_METHOD_IS_NOT_EXISTED, "methodName:" + methodName);
            }
        }
    }

    //یک متد را طبق نام و پارامترهای آن و کلاس مربوطه ورودی خروجی میدهد

    /**
     * این متد یک کلاس و یک نام متد و پارمترهای متد را از ورودی دریافت میکند و متد متناسب با نام و پارامترهای آن در کلاس را خروجی میدهد
     *
     * @param clazz          کلاس
     * @param methodName     نام متد
     * @param parameterTypes پارامترهای متد
     * @return خروجی: متد متناسب با نام و پارامترهای آن در کلاس
     */
    @NotNull
    static Method getMethod(@NotNull Class<?> clazz, @NotNull String methodName, Class<?>... parameterTypes) {
        if (ObjectUtils.isEmpty(clazz)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "clazz");
        }
        if (ObjectUtils.isEmpty(methodName)) {
            throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "methodName");
        }
        try {
            return clazz.getDeclaredMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException exception) {
            if (!ObjectUtils.isEmpty(clazz.getSuperclass())) {
                return getMethod(clazz.getSuperclass(), methodName, parameterTypes);
            } else {
                throw new UtilityException(ReflectionTools.class, UtilityExceptionKeyEnum.REFLECTION_METHOD_IS_NOT_EXISTED, "methodName:" + methodName);
            }
        }
    }

}
