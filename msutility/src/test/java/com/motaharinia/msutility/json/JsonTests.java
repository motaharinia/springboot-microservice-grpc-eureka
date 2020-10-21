package com.motaharinia.msutility.json;

import com.motaharinia.msutility.calendar.CalendarTools;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.json.sample.JsonModel;
import com.motaharinia.msutility.json.sample.MembershipRequestFrontModelUpdate;
import org.junit.jupiter.api.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-10-06 <br>
 * Time: 20:31:10 <br>
 * Description: <br>
 * کلاس تست SearchDataModel
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JsonTests {
    private final CustomObjectMapper mapper = new CustomObjectMapper(getMessageSource());


    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() {
        Locale.setDefault(new Locale("fa", "IR"));

    }

    /**
     * این متد بعد از هر تست این کلاس اجرا میشود
     */
    @AfterEach
    void finalizeEach() {
        Locale.setDefault(Locale.US);
    }


    private ReloadableResourceBundleMessageSource getMessageSource(){
        ReloadableResourceBundleMessageSource messageSource= new ReloadableResourceBundleMessageSource();
        messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames(new String[]{"classpath:lang/module/common/etcitem/etcItem",});
        messageSource.setCacheSeconds(5);
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }

    @Order(1)
    @Test
    void serializeDeserializeTest() {
        try {
            //تاریخ میلادی موجود در دیتابیس
            Date dbDate = new Date();
            //مدل جاوا حاوی تاریخ میلادی برای ارسال به کلاینت
            JsonModel jsonModel = new JsonModel(new CustomDate(dbDate), "etcItem.common.user.gender.FEMALE");

            //سریالایز مدل جاوا در کنترلر در زمان ارسال به کلاینت
            String jsonString= this.mapper.writeValueAsString(jsonModel);

            //تستهای سریالایز
            //تست عدم وجود خطا
            assertThat(jsonString).isNotEqualTo(null);
            //تست تبدیل تاریخ میلادی به جلالی جهت ارسال به کلاینت
            //برای اینکه بتوانیم سال شمسی را در رشته جیسون پیدا کنیم این شیی را میسازیم
            CustomDate jalaliCustomDate= CalendarTools.gregorianToJalaliDate(dbDate);
            assertThat(jsonString.contains(jalaliCustomDate.getYear().toString())).isTrue();
            //تست  رشته ترجمه "ای تی سی آیتم" به زبان فارسی
            assertThat(jsonString.contains("زن")).isTrue();

            //دیسریالایز رشته جیسون دریافتی از کلاینت به مدل در کنترلر
            jsonModel = mapper.readValue(jsonString,JsonModel.class);

            //تست های دیسریالایز
            //تست عدم وجود خطا
            assertThat(jsonModel).isNotEqualTo(null);
            //تست تبدیل تاریخ جلالی دریافتی از کلاینت به میلادی
            assertThat(jsonModel.getCustomDate()).isNotEqualTo(null);
            Calendar dbDateCalendar = Calendar.getInstance(TimeZone.getTimeZone("Asia/Tehran"));
            dbDateCalendar.setTime(dbDate);
            assertThat(jsonModel.getCustomDate().getYear()).isEqualTo(dbDateCalendar.get(Calendar.YEAR));
            assertThat(jsonModel.getCustomDate().getMonth()).isEqualTo(dbDateCalendar.get(Calendar.MONTH)+1);
            assertThat(jsonModel.getCustomDate().getDay()).isEqualTo(dbDateCalendar.get(Calendar.DAY_OF_MONTH));
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Order(2)
    @Test
    void serializeEmptyModelTest() {
        try {
            MembershipRequestFrontModelUpdate membershipRequestFrontModelUpdate = new MembershipRequestFrontModelUpdate();
            String jsonString= this.mapper.writeValueAsString(membershipRequestFrontModelUpdate);
            assertThat(jsonString).isNotEqualTo(null);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
