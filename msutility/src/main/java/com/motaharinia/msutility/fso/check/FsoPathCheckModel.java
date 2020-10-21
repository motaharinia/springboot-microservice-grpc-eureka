package com.motaharinia.msutility.fso.check;

import java.io.File;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 12:43:42<br>
 * Description:<br>
 * کلاس مدل که نوع مسیر داده شده فایل یا دایرکتوری و رفرنس فایل آن مسیر را در خود دارد
 */
public class FsoPathCheckModel {
    /**
     * رفرنس فایل مسیر
     */
    File file;
    /**
     * نوع مسیر
     */
    FsoPathCheckTypeEnum typeEnum;

    //getter-setter;
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FsoPathCheckTypeEnum getTypeEnum() {
        return typeEnum;
    }

    public void setTypeEnum(FsoPathCheckTypeEnum typeEnum) {
        this.typeEnum = typeEnum;
    }
}
