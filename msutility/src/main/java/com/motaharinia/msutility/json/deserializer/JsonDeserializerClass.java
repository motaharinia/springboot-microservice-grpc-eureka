package com.motaharinia.msutility.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.motaharinia.msutility.json.serializer.JsonSerializerCustomDate;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-17<br>
 * Time: 08:06:23<br>
 * Description:<br>
 *     این کلاس برای تبدیل رشته جیسون مسیر کلاس جاوا در کلاینت به کلاس جاوا میباشد
 */
public class JsonDeserializerClass extends JsonDeserializer<Class> {
    @Override
    public Class deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        String className = jp.getText();
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(JsonSerializerCustomDate.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
