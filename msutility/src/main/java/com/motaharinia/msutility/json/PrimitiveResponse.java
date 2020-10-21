package com.motaharinia.msutility.json;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس برای تبدیل به رشته جیسون شدن نوع داده هایی در خروجی متد کنترلر به سمت کلاینت است که تک مقدار هستند و فیلد ندارند مانند نوع داده بولین
 */
public class PrimitiveResponse {

    private Object response;

    /**
     * متد سازنده
     *
     * @param response مقدار ورودی
     */
    public PrimitiveResponse(Object response) {
        this.response = response;
    }

    public PrimitiveResponse() {
    }

    //getter-setter:
    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

}
