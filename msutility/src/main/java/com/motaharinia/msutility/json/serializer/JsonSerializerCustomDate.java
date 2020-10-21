package com.motaharinia.msutility.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.motaharinia.msutility.calendar.CalendarTools;
import com.motaharinia.msutility.customfield.CustomDate;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس برای تبدیل کلاس تاریخ میلادی به رشته جیسون تاریخ جلالی برای ارسال به سمت کلاینت میباشد
 */
public class JsonSerializerCustomDate extends JsonSerializer<CustomDate> {

    @Override
    public void serialize(CustomDate customDate, JsonGenerator jg, SerializerProvider sp) {
        try {
            //برای حفظ ترتیب درج بجای هشمپ از لینکدهشمپ استفاده میکنیم
            LinkedHashMap<String, String> output = new LinkedHashMap<>();
            if (CustomDate.isEmpty(customDate)) {
                output.put("year", "");
                output.put("month", "");
                output.put("day", "");
            } else {
                Locale currentLocale = LocaleContextHolder.getLocale();
                if (currentLocale.getLanguage().equals("fa")) {
                    Date date = CalendarTools.getDateFromCustomDate(customDate);
                    customDate = CalendarTools.gregorianToJalaliDate(date);
                }
                output.put("year", customDate.getYear().toString());
                output.put("month", CalendarTools.fixOneDigit(customDate.getMonth().toString()));
                output.put("day", CalendarTools.fixOneDigit(customDate.getDay().toString()));
            }
            jg.writeObject(output);
        } catch (Exception ex) {
            Logger.getLogger(JsonSerializerCustomDate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
