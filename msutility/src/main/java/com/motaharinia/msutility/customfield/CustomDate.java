package com.motaharinia.msutility.customfield;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.motaharinia.msutility.calendar.CalendarTools;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس برای جابجا کردن فیلد تاریخ در مدلها از شمسی به میلادی در زمان ارسال درخواست از کلاینت به سرور و بالعکس استفاده میشود
 */
public class CustomDate implements Comparable, Serializable {

    /**
     * سال در تاریخ شمسی
     */
    private Integer year;
    /**
     * ماه در تاریخ شمسی
     */
    private Integer month;
    /**
     * روز در تاریخ شمسی
     */
    private Integer day;

    public CustomDate() {
    }

    @JsonCreator
    public CustomDate(@JsonProperty("year") Integer year, @JsonProperty("month") Integer month, @JsonProperty("day") Integer day) throws Exception {
        this.year = year;
        this.month = month;
        this.day = day;
        deserializeDate();
    }

    /**
     *این متد تاریخ میلادی در ورودی دریافت میکند و یک کاستوم میلادی بر طبق آن تولید میکند
     * @param date تاریخ میلادی
     */
    public CustomDate( Date date) {
        if (!ObjectUtils.isEmpty(date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.setYear(calendar.get(Calendar.YEAR));
            this.setMonth(calendar.get(Calendar.MONTH) + 1);
            this.setDay(calendar.get(Calendar.DAY_OF_MONTH));
        }
    }

    private void deserializeDate() throws Exception {
        if (ObjectUtils.isEmpty(this.year) && ObjectUtils.isEmpty(this.month) && ObjectUtils.isEmpty(this.day)) {
            return;
        }
        Locale currentLocale = LocaleContextHolder.getLocale();
        System.out.println("currentLocale:" + currentLocale);
        System.out.println(this.toString());
        if (!validateByLocal(currentLocale.getLanguage())) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.DATE_VALIDATION_FAILED, "");
        }
        if (currentLocale.getLanguage().equals("fa")) {
            //user entered a jalali date
            String jalaiDateString = this.year + "/" + this.month + "/" + this.day;
            try {
                String gregorianDateString = CalendarTools.jalaliToGregorianDate(jalaiDateString, "/", "/");
                String[] gregorianParts = gregorianDateString.split("/");
                this.year = Integer.parseInt(gregorianParts[0]);
                this.month = Integer.parseInt(gregorianParts[1]);
                this.day = Integer.parseInt(gregorianParts[2]);
            } catch (Exception e) {
                this.year = null;
                this.month = null;
                this.day = null;
            }
        }
    }

    /**
     * این متد بررسی میکند که آیا متناسب با لوکال فعلی تاریخ کلاس معتبر است یا خیر
     * @param locale  لوکال فعلی
     * @return خروجی: نتیجه بررسی
     * @throws UtilityException
     */
    private Boolean validateByLocal(String locale) throws UtilityException {
        if (ObjectUtils.isEmpty(locale)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "locale");
        }
        boolean result = true;
        if (locale.equals("fa") && !CalendarTools.checkJalaliDateValidity(year, month, day)) {
            result = false;
        } else if (locale.equals("en") && !CalendarTools.checkGregorianDateValidity(year, month, day)) {
            result = false;
        }
        return result;
    }

    /**
     * بررسی میکند آیا کاستوم ورودی نال یا خالی هست
     * @param customDate کاستوم ورودی
     * @return خروجی: نتیجه بررسی
     */
    @NotNull
    public static Boolean isEmpty(CustomDate customDate) {
        if (ObjectUtils.isEmpty(customDate)) {
            return true;
        }
        if (ObjectUtils.isEmpty(customDate.getYear()) || ObjectUtils.isEmpty(customDate.getMonth()) || ObjectUtils.isEmpty(customDate.getDay())) {
            return true;
        }
        return false;
    }

    /**
     * این متد یک رشته جداکننده تاریخ از ورودی دریافت میکند و در صورت خالی نبودن کلاس ، رشته تاریخ متناسب با فیلدهای کلاس را با فرمت مورد نظر ورودی خروجی میدهد
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ
     */
    @NotNull
    public String getFormattedString(@NotNull String dateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        if (!CustomDate.isEmpty(this)) {
            return year + dateDelimiter + CalendarTools.fixOneDigit(month.toString()) + dateDelimiter + CalendarTools.fixOneDigit(day.toString());
        } else {
            return year + dateDelimiter + month + dateDelimiter + day;
        }
    }

    @Override
    public String toString() {
        try {
            return "CustomDate{" + this.getFormattedString("-") + '}';
        } catch (UtilityException e) {
            return "CustomDate{" + e.getMessage() + '}';
        }
    }

    @Override
    public int compareTo(Object customDate) {
        CustomDate compareCustomDate = (CustomDate) customDate;
        Integer ret = 0;
        if ((this.getYear() == null) || (compareCustomDate.getYear() == null)) {
            ret = 0;
        } else {
            if (Objects.equals(this.getYear(), compareCustomDate.getYear())) {
                if ((this.getMonth() == null) || (compareCustomDate.getMonth() == null)) {
                    ret = 0;
                } else {
                    if (Objects.equals(this.getMonth(), compareCustomDate.getMonth())) {
                        if ((this.getDay() == null) || (compareCustomDate.getDay() == null)) {
                            ret = 0;
                        } else {
                            ret = this.getDay() - compareCustomDate.getDay();
                        }
                    } else {
                        ret = this.getMonth() - compareCustomDate.getMonth();
                    }
                }
            } else {
                ret = this.getYear() - compareCustomDate.getYear();
            }
        }
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.year);
        hash = 79 * hash + Objects.hashCode(this.month);
        hash = 79 * hash + Objects.hashCode(this.day);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this.equals(obj)) {
            return true;
        }
        if (ObjectUtils.isEmpty(obj)) {
            return false;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        final CustomDate other = (CustomDate) obj;
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (!Objects.equals(this.day, other.day)) {
            return false;
        }
        return true;
    }

    //getter-setter:
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

}
