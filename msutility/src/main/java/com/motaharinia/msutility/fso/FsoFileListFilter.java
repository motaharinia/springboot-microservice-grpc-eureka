package com.motaharinia.msutility.fso;

import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:43:42<br>
 * Description:<br>
 * کلاس فیلتر فایل و دایرکتوری
 */
class FsoFileListFilter implements FileFilter {

    /**
     * آرایه نام فایلهای مجاز برای فیلتر
     */
    private final String[] acceptNameArray;
    /**
     * آرایه پسوند فایلهای مجاز برای فیلتر
     */
    private final String[] acceptExtensionArray;
    /**
     * آرایه نام فایلهای غیر مجاز برای فیلتر
     */
    private final String[] denyNameArray;
    /**
     * آرایه پسوند فایلهای غیر مجاز برای فیلتر
     */
    private final String[] denyExtensionArray;
    /**
     *مجاز بودن فایل و دایرکتوری های مخفی در فیلتر
     */
    private final Boolean showHidden;

    /**
     * متد سازنده پیش فرض
     */
    public FsoFileListFilter() {
        acceptNameArray=new String[0];
        acceptExtensionArray=new String[0];
        denyNameArray=new String[0];
        denyExtensionArray=new String[0];
        showHidden=true;
    }

    /**
     * متد سازنده که با دریافت اطلاعات مدل را می سازد
     * @param acceptNameArray  آرایه نام فایلهای مجاز برای فیلتر
     * @param acceptExtensionArray  آرایه پسوند فایلهای مجاز برای فیلتر
     * @param denyNameArray  آرایه نام فایلهای غیر مجاز برای فیلتر
     * @param denyExtensionArray  آرایه پسوند فایلهای غیر مجاز برای فیلتر
     * @param showHidden  مجاز بودن فایل و دایرکتوری های مخفی در فیلتر
     */
    public FsoFileListFilter(String[] acceptNameArray, String[] acceptExtensionArray, String[] denyNameArray, String[] denyExtensionArray, Boolean showHidden) {
        this.acceptNameArray = acceptNameArray;
        this.acceptExtensionArray = acceptExtensionArray;
        this.denyNameArray = denyNameArray;
        this.denyExtensionArray = denyExtensionArray;
        if(ObjectUtils.isEmpty(showHidden)){
            showHidden=false;
        }
        this.showHidden = showHidden;
    }

    /**
     * این متد چک میکند با توجه به شرایط داده شده فایل ورودی قابل انتخاب می باشد یا خیر
     * @param file فایل ورودی
     * @return خروجی: تایید یا عدم تایید انتخاب فایل
     */
    @Override
    public boolean accept(File file) {
        String filename = file.getName();
        boolean result = true;
        boolean acceptNameResult = false;
        if(!ObjectUtils.isEmpty(acceptNameArray)){
             acceptNameResult=  Arrays.stream(acceptNameArray).anyMatch(item-> filename.startsWith(item));
        } else {
            acceptNameResult = true;
        }

        boolean acceptExtensionResult = false;
        if(!ObjectUtils.isEmpty(acceptExtensionArray)){
            acceptNameResult=  Arrays.stream(acceptExtensionArray).anyMatch(item-> filename.endsWith('.' +item));
        } else {
            acceptExtensionResult = true;
        }

        boolean denyNameResult = true;
        if(!ObjectUtils.isEmpty(denyNameArray)){
            denyNameResult=  !Arrays.stream(denyNameArray).anyMatch(item-> filename.startsWith(item));
        }

        boolean denyExtensionResult = true;
        if(!ObjectUtils.isEmpty(denyExtensionArray)){
            denyExtensionResult=  !Arrays.stream(denyExtensionArray).anyMatch(item-> filename.endsWith('.' +item));
        }
        result = (acceptNameResult && acceptExtensionResult && denyNameResult && denyExtensionResult);

        if ((result) && (!this.showHidden) && (file.isHidden())){
                result = false;
        }

        return result;

    }



}
