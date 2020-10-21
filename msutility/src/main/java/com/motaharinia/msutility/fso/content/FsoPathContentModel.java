package com.motaharinia.msutility.fso.content;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:53:03<br>
 * Description:<br>
 * کلاس مدل حاوی اطلاعات فایلها و دایرکتوری های داخل یک مسیر
 */
public class FsoPathContentModel {
    /**
     * لیست اطلاعات دایرکتوری های داخل مسیر داده شده
     */
    private  List<FsoPathContentDirectoryModel> directoryModelList=new ArrayList<>();
    /**
     * لیست اطلاعات فایلهای داخل مسیر داده شده
     */
    private   List<FsoPathContentFileModel> fileModelList=new ArrayList<>();

    @Override
    public String toString() {
        return "FsoPathContentModel{" +
                "directoryModelList=[" +System.lineSeparator()+ directoryModelList.stream().map(item ->item.toString() ).collect(Collectors.joining("," + System.lineSeparator() )) +System.lineSeparator()+"]" +
                ", fileModelList=[" +System.lineSeparator()+ fileModelList.stream().map(item ->item.toString() ).collect(Collectors.joining("," + System.lineSeparator() )) +System.lineSeparator()+"]" +
                '}';
    }

    //getter-setter:
    public List<FsoPathContentDirectoryModel> getDirectoryModelList() {
        return directoryModelList;
    }

    public void setDirectoryModelList(List<FsoPathContentDirectoryModel> directoryModelList) {
        this.directoryModelList = directoryModelList;
    }

    public List<FsoPathContentFileModel> getFileModelList() {
        return fileModelList;
    }

    public void setFileModelList(List<FsoPathContentFileModel> fileModelList) {
        this.fileModelList = fileModelList;
    }
}
