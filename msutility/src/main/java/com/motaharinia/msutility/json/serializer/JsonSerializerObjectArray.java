package com.motaharinia.msutility.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.motaharinia.msutility.calendar.CalendarTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس برای تبدیل آرایه ای از آبجکت به رشته جیسون برای ارسال به سمت کلاینت میباشد
 */
public class JsonSerializerObjectArray extends JsonSerializer<Object[]> {

    @Autowired
    public MessageSource messageSource;

    @Override
    public void serialize(Object[] objectArray, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
        if (!ObjectUtils.isEmpty(objectArray)) {
            try {
//                System.out.println("--------- JsonSerializerObjectArray.serialize: objectArray:" + Arrays.stream(objectArray).map(item -> item.getClass() + "/" + item.toString()).collect(Collectors.joining(",")));
                for (int i = 0; i < objectArray.length; i++) {
                    if (!ObjectUtils.isEmpty(objectArray[i])) {
                        if (Date.class.equals(objectArray[i].getClass())) {
                            objectArray[i] = CalendarTools.fixToLocaleDate((Date) objectArray[i], "/", LocaleContextHolder.getLocale());
                        } else if (Timestamp.class.equals(objectArray[i].getClass())) {
                            Timestamp timestamp = (Timestamp) objectArray[i];
                            objectArray[i] = CalendarTools.fixToLocaleDate(new Date(timestamp.getTime()), "/", LocaleContextHolder.getLocale());
                        } else if (String.class.equals(objectArray[i].getClass())) {
                            String objString = (String) objectArray[i];
                            if (objString.length() > 7) {
                                if ((!ObjectUtils.isEmpty(messageSource)) && ("etcItem.".equals(objString.substring(0, 8)))) {
                                    objString = messageSource.getMessage(objString, new Object[]{}, LocaleContextHolder.getLocale());
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            gen.writeObject(objectArray);
        }
    }
}
