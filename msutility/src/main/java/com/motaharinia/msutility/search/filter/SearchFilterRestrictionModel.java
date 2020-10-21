package com.motaharinia.msutility.search.filter;

import java.io.Serializable;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:05:15<br>
 * Description:<br>
 * این کلاس مدل شرطی است که میخواهیم در جستجو هایی مانند جستجوی گرید از آن استفاده کنیم
 */
public class SearchFilterRestrictionModel implements Serializable {

    /**
     * نام فیلدی که روی آن شرط گذاشته میشود
     */
    private String fieldName;
    /**
     * عملیات شرط برای فیلد مورد نظر در جستجو
     */
    private SearchFilterOperationEnum fieldOperation;
    /**
     * مقدار شرط برای فیلد مورد نظر در جستجو
     */
    private Object fieldValue;


    /**
     *  مقادیر ثابت and و or بر روی جستجوی پیشرفته
     */
    SearchFilterNextConditionOperatorEnum nextConditionOperator= SearchFilterNextConditionOperatorEnum.AND;


    /**
     * متد سازنده پیش فرض
     */
    public SearchFilterRestrictionModel() {
    }

    /**
     * متد سازنده که با دریافت نام فیلد و عملیات شرط و مقدار شرط مدل را می سازد
     *
     * @param fieldName      نام فیلدی که روی آن شرط گذاشته میشود
     * @param fieldOperation عملیات شرط برای فیلد مورد نظر در جستجو
     * @param fieldValue     مقدار شرط برای فیلد مورد نظر در جستجو
     * @param nextConditionOperator    مقادیر ثابت and و or بر روی جستجوی پیشرفته
     */
    public SearchFilterRestrictionModel(String fieldName, SearchFilterOperationEnum fieldOperation, Object fieldValue, SearchFilterNextConditionOperatorEnum nextConditionOperator) {
        this.fieldName = fieldName;
        this.fieldOperation = fieldOperation;
        this.fieldValue = fieldValue;
        this.nextConditionOperator=nextConditionOperator;
    }

    @Override
    public String toString() {
        return "SearchFilterRestrictionModel{" +
                "fieldName='" + fieldName + '\'' +
                ", fieldOperation=" + fieldOperation +
                ", fieldValue=" + fieldValue +
                ", nextConditionOperator=" + nextConditionOperator +
                '}';
    }

    //getter-setter:
    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(Object fieldValue) {
        this.fieldValue = fieldValue;
    }

    public SearchFilterOperationEnum getFieldOperation() {
        return fieldOperation;
    }

    public void setFieldOperation(SearchFilterOperationEnum fieldOperation) {
        this.fieldOperation = fieldOperation;
    }

    public SearchFilterNextConditionOperatorEnum getNextConditionOperator() {
        return nextConditionOperator;
    }

    public void setNextConditionOperator(SearchFilterNextConditionOperatorEnum nextConditionOperator) {
        this.nextConditionOperator = nextConditionOperator;
    }
}
