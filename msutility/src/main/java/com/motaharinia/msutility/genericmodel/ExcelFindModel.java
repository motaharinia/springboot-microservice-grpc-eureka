package com.motaharinia.msutility.genericmodel;


import com.motaharinia.msutility.customvalidation.required.Required;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس جستجوی اکسل در کلاینت است<br>
 */
public class ExcelFindModel extends FindModel {

    /**
     * کلید جستجو
     */
    @Required
    String fileKey;

    //getter-setter:
    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

}
