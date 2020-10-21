//package com.motaharinia.msutility.json.deserializer;
//
//import com.fasterxml.jackson.core.JsonParser;
//import com.fasterxml.jackson.databind.DeserializationContext;
//import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.motaharinia.msutility.json.CustomObjectMapper;
//import org.springframework.util.ObjectUtils;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * User: https://github.com/motaharinia<br>
// * Date: 2020-06-12<br>
// * Time: 01:05:58<br>
// * Description:<br>
// *  این کلاس برای تبدیل رشته جیسون کلاینت به مدل سطر گرید میباشد
// */
//public class JsonDeserializerGridDataRows extends JsonDeserializer<List<Object[]>> {
//
//    @Override
//    public List<Object[]> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
//        String gridRowModelArrayJson = jp.getText();
//        List<Object[]> rows = new ArrayList<>();
//        CustomObjectMapper customObjectMapper = new CustomObjectMapper(null);
//        SearchDataRowModel[] searchDataRowModelArray = customObjectMapper.readValue(gridRowModelArrayJson, SearchDataRowModel[].class);
//        if (!ObjectUtils.isEmpty(searchDataRowModelArray)) {
//            for (SearchDataRowModel searchDataRowModel : searchDataRowModelArray) {
//                rows.add(searchDataRowModel.getCell());
//            }
//        }
//        return rows;
//    }
//
//}
