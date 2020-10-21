package com.motaharinia.msutility.customvalidation.listnoduplicatebyfields;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-18<br>
 * Time: 15:02:58<br>
 * Description:<br>
 * انوتیشن اعتبارسنجی عدم تکرار در لیست توسط فیلدهای مورد نظر<br>
 * فقط برای فیلدهای از نوع List میتوان از این اعتبارسنجی استفاده کرد
 */
@Documented
@Constraint(validatedBy = ListNoDuplicateByFieldsValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ListNoDuplicateByFields {
    
    String message() default "customValidation.uniqueArray";
      
    Class<?>[] groups() default {};
      
    Class<? extends Payload>[] payload() default {};
    
    String[] fields() default {};
    
    
    
    

//    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
//        
//        
////        List<Integer> first = new ArrayList<>();
////        
////        first.add(10);
////        first.add(10);
////        first.add(30);
////        first.add(40);
////        
////        boolean repeat = false;
////        for(int i=0; i<first.size(); i++){
////            for(int j=i+1; j<first.size(); j++){
////                if(first.get(i).equals(first.get(j))){
////                    repeat = true;
////                    break;
////                }
////            }
////        }
////        
////        System.out.println("repeat: " + repeat);
//        
//        
//        List<TestObject> list = new ArrayList<>();
//        TestObject obj = new TestObject("xx");
//        list.add(new TestObject(10));
//        list.add(new TestObject(20));
//        
//        
//        System.out.println(getObjectFieldValue(obj, "xxx"));
//        
////        System.out.println( obj.getClass().getField("name").getType().getName());
////        System.out.println(obj.getClass().getMethod("is").getReturnType());
//        
////        boolean repeat = false;
////        String[] compareFields = new String[]{"id","name"};
////        
////        for(int i=0; i<list.size(); i++){
////            for(int j=i+1; j<list.size(); j++){
////                
////            }
////        }
//        
//    }
//    
//    private static Object getObjectFieldValue(Object obj, String fieldName) {
//        try {
//            Field field = obj.getClass().getDeclaredField(fieldName);
//            field.setAccessible(true);
//            return field.get(obj);
//        }
//        catch (Exception e) {
//            return null;
//        }
//    }

}
