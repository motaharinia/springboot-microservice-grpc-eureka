package com.motaharinia.msutility.search.data;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.motaharinia.msutility.json.serializer.JsonSerializerObjectArray;

import java.io.Serializable;
import java.util.Arrays;

/**
 * https://www.baeldung.com/jackson-field-serializable-deserializable-or-not
 * <p>
 * we can control if a field is serialized / deserialized by Jackson or not:
 * <p>
 * 1. A Public Field:
 * The simplest way to make sure a field is both serializable and deserializable is to make it public.
 * <p>
 * 2. A Getter Makes a Non-Public Field Serializable and Deserializable and A Setter Makes a Non-Public Field Deserializable Only:
 * <p>
 * 3. Make All Fields Globally Serializable :
 * That kind of global configuration can be done at the ObjectMapper level, by turning on the AutoDetect function to use either public fields or getter/setter methods for serialization, or maybe turn on serialization for all fields:
 * ObjectMapper mapper = new ObjectMapper();
 * mapper.setVisibility(PropertyAccessor.ALL, Visibility.NONE);
 * mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
 * <p>
 * 4. Change the Name of a Property on Serialization/Deserialization :
 *
 * @ JsonProperty("strVal")
 * public String getStringValue() {
 * return stringValue;
 * }
 * <p>
 * 5. Ignore a Field on Serialization or Deserialization:
 * @ JsonIgnore
 * public String getPassword() {
 * return password;
 * }
 * @ JsonProperty
 * public void setPassword(String password) {
 * this.password = password;
 * }
 */


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس اطلاعاتی از هر یک از سطرهای گرید ارائه میکند
 */
@JsonSerialize
//@JsonDeserialize
public class SearchDataRowModel implements Serializable {

    /**
     * شناسه سطر داده جستجو
     */
    private Integer id;

    /**
     * آرایه مقادیر ستونهای دیتای جستجو
     */
    private Object[] rowCellArray;

    /**
     * متد سازنده پیش فرض
     */
    public SearchDataRowModel() {
    }


    /**
     * متد سازنده که شناسه و  آرایه مقادیر ستونهای دیتای جستجو را از ورودی میگیرد و مدل سطر داده را می سازد
     *
     * @param id           شناسه سطر داده جستجو
     * @param rowCellArray آرایه مقادیر ستونهای دیتای جستجو
     */
    public SearchDataRowModel(Integer id, Object[] rowCellArray) {
        this.id = id;
        this.rowCellArray = rowCellArray;
    }

    @Override
    public String toString() {
        return "SearchDataRowModel{" +
                "id=" + id +
                ", rowCellArray=" + Arrays.toString(rowCellArray) +
                '}';
    }


    //getter-setter:
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @JsonSerialize(using = JsonSerializerObjectArray.class)
    public Object[] getRowCellArray() {
        return rowCellArray;
    }

    public void setRowCellArray(Object[] rowCellArray) {
        this.rowCellArray = rowCellArray;
    }
}
