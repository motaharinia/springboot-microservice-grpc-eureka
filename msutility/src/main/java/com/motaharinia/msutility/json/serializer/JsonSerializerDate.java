package com.motaharinia.msutility.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.motaharinia.msutility.calendar.CalendarTools;
import com.motaharinia.msutility.customexception.UtilityException;
import org.springframework.context.i18n.LocaleContextHolder;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس برای تبدیل کلاس Date میلادی به رشته جیسون تاریخ-زمان جلالی برای ارسال به سمت کلاینت میباشد
 */
public class JsonSerializerDate extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGen, SerializerProvider sp) throws IOException, JsonProcessingException {
        try {
            jsonGen.writeString( CalendarTools.fixToLocaleDate(date,"/",LocaleContextHolder.getLocale()));
        } catch (ParseException | UtilityException ex) {
            Logger.getLogger(JsonSerializerCustomDateTime.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
