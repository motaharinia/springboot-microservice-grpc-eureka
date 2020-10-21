package com.motaharinia.msutility.entity;

import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *     این کلاس برای مدیریت یکپارچه متغیرهای مورد نیاز برای دیتابیس اوراکل در سطح انتیتی ها آماده شده است
 */
public class OracleUtility {

    /**
     * تعریف میزان محاسه عدد اعشار تا 5 رقم
     */
    public static final Integer BIG_DECIMAL_SCALE = 5;
    /**
     * تعریف نوع داده میزان پول با طول عدد صحیح 19 رقم و طول اعشار 4 رقم
     */
    public static final String COLUMN_DEFINITION_AMOUNT_194 = "DECIMAL(19,4)";
    /**
     * تعریف نوع داده اعشاری با طول عدد صحیح 8 رقم و طول اعشار 4 رقم
     */
    public static final String COLUMN_DEFINITION_DOUBLE_84 = "DECIMAL(8,4)";
    /**
     * تعریف نوع داده اعشاری با طول عدد صحیح 14 رقم و طول اعشار 4 رقم
     */
    public static final String COLUMN_DEFINITION_DOUBLE_144 = "DECIMAL(14,4)";
    /**
     * تعریف نوع داده تاریخ
     */
    public static final String COLUMN_DEFINITION_DATE = "DATE";
    /**
     * تعریف نوع داده کلید اصلی انتیتی در دیتابیس
     */
    public static final String COLUMN_DEFINITION_PRIMARY_KEY = "NUMBER";

    /**
     * این متد برای تبدیل رشته تاریخ به عبارت متناظر آن در دیتابیس اوراکل میباشد
     * @param dateString رشته تاریخ
     * @return خروجی: رشته متناسب تاریخ در دیتابیس اوراکل
     */
    @NotNull
    public static String toDate(@NotNull String dateString) {
        if (ObjectUtils.isEmpty(dateString)) {
            dateString = "null";
        }
        return "TO_DATE('" + dateString + "','YYYY-MM-DD HH24:MI:SS')";
    }

    /**
     * این متد برای تبدیل Date تاریخ به عبارت متناظر آن در دیتابیس اوراکل میباشد
     * @param date Date تاریخ
     * @return خروجی: رشته متناسب تاریخ در دیتابیس اوراکل
     */
    @NotNull
    public static String toDate(@NotNull Date date) {
        if(!ObjectUtils.isEmpty(date)){
            return toDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
        }else{
            return toDate("null");
        }
    }

    /**
     *این متد ، متد fixQueryIN را با شرط IN فراخوانی میکند
     * @param jpqlFieldName نام فیلد مورد نظر برای کوئری
     * @param itemCsv مقادیر csv
     * @return خروجی: کوئری مناسب که مشکل محدودیت تعداد csv های دستور IN در اوراکل را نداشته باشد
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
*/
    public static String fixQueryIN(String jpqlFieldName, String itemCsv) throws Exception {
        return fixQueryIN(jpqlFieldName, itemCsv, " IN ");
    }

    /**
     *این متد ، متد fixQueryIN را با شرط NOT IN فراخوانی میکند
     * @param jpqlFieldName نام فیلد مورد نظر برای کوئری
     * @param itemCsv مقادیر csv
     * @return خروجی: کوئری مناسب که مشکل محدودیت تعداد csv های دستور IN در اوراکل را نداشته باشد
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
*/
    public static String fixQueryNOTIN(String jpqlFieldName, String itemCsv) throws Exception {
        return fixQueryIN(jpqlFieldName, itemCsv, " NOT IN ");
    }

    /**
     *این متد به دلیل محدودیتی که اوراکل در استفاده از دستور IN دارد نام فیلد و تعداد مقادیر به صورت csv و نوع IN یا NOTIN را میگیرد و در صورتی که تعداد مقادیر csv بیش از تعداد مجاز اوراکل باشد به چند شرط تبدیل میکند
     * @param jpqlFieldName نام فیلد مورد نظر برای کوئری
     * @param itemCsv مقادیر csv
     * @param INOrNOTIN کلمه IN یا NOTIN
     * @return خروجی: کوئری مناسب که مشکل محدودیت تعداد csv های دستور IN در اوراکل را نداشته باشد
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
*/
    private static String fixQueryIN(String jpqlFieldName, String itemCsv, String INOrNOTIN) throws Exception {
        String resultIN = "";
        String result = "";

        List<String> myList = new ArrayList<>(Arrays.asList(itemCsv.split(",")));

        Integer myListSize = myList.size();
        Integer i = 1;
        Integer counter = 1;
        for (String item : myList) {
            if (i < 1000) {
                //-----------------------------------
                //-----------------------------------
                if (resultIN != "") {
                    resultIN = resultIN + ",";
                }
                resultIN = resultIN + item;

                //-----------------------------------
                //-----------------------------------
                if (counter.equals(myListSize)) {

                    if (result != "") {
                        result = result + " OR ";
                    }
                    result = result + "(" + jpqlFieldName + INOrNOTIN + "(" + resultIN + "))";

                }
                i++;
            } else {
                //-----------------------------------
                //وقتی مقدار i برابر 1000 میشود در else میفتد و در اینجا باید مقدار 1000 رو به resultIN اضافه کنم
                //-----------------------------------
                if (resultIN != "") {
                    resultIN = resultIN + ",";
                }
                resultIN = resultIN + item;

                //-----------------------------------
                //ایجاد شرط restriction
                //-----------------------------------
                if (result != "") {
                    result = result + " OR ";
                }
                result = result + "(" + jpqlFieldName + INOrNOTIN + "(" + resultIN + "))";

                //-----------------------------------
                //مقادیر i و resultIN بعد از هر 1000 تا ریست میشوند
                //-----------------------------------
                resultIN = "";
                i = 1;
            }
            counter++;

        }
        //System.out.println("result : " + result);
        return "(" + result + ")";

    }

}

/**
 * سمپل ست کردن scale برای عملیات bigDecimal public static void main(String[]
 * args) {
 * <p>
 * final BigDecimal bg1 = new BigDecimal("5.655388"); final BigDecimal bg2 = new
 * BigDecimal("-1").multiply(bg1);
 * <p>
 * جمع// System.out.println("add = " +
 * bg1.add(bg1).setScale(OracleUtility.BIG_DECIMAL_SCALE,BigDecimal.ROUND_HALF_DOWN));
 * تفریق// System.out.println("subtract = " +
 * bg1.subtract(bg2).setScale(OracleUtility.BIG_DECIMAL_SCALE,BigDecimal.ROUND_HALF_DOWN));
 * ضرب// System.out.println("multiply = " +
 * bg1.multiply(bg2).setScale(OracleUtility.BIG_DECIMAL_SCALE,BigDecimal.ROUND_HALF_DOWN));
 * تقسیم// System.out.println("divide = " +
 * bg1.divide(bg1,OracleUtility.BIG_DECIMAL_SCALE,BigDecimal.ROUND_HALF_DOWN));
 * <p>
 * // System.out.println("مثبت/////////////////////////////////"); //
 * System.out.println("CEILING = " + bg1.setScale(scale,RoundingMode.CEILING));
 * // System.out.println("DOWN = " + bg1.setScale(scale,RoundingMode.DOWN)); //
 * System.out.println("FLOOR = " + bg1.setScale(scale,RoundingMode.FLOOR)); //
 * System.out.println("UP = " + bg1.setScale(scale,RoundingMode.UP)); // //
 * System.out.println("HALF_EVEN = " +
 * bg1.setScale(scale,RoundingMode.HALF_EVEN)); // System.out.println("HALF_DOWN
 * = " + bg1.setScale(scale,RoundingMode.HALF_DOWN)); //
 * System.out.println("HALF_UP = " + bg1.setScale(scale,RoundingMode.HALF_UP));
 * // // System.out.println("منفی/////////////////////////////////"); // //
 * System.out.println("CEILING = " + bg2.setScale(scale,RoundingMode.CEILING));
 * // System.out.println("DOWN = " + bg2.setScale(scale,RoundingMode.DOWN)); //
 * System.out.println("FLOOR = " + bg2.setScale(scale,RoundingMode.FLOOR)); //
 * System.out.println("UP = " + bg2.setScale(scale,RoundingMode.UP)); // //
 * System.out.println("HALF_EVEN = " +
 * bg2.setScale(scale,RoundingMode.HALF_EVEN)); // System.out.println("HALF_DOWN
 * = " + bg2.setScale(scale,RoundingMode.HALF_DOWN)); //
 * System.out.println("HALF_UP = " + bg2.setScale(scale,RoundingMode.HALF_UP));
 * <p>
 * }
 */
