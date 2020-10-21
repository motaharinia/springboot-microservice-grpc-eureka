//package com.motaharinia.msutility.json.serializer;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.MessageSource;
//import org.springframework.util.ObjectUtils;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * User: https://github.com/motaharinia<br>
// * Date: 2020-06-12<br>
// * Time: 01:05:58<br>
// * Description:<br>
// *این کلاس برای تبدیل مدل سطر گرید به رشته جیسون برای ارسال به سمت کلاینت میباشد
// */
//public class JsonSerializerGridDataRows extends JsonSerializer<List<Object[]>> {
//
//    @Autowired
//    public MessageSource messageSource;
//
//    @Override
//    public void serialize(List<Object[]> rows, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException {
//        if (!ObjectUtils.isEmpty(rows)) {
//            Integer id = 0;
//            Object[] row = null;
//            SearchDataRowModel searchDataRowModel = null;
//            SearchDataRowModel[] searchDataRowModelArray = new SearchDataRowModel[rows.size()];
//            for (int i = 0; i < rows.size(); i++) {
//                row = rows.get(i);
//                if (!ObjectUtils.isEmpty(row)) {
//                    if (!ObjectUtils.isEmpty(row[0])) {
//                        id = (Integer) row[0];
//                    } else {
//                        id = 0;
//                    }
//                    searchDataRowModelArray[i] = new SearchDataRowModel(id, row);
//                } else {
//                    searchDataRowModelArray[i] = new SearchDataRowModel(0, row);
//                }
//            }
//            gen.writeObject(searchDataRowModelArray);
//        } else {
//            SearchDataRowModel[] searchDataRowModelArray = new SearchDataRowModel[0];
//            gen.writeObject(searchDataRowModelArray);
//        }
//    }
//
//}
