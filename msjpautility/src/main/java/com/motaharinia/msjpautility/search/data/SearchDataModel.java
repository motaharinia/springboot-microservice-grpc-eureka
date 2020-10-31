package com.motaharinia.msjpautility.search.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.motaharinia.msjpautility.search.annotation.SearchDataColumn;
import com.motaharinia.msjpautility.search.filter.SearchFilterModel;
import com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import org.jetbrains.annotations.NotNull;
import org.reflections.ReflectionUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس مدل حاوی نتیجه جستجوی اطلاعات میباشد
 */

public class SearchDataModel implements Serializable {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    private static final SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

    /**
     * شماره صفحه فعلی
     */
    public Integer page;

    /**
     * تعداد کل سطرهای قابل نمایش که صفحه بندی شده اند و به صفحات کوچکتر تبدیل شده اند
     */
    public Long records;


    /**
     * لیست سطرهای داده
     */
    @JsonProperty(value = "rows")
    private List<SearchDataRowModel> searchDataRowModelList = new ArrayList<>();

    /**
     * لیست اطلاعات ستونها
     */
    @JsonProperty(value = "columns")
    private List<com.motaharinia.msjpautility.search.data.SearchDataColModel> searchDataColModelList = new ArrayList<>();

    /**
     * تعداد صفحات در صفحه بندی اطلاعات
     */
    public Long total;

    /**
     * اطلاعات اضافی
     */
    @JsonProperty(value = "userdata")
    public Object userData;


    /**
     * متد سازنده پیش فرض
     */
    public SearchDataModel() {
    }

    /**
     * متد سازنده مدل جستجوی داده که صفحه ای از اینتیرفیس ریپازیتوری دریافت شده از دیتابیس و مدل جستجو و اطلاعات اضافی را از ورودی دریافت میکند و مدل جستجوی داده را طبق آنها برای ارسال به کلاینت می سازد
     *
     * @param viewPage                صفحه ای از اینتیرفیس ریپازیتوری دریافت شده از دیتابیس
     * @param searchFilterModel       مدل جستجو
     * @param searchViewTypeInterface کلاس اینترفیس نوع نمایش خروجی که ستونهای(فیلدهای) خروجی داخل آن تعریف شده است
     * @param userData                خروجی: مدل جستجوی داده برای ارسال به کلاینت
     */
    public SearchDataModel(@NotNull Page<?> viewPage, @NotNull SearchFilterModel searchFilterModel, @NotNull Class searchViewTypeInterface, @NotNull Object userData) throws UtilityException {
        if (ObjectUtils.isEmpty(viewPage)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "viewPage");
        }
        if (ObjectUtils.isEmpty(searchFilterModel)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "searchFilterModel");
        }
        if (userData.equals(null)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "userData");
        }
        this.userData = userData;
        this.page = searchFilterModel.getPage();
        this.total = (long) viewPage.getTotalPages();
        this.records = viewPage.getTotalElements();


        //searchDataColModelList:
        HashMap<Integer, com.motaharinia.msjpautility.search.annotation.SearchDataColumn> indexAnnotationHashMap = new HashMap<>();
        List<com.motaharinia.msjpautility.search.data.SearchDataColModel> searchDataColModelList = new ArrayList<>();

        Set<Method> getterMethodSet1 = ReflectionUtils.getAllMethods(searchViewTypeInterface, ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get"));
        getterMethodSet1.stream().forEach(getterMethod -> {
            if (!ObjectUtils.isEmpty(getterMethod.getAnnotation(com.motaharinia.msjpautility.search.annotation.SearchDataColumn.class))) {
                indexAnnotationHashMap.put(getterMethod.getAnnotation(com.motaharinia.msjpautility.search.annotation.SearchDataColumn.class).index(), getterMethod.getAnnotation(com.motaharinia.msjpautility.search.annotation.SearchDataColumn.class));
            }
        });
        indexAnnotationHashMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            try {
                com.motaharinia.msjpautility.search.data.SearchDataColModel searchDataColModel = new com.motaharinia.msjpautility.search.data.SearchDataColModel();
                searchDataColModel.setAlign(entry.getValue().align());
                searchDataColModel.setFormatter(entry.getValue().formatter());
                searchDataColModel.setIndex(String.valueOf(entry.getValue().index()));
                searchDataColModel.setName(entry.getValue().name());
                searchDataColModel.setSearchable(entry.getValue().searchable());
                searchDataColModel.setSearchType(entry.getValue().searchType());
                searchDataColModel.setSortable(entry.getValue().sortable());
                searchDataColModel.setSortType(entry.getValue().sortType());
                searchDataColModel.setWidth(entry.getValue().width());
                if (searchDataColModel.getSortType().equals(SearchDataColSortTypeEnum.TEXT)) {
                    searchDataColModel.setSearchFilterOperationList(Arrays.asList(new com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum[]{com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.EQUAL, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.MATCH, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.MATCH_END, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.MATCH_START, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.IN, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.NOT_IN}));
                } else {
                    searchDataColModel.setSearchFilterOperationList(Arrays.asList(new com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum[]{com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.EQUAL, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.GREATER_THAN, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.GREATER_THAN_EQUAL, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.LESS_THAN, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.LESS_THAN_EQUAL, com.motaharinia.msjpautility.search.filter.SearchFilterOperationEnum.IN, SearchFilterOperationEnum.NOT_IN}));
                }
                searchDataColModelList.add(searchDataColModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.searchDataColModelList = searchDataColModelList;
//        this.searchDataColModelList.stream().forEach(item ->  System.out.println("SearchDataModel.Constructor searchDataColModelList.stream():"+item.toString()));

        //searchDataRowModelList:
        List<SearchDataRowModel> searchDataRowModelList = new ArrayList<>();
        viewPage.stream().forEach(item -> {
            try {
                searchDataRowModelList.add(new SearchDataRowModel((Integer) item.getClass().getDeclaredMethod("getId").invoke(item), recursiveDataRowModelList(item, item.getClass(), new HashMap<>(), new HashMap<>()).toArray()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        this.searchDataRowModelList = searchDataRowModelList;
//        this.searchDataRowModelList.stream().forEach(item ->  System.out.println("SearchDataModel.Constructor searchDataRowModelList.stream():"+item.toString()));
    }


    /**
     * متد بازگشتی که شیی اینترفیس ریپازیتوری و هش مپ اندیس-متد و هش مپ اندیس-شیی آن اینترفیس را ورودی میگیرد و در نهایت لیستی از آرایه مقادیر ستونهای دیتای جستجو را خروجی میدهد
     *
     * @param object             شیی اینترفیس ریپازیتوری
     * @param indexMethodHashMap هش مپ اندیس-متد اینترفیس
     * @param indexObjectHashMap هش مپ اندیس-شیی اینترفیس
     * @return خروجی:  لیستی از آرایه مقادیر ستونهای دیتای جستجو
     */
    @NotNull
    private List<Object> recursiveDataRowModelList(@NotNull Object object, Class clazz, @NotNull HashMap<Integer, Method> indexMethodHashMap, @NotNull HashMap<Integer, Object> indexObjectHashMap) throws UtilityException {
        if (ObjectUtils.isEmpty(clazz)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "clazz");
        }
        if (indexMethodHashMap.equals(null)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "indexMethodHashMap");
        }
        if (indexObjectHashMap.equals(null)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "indexObjectHashMap");
        }


        Set<Method> getterMethodSet = ReflectionUtils.getAllMethods(clazz, ReflectionUtils.withModifier(Modifier.PUBLIC), ReflectionUtils.withPrefix("get"));
        getterMethodSet.stream().forEach(getterMethod -> {
            try {
                if (!ObjectUtils.isEmpty(getterMethod.getAnnotation(com.motaharinia.msjpautility.search.annotation.SearchDataColumn.class))) {
                    if (getterMethod.getReturnType().getName().startsWith("java.lang") || getterMethod.getReturnType().getName().startsWith("java.util.Date")) {
                        indexMethodHashMap.put(getterMethod.getAnnotation(com.motaharinia.msjpautility.search.annotation.SearchDataColumn.class).index(), getterMethod);
                        indexObjectHashMap.put(getterMethod.getAnnotation(SearchDataColumn.class).index(), object);
                    } else {
                        getterMethod.setAccessible(true);
                        Object childObject = null;
                        if (!object.equals(null)) {
                            childObject = getterMethod.invoke(object);
                        }
                        recursiveDataRowModelList(childObject, getterMethod.getReturnType(), indexMethodHashMap, indexObjectHashMap);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        List<Object> rowCellList = new ArrayList<>();
        indexMethodHashMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(entry -> {
            try {
                entry.getValue().setAccessible(true);
                if (ObjectUtils.isEmpty(indexObjectHashMap.get(entry.getKey()))) {
                    rowCellList.add("");
                } else {
                    rowCellList.add(entry.getValue().invoke(indexObjectHashMap.get(entry.getKey())));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return rowCellList;
    }


    /**
     * متد سازنده
     *
     * @param page                   شماره صفحه فعلی
     * @param records                تعداد کل سطرهای قابل نمایش که صفحه بندی شده اند و به صفحات کوچکتر تبدیل شده اند
     * @param total                  تعداد صفحات در صفحه بندی اطلاعات
     * @param searchDataRowModelList لیست سطرهای داده
     * @param userData               اطلاعات اضافی
     */
    public SearchDataModel(Integer page, Long records, Long total, List<SearchDataRowModel> searchDataRowModelList, Object userData) {
        this.userData = userData;
        this.page = page;
        this.total = total;
        this.records = records;
        this.searchDataRowModelList = (ObjectUtils.isEmpty(searchDataRowModelList)) ? new ArrayList<>() : searchDataRowModelList;
    }
//    public GridDataModel(Integer page, Long records, Long total, List<Object[]> rows, Object userData) {
//        this.userData = userData;
//        this.page = page;
//        this.total = total;
//        this.records = records;
//        this.rows = (ObjectUtils.isEmpty(rows)) ? new ArrayList<>() : rows;
//    }


//    public SearchDataModel GridDataFixForExcel(@NotNull SearchDataModel gridData, @NotNull List<GridColModel> pageGridColModelList, @NotNull MessageSource messageSource, @NotNull  HashMap<String, List<Object[]>> formatters) throws Exception {
//        if (ObjectUtils.isEmpty(gridData) || ObjectUtils.isEmpty(pageGridColModelList)) {
//            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "");
//        }
//        List<Object[]> objectArrayList = gridData.getRows();
//        Object[] objectArray;
//        String colModelName = "";
//        if (objectArrayList != null) {
//            for (int i = 0; i < objectArrayList.size(); i++) {
//                objectArray = objectArrayList.get(i);
//                for (int j = 0; j < pageGridColModelList.size(); j++) {
//                    colModelName = pageGridColModelList.get(j).getName();
//                    if (formatters != null && formatters.containsKey(colModelName)) {
//                        List<Object[]> formatterArrayList = formatters.get(colModelName);
//                        objectArray[j] = fixFormatter(formatterArrayList, objectArray[j]);
//                    } else {
//                        if (".langKey".equals(colModelName.substring(Math.max(colModelName.length() - 8, 0)))) {
//                            objectArray[j] = fixLangKey((String) objectArray[j], messageSource);
//                        } else {
//                            switch (pageGridColModelList.get(j).getSortType()) {
//                                case "date":
//                                    objectArray[j] = CalDateTime.fixToLocaleDate((Date) objectArray[j], "/", LocaleContextHolder.getLocale());
//                                    break;
//                                case "dateTime":
//                                    objectArray[j] = CalDateTime.fixToLocaleDateTime((Date) objectArray[j], "/", LocaleContextHolder.getLocale());
//                                    break;
//                            }
//                        }
//                    }
//                    objectArrayList.set(i, objectArray);
//                }
//            }
//        }
//        return gridData;
//    }


    //لیستی از فرمترهای گرید و یک داده را دریافت میکند و اگر آن داده ورودی با یکی از داده های لیست فرمترها بخواند مقدار فرمت شده فرمتر را بجای داده خروجی میدهد
    @NotNull
    public String fixFormatter(@NotNull List<Object[]> formatterArrayList, @NotNull Object object) throws UtilityException {
        if (ObjectUtils.isEmpty(formatterArrayList)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "formatterArrayList");
        }
        if (ObjectUtils.isEmpty(object)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "object");
        }
        for (int i = 0; i < formatterArrayList.size(); i++) {
            if (formatterArrayList.get(i)[0].toString().equals(object + "")) {
                return formatterArrayList.get(i)[1].toString();
            }
        }
        return "";
    }

    //مطابق با زبان سیستم در صورت نیاز کلید لاتین را به متن فارسی معادل آن ترجمه میکند و خروجی میدهد
    public String fixLangKey(String langKey, MessageSource messageSource) {
        if (!ObjectUtils.isEmpty(langKey)) {
            return messageSource.getMessage(langKey, new Object[]{}, LocaleContextHolder.getLocale());
        } else {
            return "";
        }
    }


    @Override
    public String toString() {
        return "SearchDataModel{" +
                "page=" + page +
                ", records=" + records +
                ", searchDataRowModelList=[" + System.lineSeparator() + searchDataRowModelList.stream().map(item -> item.toString()).collect(Collectors.joining("," + System.lineSeparator())) + System.lineSeparator() + "]" +
                ", total=" + total +
                ", userData=" + userData +
                '}';
    }

    //getter-setter
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }


    public List<SearchDataRowModel> getSearchDataRowModelList() {
        return searchDataRowModelList;
    }

    public void setSearchDataRowModelList(List<SearchDataRowModel> searchDataRowModelList) {
        this.searchDataRowModelList = searchDataRowModelList;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getUserData() {
        return userData;
    }

    public void setUserData(Object userData) {
        this.userData = userData;
    }


    public List<com.motaharinia.msjpautility.search.data.SearchDataColModel> getSearchDataColModelList() {
        return searchDataColModelList;
    }

    public void setSearchDataColModelList(List<SearchDataColModel> searchDataColModelList) {
        this.searchDataColModelList = searchDataColModelList;
    }
}
