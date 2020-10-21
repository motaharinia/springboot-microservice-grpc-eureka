//package com.motaharinia.msutility.json.deserializer;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.motaharinia.msutility.search.filter.GridFilterModel;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.IOException;
//
///**
// * User: https://github.com/motaharinia<br>
// * Date: 2020-06-12<br>
// * Time: 01:05:58<br>
// * Description:<br>
// * این کلاس برای تبدیل رشته جیسون رمزنگاری شده پارامتر مد به رشته معمولی پارامتر مد میباشد
// */
//public class JsonDeserializerGridParametersMode extends JsonDeserializer<String> {
//    @Override
//    public String deserialize(JsonParser jp, DeserializationContext dc) throws IOException {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        String parametersModeHash = jp.getText();
//        if (ObjectUtils.isEmpty(parametersModeHash) || parametersModeHash.equals("default"))
//            return "default";
//        else {
//            return GridFilterModel.decryptParametersMode(parametersModeHash, request.getRemoteAddr());
//        }
//    }
//}
