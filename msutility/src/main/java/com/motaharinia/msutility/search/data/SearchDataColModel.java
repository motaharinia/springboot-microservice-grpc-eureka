package com.motaharinia.msutility.search.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.motaharinia.msutility.search.filter.SearchFilterOperationEnum;

import java.io.Serializable;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-17<br>
 * Time: 15:51:26<br>
 * Description:<br>
 * از این کلاس مدل برای تنظیم کردن مشخصات نمایشی ستونهای خروجی داده استفاده میشود
 */
public class SearchDataColModel implements Serializable {
    /**
     * نام فیلد ستون
     */
    private String name;
    /**
     * اندیس و ترتیب قرارگیری ستون
     */
    private String index;
    /**
     * جهت نمایش افقی
     */
    private SearchDataColAlignEnum align = SearchDataColAlignEnum.CENTER;
    /**
     * عرض ستون
     */
    private Integer width;
    /**
     * نوع مرتب سازی ستون که عددی یا متنی است
     */
    private SearchDataColSortTypeEnum sortType;
    /**
     * نوع جستجوی ستون که متنی یا انتخابی است
     */
    @JsonProperty("stype")
    private SearchDataColSearchTypeEnum searchType;
    /**
     * لیستی از آپشنهای جستجوی پیشرفته قابل استفاده در این ستون که بیشتر در جستجوی پیشرفته استفاده میشود
     */
    @JsonProperty(value = "searchoptions")
    private List<SearchFilterOperationEnum> searchFilterOperationList;
    /**
     * رشته فرمت کننده ستون
     * مثلا میخواهیم برای مقادیر صفر در ستون کلمه خیر بیاریم و برای مقادیر یک در ستون کلمه بلی بیاریم
     */
    private String formatter;
    /**
     * جستجوی پیشرفته دارد یا خیر
     */
    @JsonProperty("search")
    private Boolean searchable;
    /**
     * قابل مرتب سازی داده هست یا خیر
     */
    private Boolean sortable;

    /**
     * متد سازنده پیش فرض
     */
    public SearchDataColModel() {
    }

    /**
     * متد سازنده
     *
     * @param name                      نام فیلد ستون
     * @param index                     اندیس و ترتیب قرارگیری ستون
     * @param align                     جهت نمایش افقی
     * @param width                     عرض ستون
     * @param sortType                  نوع مرتب سازی ستون که عددی یا متنی است
     * @param searchType                نوع جستجوی ستون که متنی یا انتخابی است
     * @param searchFilterOperationList لیستی از آپشنهای جستجوی پیشرفته قابل استفاده در این ستون که بیشتر در جستجوی پیشرفته استفاده میشود
     * @param formatter                 رشته فرمت کننده ستون
     * @param searchable                جستجوی پیشرفته دارد یا خیر
     * @param sortable                  قابل مرتب سازی داده هست یا خیر
     */
    public SearchDataColModel(String name, String index, SearchDataColAlignEnum align, Integer width, SearchDataColSortTypeEnum sortType, SearchDataColSearchTypeEnum searchType, List<SearchFilterOperationEnum> searchFilterOperationList, String formatter, Boolean searchable, Boolean sortable) {
        this.name = name;
        this.index = index;
        this.align = align;
        this.width = width;
        this.sortType = sortType;
        this.searchType = searchType;
        this.searchFilterOperationList = searchFilterOperationList;
        this.formatter = formatter;
        this.searchable = searchable;
        this.sortable = sortable;
    }

    @Override
    public String toString() {
        return "SearchDataColModel{" +
                "name='" + name + '\'' +
                ", index='" + index + '\'' +
                ", align=" + align +
                ", width=" + width +
                ", sortType=" + sortType +
                ", searchType=" + searchType +
                ", searchFilterOperationList=" + searchFilterOperationList +
                ", formatter='" + formatter + '\'' +
                ", searchable=" + searchable +
                ", sortable=" + sortable +
                '}';
    }

    //getter-setter:
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public SearchDataColAlignEnum getAlign() {
        return align;
    }

    public void setAlign(SearchDataColAlignEnum align) {
        this.align = align;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public SearchDataColSortTypeEnum getSortType() {
        return sortType;
    }

    public void setSortType(SearchDataColSortTypeEnum sortType) {
        this.sortType = sortType;
    }

    public SearchDataColSearchTypeEnum getSearchType() {
        return searchType;
    }

    public void setSearchType(SearchDataColSearchTypeEnum searchType) {
        this.searchType = searchType;
    }

    public List<SearchFilterOperationEnum> getSearchFilterOperationList() {
        return searchFilterOperationList;
    }

    public void setSearchFilterOperationList(List<SearchFilterOperationEnum> searchFilterOperationList) {
        this.searchFilterOperationList = searchFilterOperationList;
    }

    public String getFormatter() {
        return formatter;
    }

    public void setFormatter(String formatter) {
        this.formatter = formatter;
    }

    public Boolean getSearchable() {
        return searchable;
    }

    public void setSearchable(Boolean searchable) {
        this.searchable = searchable;
    }

    public Boolean getSortable() {
        return sortable;
    }

    public void setSortable(Boolean sortable) {
        this.sortable = sortable;
    }
}
