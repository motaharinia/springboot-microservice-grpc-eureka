package com.motaharinia.msutility.genericmodel;

import java.util.ArrayList;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس کاستوم کامبو در کلاینت است<br>
 */
public class CustomComboModel {

    /**
     * لیستی از سطرهای کاستوم کامبو
     */
    private List<CustomComboDataRowModel> dataList = new ArrayList<>();

    /**
     * تعداد کل موارد
     */
    private Integer totalCount;

    /**
     * شماره صفحه
     */
    private Integer page;
    /**
     * تعداد موارد در هر صفحه
     */
    private Integer rows;

    /**
     * متد سازنده پیش فرض
     */
    public CustomComboModel() {
    }

    /**
     * متد سازنده ای که با دریافت مشخصات ورودی مدل را میسازد
     *
     * @param dataList   لیستی از سطرهای کاستوم کامبو
     * @param totalCount تعداد کل موارد
     * @param page       شماره صفحه
     * @param rows       تعداد موارد در هر صفحه
     */
    public CustomComboModel(List<CustomComboDataRowModel> dataList, Integer totalCount, Integer page, Integer rows) {
        this.dataList = dataList;
        this.totalCount = totalCount;
        this.page = page;
        this.rows = rows;
    }


    //getter-setter:
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<CustomComboDataRowModel> getDataList() {
        return dataList;
    }

    public void setDataList(List<CustomComboDataRowModel> dataList) {
        this.dataList = dataList;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

}
