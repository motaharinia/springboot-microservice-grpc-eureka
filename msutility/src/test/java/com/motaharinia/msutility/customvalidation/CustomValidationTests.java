package com.motaharinia.msutility.customvalidation;

import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.customvalidation.sample.*;
import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-13<br>
 * Time: 16:40:56<br>
 * Description:<br>
 * کلاس تست CustomValidation
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomValidationTests {

    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private Validator validator = factory.getValidator();

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() {
        Locale.setDefault(Locale.US);
    }

    /**
     * این متد بعد از هر تست این کلاس اجرا میشود
     */
    @AfterEach
    void finalizeEach() {
        Locale.setDefault(Locale.US);
    }


    @Order(1)
    @Test
    void customValidationTest() {
        try {
            Locale.setDefault(new Locale("fa", "IR"));
            CustomValidationTestModel model = new CustomValidationTestModel();
            model.setCompanyPhoneNo("22555");
            model.setDateRange1(new CustomDate(new Date()));
            model.setDateRange2(new CustomDate(new Date()));
            model.setDateRange3(new CustomDate(new Date()));
            model.setDateRange4(new CustomDate(new Date()));
            model.setDecimalCount1(12.582);
            model.setDecimalCount2(12.582);
            model.setDoubleRange(12.582);
            model.setEmail("eng.motahari@gmail.com");
            model.setIntegerRange(12);
            model.setLatinCharacter("this is a latin character string");
            model.setListLength1(Arrays.asList(new String[]{"item1", "item2", "item3"}));
            model.setListLength2(Arrays.asList(new String[]{"item1", "item2", "item3"}));
            model.setListNoDuplicate(Arrays.asList(new String[]{"item1", "item2", "item3"}));
            model.setListNoDuplicateByFields(Arrays.asList(new ListNoDuplicateByFieldsModel[]{new ListNoDuplicateByFieldsModel("f11", "f12"), new ListNoDuplicateByFieldsModel("f21", "f22"), new ListNoDuplicateByFieldsModel("f31", "f32")}));
            model.setMobile("09124376251");
            model.setNationalCode("0557093007");
            model.setOrganizationEconomicCode("12345678901234");
            model.setOrganizationNationalCode("12345678901");
            model.setOrganizationRegistrationNo("2315");
            model.setPassword1("123456");
            model.setPassword2("123$Abc");
            model.setPersianCharacters("مصطفی مطهری نیا");
            model.setPersonPhone("88888888");
            model.setPostalCode("1234567890");
            model.setPrice(new BigDecimal(15000));
            model.setRequired1("Mostafa Motaharinia");
            model.setRequired2(15000);
            model.setRequired3(true);
            model.setRequired4(Arrays.asList(new String[]{"item1", "item2"}));
            model.setRequired5(Map.of(1, "A", 2, "B", 3, "C"));
            model.setStringLength1("abc");
            model.setStringLength2("abc");
            model.setStringPattern("4012888888881881");
            model.setUsername1("09124376251");
            model.setUsername2("eng.motahari@gmail.com");
            Set<ConstraintViolation<CustomValidationTestModel>> violations = validator.validate(model);
            violations.stream().forEach(item -> System.out.println(item.toString()));
            assertThat(violations.isEmpty()).isEqualTo(true);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


}
