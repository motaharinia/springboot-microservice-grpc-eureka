package com.motaharinia.msutility.customvalidation.sample;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18 <br>
 * Time: 21:09:10 <br>
 * Description: <br>
 *     مدل نمونه برای تست اعتبارسنجی ListNoDuplicateByFields
 */
public class ListNoDuplicateByFieldsModel {
    String field1;
    String field2;

    public ListNoDuplicateByFieldsModel() {
    }

    public ListNoDuplicateByFieldsModel(String field1, String field2) {
        this.field1 = field1;
        this.field2 = field2;
    }

    //getter-setter:

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public String getField2() {
        return field2;
    }

    public void setField2(String field2) {
        this.field2 = field2;
    }
}
