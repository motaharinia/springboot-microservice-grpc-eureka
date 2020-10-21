package com.motaharinia.msutility.genericmodel;

import com.motaharinia.msutility.customvalidation.required.Required;

import java.util.ArrayList;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس جستجو در کلاینت است<br>
 */
public class FindModel {

    /**
     * لیستی از شناسه ها جهت جستجو
     */
    @Required
    private List<Integer> idList = new ArrayList<>();

    /**
     * پارامتر
     */
    private String param;


    /**
     * متد سازنده پیش فرض
     */
    public FindModel() {
    }

    /**
     * متد سازنده ای که با دریافت لیست شناسه ها از ورودی مدل را میسازد
     *
     * @param idList لیست شناسه ها جهت جستجو
     */
    public FindModel(List<Integer> idList) {
        this.idList = idList;
    }

    /**
     * متد سازنده ای که با دریافت یک شناسه از ورودی مدل را میسازد
     *
     * @param id شناسه جهت جستجو
     */
    public FindModel(Integer id) {
        List<Integer> idList = new ArrayList<>();
        idList.add(id);
        this.idList = idList;
    }


    @Override
    public String toString() {
        return "FindModel{" + "idList=" + idList + ", param=" + param + '}';
    }


    //getter-setter:
    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

}
