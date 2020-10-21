package com.motaharinia.msutility.fso.download;

import com.motaharinia.msutility.fso.view.FileViewModel;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-13<br>
 * Time: 14:52:37<br>
 * Description:<br>
 * کلاس مدل فایل جهت دانلود
 */
public class FileDownloadModel extends FileViewModel {
    /**
     * آرایه بایت داده فایل
     */
    private byte[] dataByteArray;

    //getter-setter:
    public byte[] getDataByteArray() {
        return dataByteArray;
    }

    public void setDataByteArray(byte[] dataByteArray) {
        this.dataByteArray = dataByteArray;
    }
}
