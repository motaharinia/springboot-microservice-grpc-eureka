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
 * Description: <br>
 * این کلاس برای جابجا کردن فیلد تاریخ-زمان در مدلها از شمسی به میلادی در زمان ارسال درخواست از کلاینت به سرور و بالعکس استفاده میشود
 */
public class CustomDateTime implements Comparable, Serializable {

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
    /**
     * ساعت در تاریخ شمسی
     */
    private Integer hour;
    /**
     * دقیقه در تاریخ شمسی
     */
    private Integer minute;
    /**
     * ثانیه در تاریخ شمسی
     */
    private Integer second;

    public CustomDateTime() {
    }

    @JsonCreator
    public CustomDateTime(@JsonProperty("year") Integer year, @JsonProperty("month") Integer month, @JsonProperty("day") Integer day,
                          @JsonProperty("hour") Integer hour, @JsonProperty("minute") Integer minute, @JsonProperty("second") Integer second) throws Exception {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
        deserializeDateTime();
    }

    /**
     * این متد تاریخ میلادی در ورودی دریافت میکند و یک کاستوم میلادی بر طبق آن تولید میکند
     *
     * @param date تاریخ میلادی
     */
    public CustomDateTime(Date date) {
        if (!ObjectUtils.isEmpty(date)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            this.setYear(calendar.get(Calendar.YEAR));
            this.setMonth(calendar.get(Calendar.MONTH) + 1);
            this.setDay(calendar.get(Calendar.DAY_OF_MONTH));
            this.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            this.setMinute(calendar.get(Calendar.MINUTE));
            this.setSecond(calendar.get(Calendar.SECOND));
        }
    }

    private void deserializeDateTime() throws UtilityException {
        if (ObjectUtils.isEmpty(this.year) && ObjectUtils.isEmpty(this.month) && ObjectUtils.isEmpty(this.day)) {
            return;
        }
        Locale currentLocale = LocaleContextHolder.getLocale();
        if (!validateByLocal(currentLocale.getLanguage())) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.DATE_TIME_VALIDATION_FAILED, "");
        }
        if (currentLocale.getLanguage().equals("fa")) {
            //user entered a jalali date
            String jalaiDateStringTime = this.year + "/" + this.month + "/" + this.day + " " + this.hour + ":" + this.minute + ":" + this.second;
            try {
                String gregorianDateString = CalendarTools.jalaliToGregorianDateTime(jalaiDateStringTime, "/", "/");
                String[] dateTimeParts = gregorianDateString.split(" ");
                String[] dateParts = dateTimeParts[0].split("/");
                String[] timeParts = dateTimeParts[1].split(":");
                this.year = Integer.parseInt(dateParts[0]);
                this.month = Integer.parseInt(dateParts[1]);
                this.day = Integer.parseInt(dateParts[2]);
                this.hour = Integer.parseInt(timeParts[0]);
                this.minute = Integer.parseInt(timeParts[1]);
                this.second = Integer.parseInt(timeParts[2]);
            } catch (Exception e) {
                this.year = null;
                this.month = null;
                this.day = null;
                this.hour = null;
                this.minute = null;
                this.second = null;
            }
        }
    }

    /**
     * این متد بررسی میکند که آیا متناسب با لوکال فعلی تاریخ کلاس معتبر است یا خیر
     *
     * @param locale لوکال فعلی
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
     *
     * @param customDateTime کاستوم ورودی
     * @return خروجی: نتیجه بررسی
     */
    @NotNull
    public static Boolean isEmpty(CustomDateTime customDateTime) {
        if (ObjectUtils.isEmpty(customDateTime)) {
            return true;
        }
        if (ObjectUtils.isEmpty(customDateTime.getYear()) || ObjectUtils.isEmpty(customDateTime.getMonth()) || ObjectUtils.isEmpty(customDateTime.getDay()) || ObjectUtils.isEmpty(customDateTime.getHour()) || ObjectUtils.isEmpty(customDateTime.getMinute()) || ObjectUtils.isEmpty(customDateTime.getSecond())) {
            return true;
        }
        return false;
    }

    /**
     * این متد یک رشته جداکننده تاریخ از ورودی دریافت میکند و در صورت خالی نبودن کلاس ، رشته تاریخ-زمان متناسب با فیلدهای کلاس را با فرمت مورد نظر ورودی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ-زمان
     */
    @NotNull
    public String getFormattedString(@NotNull String dateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(getClass(), UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        if (!CustomDateTime.isEmpty(this)) {
            return year + dateDelimiter + CalendarTools.fixOneDigit(month.toString()) + dateDelimiter + CalendarTools.fixOneDigit(day.toString()) + " " + CalendarTools.fixOneDigit(hour.toString()) + ":" + CalendarTools.fixOneDigit(minute.toString()) + ":" + CalendarTools.fixOneDigit(second.toString());
        } else {
            return year + dateDelimiter + month + dateDelimiter + day + " " + hour+ ":" + minute+ ":" + second;
        }
    }

    @Override
    public String toString() {
        try {
            return "CustomDateTime{" + this.getFormattedString("-") + '}';
        } catch (UtilityException e) {
            return "CustomDateTime{" + e.getMessage() + '}';
        }
    }

    @Override
    public int compareTo(Object customDate) {

        CustomDateTime compareCustomDateTime = (CustomDateTime) customDate;
        Integer ret = 0;
        if ((this.getYear() == null) || (compareCustomDateTime.getYear() == null)) {
            ret = 0;
        } else {
            if (Objects.equals(this.getYear(), compareCustomDateTime.getYear())) {
                if ((this.getMonth() == null) || (compareCustomDateTime.getMonth() == null)) {
                    ret = 0;
                } else {
                    if (Objects.equals(this.getMonth(), compareCustomDateTime.getMonth())) {
                        if ((this.getDay() == null) || (compareCustomDateTime.getDay() == null)) {
                            ret = 0;
                        } else {
                            if (Objects.equals(this.getDay(), compareCustomDateTime.getDay())) {
                                if ((this.getHour() == null) || (compareCustomDateTime.getHour() == null)) {
                                    ret = 0;
                                } else {
                                    if (Objects.equals(this.getHour(), compareCustomDateTime.getHour())) {
                                        if ((this.getMinute() == null) || (compareCustomDateTime.getMinute() == null)) {
                                            ret = 0;
                                        } else {
                                            if (Objects.equals(this.getMinute(), compareCustomDateTime.getMinute())) {
                                                if ((this.getSecond() == null) || (compareCustomDateTime.getSecond() == null)) {
                                                    ret = 0;
                                                } else {
                                                    ret = this.getSecond() - compareCustomDateTime.getSecond();
                                                }
                                            } else {
                                                ret = this.getMinute() - compareCustomDateTime.getMinute();
                                            }
                                        }
                                    } else {
                                        ret = this.getHour() - compareCustomDateTime.getHour();
                                    }
                                }
                            } else {
                                ret = this.getDay() - compareCustomDateTime.getDay();
                            }
                        }
                    } else {
                        ret = this.getMonth() - compareCustomDateTime.getMonth();
                    }
                }
            } else {
                ret = this.getYear() - compareCustomDateTime.getYear();
            }
        }
        return ret;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.year);
        hash = 97 * hash + Objects.hashCode(this.month);
        hash = 97 * hash + Objects.hashCode(this.day);
        hash = 97 * hash + Objects.hashCode(this.hour);
        hash = 97 * hash + Objects.hashCode(this.minute);
        hash = 97 * hash + Objects.hashCode(this.second);
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
        final CustomDateTime other = (CustomDateTime) obj;
        if (!Objects.equals(this.year, other.year)) {
            return false;
        }
        if (!Objects.equals(this.month, other.month)) {
            return false;
        }
        if (!Objects.equals(this.day, other.day)) {
            return false;
        }
        if (!Objects.equals(this.hour, other.hour)) {
            return false;
        }
        if (!Objects.equals(this.minute, other.minute)) {
            return false;
        }
        if (!Objects.equals(this.second, other.second)) {
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

    public Integer getHour() {
        return hour;
    }

    public void setHour(Integer hour) {
        this.hour = hour;
    }

    public Integer getMinute() {
        return minute;
    }

    public void setMinute(Integer minute) {
        this.minute = minute;
    }

    public Integer getSecond() {
        return second;
    }

    public void setSecond(Integer second) {
        this.second = second;
    }

}
