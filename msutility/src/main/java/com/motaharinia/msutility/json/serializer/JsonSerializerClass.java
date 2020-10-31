package com.motaharinia.msutility.json.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-17<br>
 * Time: 12:09:17<br>
 * Description:<br>
 * این کلاس برای تبدیل کلاس جاوا به رشته جیسون مسیر کلاس جاوا در کلاینت میباشد
 */
public class JsonSerializerClass  extends JsonSerializer<Object> {

    @Override
    public void serialize(Object object, JsonGenerator jsonGen, SerializerProvider sp) throws IOException {
        if(!ObjectUtils.isEmpty(object)) {
            jsonGen.writeString(object.getClass().getName());
        }else{
            jsonGen.writeString("");
        }
    }
}
