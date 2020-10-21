package com.motaharinia.msutility.calendar;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customfield.CustomDateTime;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  اینترفیس متدهای ابزاری تاریخ و زمان میلادی و جلالی
 */
public interface CalendarTools {

    /**
     * کلاس مبدل تاریخ میلادی و جلالی
     */
    JalaliCalendar jalaliCalendar = new JalaliCalendar();

    //--------------------------------------------------متدهای کمکی--------------------------------------------------

    /**
     * این متد عدد را اگر یک رقمی باشد برای نمایش مرتب در خروجی دورقمی میکند
     *
     * @param inputDigit عدد ورودی
     * @return خروجی: عدد دورقمی شده
     */
    @NotNull
    static String fixOneDigit(@NotNull String inputDigit) throws UtilityException {
        if (ObjectUtils.isEmpty(inputDigit)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputDigit");
        }
        if (inputDigit.length() > 1) {
            return inputDigit;
        } else {
            return "0" + inputDigit;
        }
    }

    /**
     * این متد یک رشته تاریخ و یک رشته جداکننده به عنوان ورودی میگیرد و سپس عددهای روز و ماه رشته تاریخ ورودی را اگر تک رقمی باشند دورقمی میکند و اصلاح مینماید
     *
     * @param inputDateString رشته تاریخ ورودی
     * @param delimiter       رشته جداکننده
     * @return خروجی: رشته تاریخ اصلاح شده
     */
    @NotNull
    static String fixDateSlash(@NotNull String inputDateString, @NotNull String delimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(inputDateString)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "inputDateString");
        }
        if (ObjectUtils.isEmpty(delimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "delimiter");
        }
        String[] tmpArray = inputDateString.split("/", -1);
        if (tmpArray.length != 3) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.INCORRECT_STRING_DATE_FORMAT, "inputDateString");
        }
        return tmpArray[0] + delimiter + fixOneDigit(tmpArray[1]) + delimiter + fixOneDigit(tmpArray[2]);
    }


    //--------------------------------------------------متدهای دریافت تاریخ و زمان فعلی--------------------------------------------------

    /**
     * این متد تاریخ فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ میلادی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ میلادی فعلی
     */
    @NotNull
    static String getCurrentGregorianDateString(@NotNull String dateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + dateDelimiter + "MM" + dateDelimiter + "dd", Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }

    /**
     * این متد تاریخ و ساعت فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ میلادی و ساعت خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ میلادی و ساعت فعلی
     */
    @NotNull
    static String getCurrentGregorianDateTimeString(@NotNull String dateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + dateDelimiter + "MM" + dateDelimiter + "dd HH:mm:ss", Locale.ENGLISH);
        return simpleDateFormat.format(new Date());
    }

    /**
     * این متد تاریخ فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ جلالی خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ جلالی فعلی
     */
    @NotNull
    static String getCurrentJalaliDateString(@NotNull String dateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        return fixDateSlash(jalaliCalendar.getJalaliDate(new Date()), dateDelimiter);
    }

    /**
     * این متد تاریخ و ساعت فعلی سیستم را با توجه به رشته جدا کننده تاریخ ورودی به صورت رشته تاریخ جلالی و ساعت خروجی میدهد
     *
     * @param dateDelimiter رشته جدا کننده تاریخ
     * @return خروجی: رشته تاریخ جلالی و ساعت فعلی
     */
    @NotNull
    static String getCurrentJalaliDateTimeString(@NotNull String dateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(dateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
        return fixDateSlash(jalaliCalendar.getJalaliDate(new Date()), dateDelimiter) + " " + simpleDateFormat.format(new Date());
    }


    //--------------------------------------------------متدهای تبدیل تاریخ جلالی به میلادی--------------------------------------------------

    /**
     * این متد رشته تاریخ جلالی و رشته جدا کننده تاریخ جلالی را از ورودی دریافت میکند و Date میلادی خروجی میدهد
     *
     * @param sourceDate          رشته تاریخ جلالی
     * @param sourceDateDelimiter رشته جدا کننده تاریخ جلالی
     * @return خروجی: Date میلادی
     */
    @NotNull
    static Date jalaliToGregorianDate(@NotNull String sourceDate, @NotNull String sourceDateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(sourceDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDate");
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateDelimiter");
        }
        Date destination = jalaliCalendar.getGregorianDate(sourceDate.replaceAll(sourceDateDelimiter, "/"));
        return destination;
    }

    /**
     * این متد رشته تاریخ جلالی و رشته جدا کننده تاریخ جلالی و رشته جدا کننده تاریخ میلادی را از ورودی دریافت میکند و رشته تاریخ-زمان میلادی آن را بر اساس رشته جدا کننده میلادی خروجی میدهد
     *
     * @param sourceDate               رشته تاریخ جلالی
     * @param sourceDateDelimiter      رشته جدا کننده تاریخ جلالی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ میلادی
     * @return خروجی: رشته میلادی
     */
    @NotNull
    static String jalaliToGregorianDate(@NotNull String sourceDate, String sourceDateDelimiter, String destinationDateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(sourceDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDate");
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateDelimiter");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + destinationDateDelimiter + "MM" + destinationDateDelimiter + "dd", Locale.ENGLISH);
        Date destination = jalaliToGregorianDate(sourceDate, sourceDateDelimiter);
        return simpleDateFormat.format(destination);
    }

    //--------------------------------------------------متدهای تبدیل تاریخ-زمان جلالی به میلادی--------------------------------------------------

    /**
     * این متد رشته تاریخ-زمان جلالی و رشته جدا کننده تاریخ جلالی را از ورودی دریافت میکند و Date میلادی خروجی میدهد
     *
     * @param sourceDateTime      رشته تاریخ-زمان جلالی
     * @param sourceDateDelimiter رشته جدا کننده تاریخ جلالی
     * @return خروجی: Date میلادی
     */
    @NotNull
    static Date jalaliToGregorianDateTime(@NotNull String sourceDateTime, @NotNull String sourceDateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(sourceDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateTime");
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateDelimiter");
        }
        String[] sourceDateArray = sourceDateTime.split(" ", -1);
        String[] sourceTimeArray = sourceDateArray[1].split(":", -1);
        String sourceDate = sourceDateArray[0];
        Date destination = jalaliCalendar.getGregorianDate(sourceDate.replaceAll(sourceDateDelimiter, "/"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(destination);
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(sourceTimeArray[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(sourceTimeArray[1]));
        calendar.set(Calendar.SECOND, Integer.parseInt(sourceTimeArray[2]));
        calendar.set(Calendar.MILLISECOND, 0);
        destination = calendar.getTime();
        return destination;
    }

    /**
     * این متد رشته تاریخ-زمان جلالی و رشته جدا کننده تاریخ جلالی و رشته جدا کننده تاریخ میلادی را از ورودی دریافت میکند و رشته تاریخ-زمان میلادی آن را بر اساس رشته جدا کننده میلادی خروجی میدهد
     *
     * @param sourceDateTime           رشته تاریخ-زمان جلالی
     * @param sourceDateDelimiter      رشته جدا کننده تاریخ جلالی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ میلادی
     * @return خروجی: رشته تاریخ-زمان میلادی
     */
    @NotNull
    static String jalaliToGregorianDateTime(@NotNull String sourceDateTime, @NotNull String sourceDateDelimiter, @NotNull String destinationDateDelimiter) throws UtilityException {
        if (ObjectUtils.isEmpty(sourceDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateTime");
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateDelimiter");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + destinationDateDelimiter + "MM" + destinationDateDelimiter + "dd", Locale.ENGLISH);
        Date destination = jalaliToGregorianDateTime(sourceDateTime, sourceDateDelimiter);
        String[] sourceDateArray = sourceDateTime.split(" ", -1);
        return simpleDateFormat.format(destination) + " " + sourceDateArray[1];
    }


    //--------------------------------------------------متدهای تبدیل تاریخ میلادی به جلالی--------------------------------------------------

    /**
     * این متد رشته تاریخ میلادی و رشته جدا کننده تاریخ میلادی و رشته جدا کننده تاریخ جلالی را از ورودی دریافت میکند و رشته تاریخ جلالی آن را بر اساس رشته جدا کننده جلالی خروجی میدهد
     *
     * @param sourceDate               رشته تاریخ میلادی
     * @param sourceDateDelimiter      رشته جدا کننده تاریخ میلادی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ جلالی
     * @return خروجی: رشته تاریخ جلالی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String gregorianToJalaliDate(@NotNull String sourceDate, @NotNull String sourceDateDelimiter, @NotNull String destinationDateDelimiter) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(sourceDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDate");
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateDelimiter");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + sourceDateDelimiter + "MM" + sourceDateDelimiter + "dd", Locale.ENGLISH);
        Date destination = simpleDateFormat.parse(sourceDate);
        String destinationDate = jalaliCalendar.getJalaliDate(destination);
        return fixDateSlash(destinationDate, destinationDateDelimiter);
    }

    /**
     * این متد تاریخ Date میلادی را از ورودی دریافت میکند و CustomDate جلالی آن را خروجی میدهد
     *
     * @param source پارامتر Date میلادی
     * @return خروجی: CustomDate جلالی
*/
    @NotNull
    static CustomDate gregorianToJalaliDate(@NotNull Date source)  {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        CustomDate destinationCustomDate = new CustomDate();
        String destinationDate = jalaliCalendar.getJalaliDate(source);
        String[] destinationDateArray = destinationDate.split("/");
        destinationCustomDate.setYear(Integer.parseInt(destinationDateArray[0]));
        destinationCustomDate.setMonth(Integer.parseInt(destinationDateArray[1]));
        destinationCustomDate.setDay(Integer.parseInt(destinationDateArray[2]));
        return destinationCustomDate;
    }

    //--------------------------------------------------متدهای تبدیل تاریخ-زمان میلادی به جلالی--------------------------------------------------

    /**
     * این متد رشته تاریخ-زمان میلادی و رشته جدا کننده تاریخ میلادی و رشته جدا کننده تاریخ جلالی را از ورودی دریافت میکند و رشته تاریخ-زمان جلالی آن را بر اساس رشته جدا کننده جلالی خروجی میدهد
     *
     * @param sourceDateTime           رشته تاریخ-زمان میلادی
     * @param sourceDateDelimiter      رشته جدا کننده تاریخ میلادی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ جلالی
     * @return خروجی: رشته تاریخ-زمان جلالی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String gregorianToJalaliDateTime(@NotNull String sourceDateTime, @NotNull String sourceDateDelimiter, @NotNull String destinationDateDelimiter) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(sourceDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateTime");
        }
        if (ObjectUtils.isEmpty(sourceDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateDelimiter");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy" + sourceDateDelimiter + "MM" + sourceDateDelimiter + "dd", Locale.ENGLISH);
        String[] sourceDateTimeArray = sourceDateTime.split(" ", -1);
        String sourceDate = sourceDateTimeArray[0];
        Date destination = simpleDateFormat.parse(sourceDate);
        String destinationDate = jalaliCalendar.getJalaliDate(destination);
        return fixDateSlash(destinationDate, destinationDateDelimiter) + " " + sourceDateTimeArray[1];
    }

    /**
     * این متد تاریخ Date میلادی را از ورودی دریافت میکند و CustomDateTime جلالی آن را خروجی میدهد
     *
     * @param source پارامتر Date میلادی
     * @return خروجی: CustomDateTime جلالی
*/
    @NotNull
    static CustomDateTime gregorianToJalaliDateTime(@NotNull Date source)  {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        CustomDateTime destinationCustomDateTime = new CustomDateTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(source);
        String destinationDate = jalaliCalendar.getJalaliDate(source);
        String[] destinationDateArray = destinationDate.split("/");
        destinationCustomDateTime.setYear(Integer.parseInt(destinationDateArray[0]));
        destinationCustomDateTime.setMonth(Integer.parseInt(destinationDateArray[1]));
        destinationCustomDateTime.setDay(Integer.parseInt(destinationDateArray[2]));
        destinationCustomDateTime.setHour(calendar.get(Calendar.HOUR_OF_DAY));
        destinationCustomDateTime.setMinute(calendar.get(Calendar.MINUTE));
        destinationCustomDateTime.setSecond(calendar.get(Calendar.SECOND));
        return destinationCustomDateTime;
    }

    //--------------------------------------------------متدهای اصلاح کننده متناسب با زبان لوکال و تفاوت زمانی بین دو تاریخ--------------------------------------------------

    /**
     * این متد رشته تاریخ و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و رشته تاریخ ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ خروجی میدهد
     *
     * @param sourceDate               رشته تاریخ ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String fixToLocaleDate(@NotNull String sourceDate, @NotNull String destinationDateDelimiter, Locale locale) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(sourceDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDate");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        if (locale.equals(null)) {
            locale = LocaleContextHolder.getLocale();
        }
        String destinationDate = "";
        sourceDate = sourceDate.replaceAll("/", "-");
        String[] sourceDateArray = sourceDate.split("-", -1);
        //بر اساس سال متوجه میشویم که رشته تاریخ ورودی میلادی است یا جلالی
        if (Integer.parseInt(sourceDateArray[0]) > 1500) {
            //اگر رشته تاریخ ورودی میلادی است و لوکال داده شده فارسی است آن را تبدیل میکنیم در غیر این صورت همان رشته تاریخ ورودی را به عنوان خروجی انتخاب میکنیم
            if (locale.getLanguage().equals("fa")) {
                destinationDate = CalendarTools.gregorianToJalaliDate(sourceDate, "-", destinationDateDelimiter);
            } else {
                destinationDate = sourceDate;
            }
        } else {
            //اگر رشته تاریخ ورودی جلالی است و لوکال داده شده غیر فارسی است آن را تبدیل میکنیم در غیر این صورت همان رشته تاریخ ورودی را به عنوان خروجی انتخاب میکنیم
            if (!locale.getLanguage().equals("fa")) {
                destinationDate = CalendarTools.jalaliToGregorianDate(sourceDate, "-", destinationDateDelimiter);
            } else {
                destinationDate = sourceDate;
            }
        }
        destinationDate = destinationDate.replaceAll("-", destinationDateDelimiter);
        destinationDate = destinationDate.replaceAll("/", destinationDateDelimiter);
        return destinationDate;
    }

    /**
     * این متد Date تاریخ و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و Date تاریخ ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ خروجی میدهد
     *
     * @param source                   Date تاریخ ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String fixToLocaleDate(@NotNull Date source, @NotNull String destinationDateDelimiter, Locale locale) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        return fixToLocaleDate(simpleDateFormat.format(source), destinationDateDelimiter, locale);
    }

    /**
     * این متد CustomDate تاریخ و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و CustomDate تاریخ ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ خروجی میدهد
     *
     * @param customDate               CustomDate تاریخ ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String fixToLocaleDate(@NotNull CustomDate customDate, @NotNull String destinationDateDelimiter, Locale locale) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(customDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDate");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        return fixToLocaleDate(customDate.getFormattedString("-"), destinationDateDelimiter, locale);
    }

    /**
     * این متد رشته تاریخ-زمان و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و رشته تاریخ-زمان ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ-زمان خروجی میدهد
     *
     * @param sourceDateTime           رشته تاریخ-زمان ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ-زمان متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String fixToLocaleDateTime(@NotNull String sourceDateTime, @NotNull String destinationDateDelimiter, Locale locale) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(sourceDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceDateTime");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        if (locale.equals(null)) {
            locale = LocaleContextHolder.getLocale();
        }
        String destinationDateTime = "";

        sourceDateTime = sourceDateTime.replaceAll("/", "-");
        String[] sourceDateTimeArray = sourceDateTime.split(" ", -1);
        String sourceDate = sourceDateTimeArray[0];
        String[] sourceDateArray = sourceDate.split("-", -1);
        if (Integer.parseInt(sourceDateArray[0]) > 1500) {
            if (locale.getLanguage().equals("fa")) {
                destinationDateTime = CalendarTools.gregorianToJalaliDate(sourceDate, "-", destinationDateDelimiter) + " " + sourceDateTimeArray[1];
            } else {
                destinationDateTime = sourceDateTime;
            }
        } else {
            if (!locale.getLanguage().equals("fa")) {
                destinationDateTime = CalendarTools.jalaliToGregorianDate(sourceDate, "-", destinationDateDelimiter) + " " + sourceDateTimeArray[1];
            } else {
                destinationDateTime = sourceDateTime;
            }
        }
        destinationDateTime = destinationDateTime.replaceAll("-", destinationDateDelimiter);
        destinationDateTime = destinationDateTime.replaceAll("/", destinationDateDelimiter);
        return destinationDateTime;
    }

    /**
     * این متد Date تاریخ-زمان و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و Date تاریخ-زمان ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ-زمان خروجی میدهد
     *
     * @param source                   Date تاریخ-زمان ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ-زمان متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String fixToLocaleDateTime(@NotNull Date source, @NotNull String destinationDateDelimiter, Locale locale) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(source)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "source");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        return fixToLocaleDateTime(simpleDateFormat.format(source), destinationDateDelimiter, locale);
    }

    /**
     * این متد CustomDateTime تاریخ-زمان و رشته جدا کننده تاریخ خروجی و زبان لوکال (زبان انتخابی کاربر در کلاینت) را از ورودی دریافت میکند و CustomDate تاریخ-زمان ورودی را متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی ، به صورت رشته تاریخ-زمان خروجی میدهد
     *
     * @param customDateTime           CustomDateTime تاریخ-زمان ورودی
     * @param destinationDateDelimiter رشته جدا کننده تاریخ خروجی
     * @param locale                   زبان لوکال کلاینت
     * @return خروجی: رشته تاریخ متناسب با زبان لوکال ورودی (اگر زبان فارسی است شمسی و در غیر این صورت میلادی) و رشته جدا کننده خروجی
     * @throws ParseException این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    static String fixToLocaleDateTime(@NotNull CustomDateTime customDateTime, @NotNull String destinationDateDelimiter, Locale locale) throws UtilityException, ParseException {
        if (ObjectUtils.isEmpty(customDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDateTime");
        }
        if (ObjectUtils.isEmpty(destinationDateDelimiter)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDateDelimiter");
        }
        return fixToLocaleDateTime(customDateTime.getFormattedString("-"), destinationDateDelimiter, locale);
    }

    /**
     * این متد دو Date میلادی را از ورودی دریافت میکند و میزان اختلاف آن دو تاریخ را براساس واحد زمانی دلخواه خروجی میدهد
     *
     * @param date1            تاریخ1
     * @param date2            تاریخ2
     * @param dateTimeUnitEnum واحد زمانی
     * @return خروجی: میزان اختلاف آن تاریخ ورودی براساس واحد زمانی ورودی
     */
    @NotNull
    static long getTwoDateDifference(@NotNull Date date1, @NotNull Date date2, @NotNull DateTimeUnitEnum dateTimeUnitEnum) throws UtilityException {
        if (ObjectUtils.isEmpty(date1)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "date1");
        }
        if (ObjectUtils.isEmpty(date2)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "date2");
        }
        if (ObjectUtils.isEmpty(dateTimeUnitEnum)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "dateTimeUnitEnum");
        }
        long differenceInMillisecond = (date2.getTime() - date1.getTime());
        switch (dateTimeUnitEnum) {
            case SECOND:
                differenceInMillisecond = differenceInMillisecond / 1000;
                break;
            case MINUTE:
                differenceInMillisecond = differenceInMillisecond / (1000 * 60);
                break;
            case HOUR:
                differenceInMillisecond = differenceInMillisecond / (1000 * 60 * 60);
                break;
            case DAY:
                differenceInMillisecond = differenceInMillisecond / (1000 * 60 * 60 * 24);
                break;
            case MONTH:
                differenceInMillisecond = differenceInMillisecond / (1000 * 60 * 60 * 24 * 30);
                break;
            case YEAR:
                differenceInMillisecond = differenceInMillisecond / (1000 * 60 * 60 * 24 * 30 * 12);
                break;
        }
        return differenceInMillisecond;
    }

    //--------------------------------------------------متدهای تبدیلی CustomDate و CustomDateTime به Date--------------------------------------------------

    /**
     * این متد مطابق با CustomDate میلادی ورودی ، Date میلادی متناسب را خروجی میدهد
     *
     * @param customDate پارامتر CustomDate میلادی
     * @return خروجی: Date میلادی
     */
    @NotNull
    static Date getDateFromCustomDate(@NotNull CustomDate customDate) throws UtilityException {
        if (CustomDate.isEmpty(customDate)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDate");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, customDate.getYear());
        calendar.set(Calendar.MONTH, customDate.getMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, customDate.getDay());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * این متد مطابق با CustomDateTime میلادی ورودی ، Date میلادی متناسب را خروجی میدهد
     *
     * @param customDateTime پارامتر CustomDateTime میلادی
     * @return خروجی: Date میلادی
     */
    @NotNull
    static Date getDateFromCustomDateTime(@NotNull CustomDateTime customDateTime) throws UtilityException {
        if (CustomDateTime.isEmpty(customDateTime)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "customDateTime");
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, customDateTime.getYear());
        calendar.set(Calendar.MONTH, customDateTime.getMonth() - 1);
        calendar.set(Calendar.DAY_OF_MONTH, customDateTime.getDay());
        calendar.set(Calendar.HOUR_OF_DAY, customDateTime.getHour());
        calendar.set(Calendar.MINUTE, customDateTime.getMinute());
        calendar.set(Calendar.SECOND, customDateTime.getSecond());
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    //--------------------------------------------------متدهای بررسی کننده تاریخهای جلالی و میلادی--------------------------------------------------

    /**
     * این متد صحت تاریخ جلالی ورودی را بررسی میکند
     *
     * @param year  سال تاریخ جلالی
     * @param month ماه تاریخ جلالی
     * @param day   روز تاریخ جلالی
     * @return خروجی: نتیجه بررسی صحت تاریخ
     */
    @NotNull
    static Boolean checkJalaliDateValidity(@NotNull Integer year, @NotNull Integer month, @NotNull Integer day) throws UtilityException {
        if (ObjectUtils.isEmpty(year)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "year");
        }
        if (ObjectUtils.isEmpty(month)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "month");
        }
        if (ObjectUtils.isEmpty(day)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "day");
        }
        if (year <= 0 || month <= 0 || month > 12 || day <= 0) {
            return false;
        }
        if ((month <= 6 && day > 31) || (month > 6 && day > 30)) {
            return false;
        }
        return true;
    }

    /**
     * این متد صحت تاریخ میلادی ورودی را بررسی میکند
     *
     * @param year  سال تاریخ میلادی
     * @param month ماه تاریخ میلادی
     * @param day   روز تاریخ میلادی
     * @return خروجی: نتیجه بررسی صحت تاریخ
     */
    @NotNull
    static Boolean checkGregorianDateValidity(@NotNull Integer year, @NotNull Integer month, @NotNull Integer day) throws UtilityException {
        if (ObjectUtils.isEmpty(year)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "year");
        }
        if (ObjectUtils.isEmpty(month)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "month");
        }
        if (ObjectUtils.isEmpty(day)) {
            throw new UtilityException(CalendarTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "day");
        }
        boolean result = true;
        String dateFormat = "yyyy/MM/dd";
        String dateString = year + "/" + month + "/" + day;
        DateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
        simpleDateFormat.setLenient(false);
        try {
            simpleDateFormat.parse(dateString);
        } catch (ParseException ex) {
            result = false;
        }
        return result;
    }
}
