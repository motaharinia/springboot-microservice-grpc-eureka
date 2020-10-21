package com.motaharinia.msutility.entity;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customfield.CustomDateTime;
import com.motaharinia.msutility.customfield.CustomHtmlString;
import com.motaharinia.msutility.reflection.ReflectionTools;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس متدهای ابزاری انتیتی
 */
public interface EntityTools {


    /**
     * این متد انتیتی یا لیستی از انتیتی های دریافتی را چک میکند که null نباشد <br>
     * و طبق شرایط ورودی فعال یا غیرفعال بودن انتیتی را نیز بررسی میکند. <br>
     * در صورت وجود مشکل اکسپشن پرتاب میکند
     *
     * @param entity       آبجکت انتیتی مورد نظر جهت بررسی است
     * @param entityClass  کلاس انتیتی مورد نظر جهت بررسی است
     * @param checkInvalid آیا غیرفعال بودن آبجکت انتیتی را هم بررسی کند یا خیر
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
*/
    static void checkEntity(Object entity, Class entityClass, Boolean checkInvalid) throws Exception{
        Integer id = 0;
        Boolean isInvalid = true;
        if (ObjectUtils.isEmpty(entity)) {
            //اگر انتیتی یا لیست انتیتی ورودی خالی باشد اکسپشن میدهیم
            throw new UtilityException(entityClass, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_NULL, "entity");
        }
        if (entity instanceof GenericEntity) {
            //اگر انتیتی ورودی از نوع جنریک است (برای اطمینان این موضوع را بررسی میکنیم که حتما انتیتی ورودی آی دی داشته باشد)
            if (checkInvalid) {
                //اگر نیاز به بررسی غیرفعال بودن انتیتی باشد
                isInvalid = (Boolean) ReflectionTools.getMethod(entity.getClass(), "getInvalid").invoke(entity);
                if (isInvalid) {
                    //اگر انتیتی غیرفعال بود آی دی آن را به دست می آوریم تا در توضیحات اکسپشن بیاوریم
                    try {
                        id = (Integer) ReflectionTools.getMethod(entity.getClass(), "getId").invoke(entity);
                        throw new UtilityException(entityClass, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_INVALID, "entity.id:" + id.toString());
                    } catch (Exception e) {
                        throw new UtilityException(entityClass, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_INVALID, "entity");
                    }

                }
            }
        } else if (entity instanceof List<?>) {
            Collection<Object> objectCollection = (Collection<Object>) entity;
            for (Object entityOfCollection : objectCollection) {
                if (ObjectUtils.isEmpty(entityOfCollection)) {
                    //اگر یکی از انتیتی های داخل کالکشن ورودی خالی باشد اکسپشن میدهیم
                    throw new UtilityException(entityClass, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_NULL, "entityOfCollection");
                } else {
                    if (checkInvalid) {
                        //اگر نیاز به بررسی غیرفعال بودن یکی از انتیتی های داخل کالکشن باشد
                        isInvalid = (Boolean) ReflectionTools.getMethod(entityOfCollection.getClass(), "getInvalid").invoke(entityOfCollection);
                        if (isInvalid) {
                            //اگر یکی از انتیتی های داخل کالکشن غیرفعال بود آی دی آن را به دست می آوریم تا در توضیحات اکسپشن بیاوریم
                            try {
                                id = (Integer) ReflectionTools.getMethod(entityOfCollection.getClass(), "getId").invoke(entityOfCollection);
                                throw new UtilityException(entityClass, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_INVALID, "entityOfCollection.id:" + id.toString());
                            } catch (Exception e) {
                                throw new UtilityException(entityClass, UtilityExceptionKeyEnum.CHECK_ENTITY_IS_INVALID, "entityOfCollection");
                            }
                        }
                    }
                }
            }
        }
    }


    /**
     * این متد یک شیی مدل خالی و یک شیی انتیتی را از ورودی دریافت میکند و اطلاعات داخل شیی انتیتی را از طریق قانون نامگذاری مدلها که در دستخط باید توسط توسعه دهندگان رعایت شود داخل شیی مدل کپی میکند و مدل پر شده را خروجی می دهد <br>
     * این متد کمک میکند توسعه دهندگان برای پر کزدن مدل از انتیتی نیاز به کدنویسی تعداد زیادی getter و setter نداشته باشند و حجم کدنویسی را در پروژه کم میکند
     *
     * @param objectModel  شیی مدل خالی
     * @param objectEntity شیی انتیتی
     * @return خروجی: شیی مدل پر شده از اطلاعات انتیتی
     */
    @NotNull
    static Object convertToModel(@NotNull Object objectModel,@NotNull  Object objectEntity) {
        String modelFieldName = "";
        String modelFieldGetterName = "", modelFieldClassName = "";
        Method modelFieldGetterMethod;
        Object modelFieldValue = null;

        Field entityField = null;
        String entityFieldName = "";
        String entityFieldGetterName = "", entityFieldClassName = "";
        Method entityFieldGetterMethod;
        Object entityFieldValue = null;

        Class modelClass = objectModel.getClass();
        Class entityClass = objectEntity.getClass();

        List<Field> fields = ReflectionTools.getAllFields(modelClass, new ArrayList<Field>());
        for (Field modelField : fields) {
            try {
                modelFieldName = modelField.getName();
                modelFieldClassName = modelField.getType().getName();
                modelFieldGetterName = ReflectionTools.getFieldGetterMethodName(modelField, modelFieldName);
                modelFieldGetterMethod = ReflectionTools.getMethod(modelClass, modelFieldGetterName);
                modelFieldGetterMethod.setAccessible(true);
                modelFieldValue = modelFieldGetterMethod.invoke(objectModel);

                EntityModelerInnerEntityFieldModel entityModelerInnerEntityFieldModel = getInnerEntityFieldModel(modelField, objectEntity);
                Object innerEntityObject = entityModelerInnerEntityFieldModel.getInnerEntity();
                entityField = entityModelerInnerEntityFieldModel.getInnerEntityField();
                entityFieldName = entityField.getName();
                entityFieldClassName = entityField.getType().getName();
                entityFieldGetterName = ReflectionTools.getFieldGetterMethodName(entityField, entityFieldName);
                entityFieldGetterMethod = ReflectionTools.getMethod(innerEntityObject.getClass(), entityFieldGetterName);
                entityFieldGetterMethod.setAccessible(true);
                entityFieldValue = entityFieldGetterMethod.invoke(innerEntityObject);

                if ((modelFieldClassName.startsWith("java.") || BeanUtils.isSimpleValueType(modelField.getType()) || (isCustomField(modelField))) && (!Collection.class.isAssignableFrom(modelField.getType()))) {
                    objectModel = convertSetValue(objectModel, modelField, entityField, entityFieldValue);
                } else {

                    if (Collection.class.isAssignableFrom(modelField.getType())) {
                        ParameterizedType modelFieldCollectionType = (ParameterizedType) modelField.getGenericType();
                        Class<?> modelFieldCollectionClass = (Class<?>) modelFieldCollectionType.getActualTypeArguments()[0];
                        Collection<Object> entityFieldCollection = (Collection<Object>) entityFieldValue;
                        List<Object> modelFieldCollection = new ArrayList<>();
                        for (Object entityFieldCollectionObj : entityFieldCollection) {
                            Object modelFieldCollectionObj = modelFieldCollectionClass.getDeclaredConstructor().newInstance();
                            modelFieldCollectionObj = convertToModel(modelFieldCollectionObj, entityFieldCollectionObj);
                            modelFieldCollection.add(modelFieldCollectionObj);
                        }
                        objectModel = convertSetValue(objectModel, modelField, entityField, modelFieldCollection);
                    } else {
                        if (modelFieldValue == null) {
                            modelFieldValue = modelField.getType().getDeclaredConstructor().newInstance();
                        }
                        modelFieldValue = convertToModel(modelFieldValue, entityFieldValue);
                        objectModel = convertSetValue(objectModel, modelField, entityField, modelFieldValue);
                    }
                }

            } catch (Exception ex) {
                System.out.println("Exception of fieldName:" + entityFieldName + " ex.getMessage():" + ex.getMessage() + "\n\n");
            }
        }
        return objectModel;
    }


    /**
     * این متد یک شیی مدل ، یک شیی فیلدی از مدل ، یک شیی فیلدی از انتیتی و یک شیی مقدار داده فیلد را از ورودی دریافت میکند و داده مورد نظر را در فیلد شیی مدل با توجه به نوع فیلد شیی انتیتی که باید پرمیتیو باشد پر میکند و شیی مدل را خروجی میدهد
     *
     * @param objectModel         شیی مدل
     * @param modelField       شیی فیلد مدل
     * @param entityField      شیی فیلد انتیتی
     * @param entityFieldValue مقدار داده
     * @return خروجی: شیی مدل که یکی از فیلدهای آن پر شده است
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    private static Object convertSetValue(@NotNull Object objectModel,@NotNull  Field modelField,@NotNull  Field entityField, Object entityFieldValue) throws Exception {
        if (Date.class.isAssignableFrom(entityField.getType())) {
            Date entityDate = (Date) entityFieldValue;
            if (CustomDate.class.isAssignableFrom(modelField.getType())) {
                CustomDate modelDate = new CustomDate(entityDate);
                modelField.setAccessible(true);
                modelField.set(objectModel, modelDate);
            } else if (CustomDateTime.class.isAssignableFrom(modelField.getType())) {
                CustomDateTime modelDateTime = new CustomDateTime(entityDate);
                modelField.setAccessible(true);
                modelField.set(objectModel, modelDateTime);
            }
        } else if (CustomHtmlString.class.isAssignableFrom(modelField.getType())) {
            CustomHtmlString customString = new CustomHtmlString();
            customString.setCustomHtmlString((String) entityFieldValue);
            modelField.setAccessible(true);
            modelField.set(objectModel, customString);
        } else {
            modelField.setAccessible(true);
            modelField.set(objectModel, entityFieldValue);
        }
        return objectModel;
    }

    /**
     * این متد شیی فیلد مدل و شیی انتیتی را از ورودی دریافت میکند و بر اساس نام شیی فیلد مدل که حاوی آندرلاین ممکن است یاشد ، انتیتی داخلی و فیلد متناظر را در قالب یک مدل خروجی میدهد <br>
     * وقتی در نام فیلدهای مدل از آندرلاین استفاده میکنیم به کلاس انتیتی مدلر نشان میدهیم که باید در کلاس انتیتی به سراغ یک فیدی از انتیتی برود که خودش یک کلاس انتیتی دیگر است
     *
     * @param modelField شیی فیلد مدل
     * @param objectEntity  شیی انتیتی
     * @return خروجی: مدلی حاوی انتیتی داخلی و فیلد متناظر ان
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
*/
    @NotNull
    private static EntityModelerInnerEntityFieldModel getInnerEntityFieldModel(@NotNull Field modelField, @NotNull Object objectEntity) throws Exception {
        Field entityChildrenField = null;
        String entityChildrenFieldName = "";
        Object entityChildrenObject = null;
        Class entityChildrenClass = null;
        Method entityChildrenGetterMethod;
        String entityChildrenFieldGetterName = "";

        List<String> artmp = new ArrayList<String>();
        artmp.addAll(Arrays.asList(modelField.getName().split("_", -1)));
        entityChildrenObject = objectEntity;
        entityChildrenFieldName = modelField.getName();

        if (artmp.size() > 1) {
            for (int j = 0; j < artmp.size() - 1; j++) {
                entityChildrenFieldName = artmp.get(j);
                entityChildrenClass = entityChildrenObject.getClass();
                entityChildrenField = ReflectionTools.getField(entityChildrenClass, entityChildrenFieldName);
                entityChildrenFieldGetterName = ReflectionTools.getFieldGetterMethodName(entityChildrenField, entityChildrenFieldName);
                entityChildrenGetterMethod = ReflectionTools.getMethod(entityChildrenClass, entityChildrenFieldGetterName);
                entityChildrenGetterMethod.setAccessible(true);
                entityChildrenObject = entityChildrenGetterMethod.invoke(entityChildrenObject);
            }
            entityChildrenFieldName = artmp.get(artmp.size() - 1);
        }

        entityChildrenClass = entityChildrenObject.getClass();
        entityChildrenField = ReflectionTools.getField(entityChildrenClass, entityChildrenFieldName);
        return new EntityModelerInnerEntityFieldModel(entityChildrenObject, entityChildrenField);
    }


    /**
     * این متد یک شیی فیلد را از ورودی دریافت میکند و چک میکند که فیلد درخواستی ورودی آیا از نوع فیلدهای کاستوم کلاسهای مدل است یا خیر <br>
     * چند نوع فیلد کاستوم در کلاسهای مدل میتوانیم استفاده کنیم: <br>
     * CustomDate: این نوع فیلدها در کلاسهای مدل برای تبدیل تاریخ دیت جاوا به تاریخ سه گانه شمسی سال و ماه و روز و برعکس از کلاینت به سرور میشود <br>
     * CustomDateTime: این نوع فیلدها در کلاسهای مدل برای تبدیل تاریخ دیت جاوا به تاریخ سه گانه شمسی  سال و ماه و روز و زمان سه گانه ساعت و دقیقه وثانیه و برعکس از کلاینت به سرور استفاده میشود <br>
     * CustomHtmlString: برای اینکه در ارتباط بین سرور و کلاینت بتوانیم فیلدهای دارای تگهای اچ تی ام ال را از فیلدهای رشته ای معمولی متمایز کنیم از این کلاس استفاده میکنیم
     *
     * @param field شیی فیلد
     * @return خروجی: تایید اینکه این فیلد کاستوم است یا خیر
     */
    @NotNull
    private static Boolean isCustomField(@NotNull  Field field) {
        List<String> customFieldClassNameList = new ArrayList<>();
        customFieldClassNameList.add(CustomDate.class.getName());
        customFieldClassNameList.add(CustomDateTime.class.getName());
        customFieldClassNameList.add(CustomHtmlString.class.getName());
        if (customFieldClassNameList.contains(field.getType().getName())) {
            return true;
        } else {
            return false;
        }
    }
}
