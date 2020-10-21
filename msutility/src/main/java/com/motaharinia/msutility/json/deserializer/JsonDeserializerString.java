package com.motaharinia.msutility.json.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.string.StringTools;
import org.springframework.util.ObjectUtils;

import java.io.IOException;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * این کلاس برای تبدیل رشته جیسون کلاینت به رشته میباشد<br>
 * این کلاس در صورتی که رشته جیسون داده شده از نوع فیلدهای با تگ وب باشد آن را خروجی میدهد<br>
 * در غیر این صورت اگر فیلد رشته مورد نظر فیلد رشته ساده باشد تگهای وب را که مشکل امنیتی ممکن است ایجاد کنند از رشته جیسون حذف مینماید
 */
public class JsonDeserializerString extends JsonDeserializer<String> {

    @Override
    public String deserialize(JsonParser jp, DeserializationContext dc) throws IOException, JsonProcessingException {
        if (ObjectUtils.isEmpty(jp.getCurrentName()) || ObjectUtils.isEmpty(jp.getText())) {
            return jp.getText();
        } else {
            if (jp.getCurrentName().toLowerCase().equals("htmlCustomString".toLowerCase())) {
                return jp.getText();
            } else {
                try {
                    return StringTools.removeHtml(jp.getText());
                } catch (UtilityException e) {
                    return jp.getText();
                }
            }
        }
    }
}
