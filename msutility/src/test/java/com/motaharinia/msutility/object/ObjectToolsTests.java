package com.motaharinia.msutility.object;

import com.motaharinia.msutility.object.sample.ClassA;
import com.motaharinia.msutility.object.sample.ClassB;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 13:41:19<br>
 * Description:<br>
 * کلاس تست ObjectTools
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ObjectToolsTests {

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws Exception {
        Locale.setDefault(new Locale("fa", "IR"));
    }

    /**
     * این متد بعد از هر تست این کلاس اجرا میشود
     */
    @AfterEach
    void finalizeEach() throws IOException {
        Locale.setDefault(Locale.US);
    }

    @Order(1)
    @Test
    void copyTest() {
        try {
            ClassA classA = new ClassA();
            classA.setId(1);
            classA.setName("ali");
            classA.setFamily("alimi");
            classA.setFatherName("FFFFFF");
            classA.setTest("TTTTTTTTT");
            classA.setMobile("09124376251");

            ClassB classB = new ClassB();
            classB = (ClassB) ObjectTools.copy(classA, classB, new String[]{}, new String[]{});


            assertThat(classB.getId()).isEqualTo(classA.getId());
            assertThat(classB.getName()).isEqualTo(classA.getName());
            assertThat(classB.getUser_fatherName()).isNull();
            assertThat(classB.getUser_test()).isNull();
            assertThat(classB.getDefaultContact_mobile()).isNull();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(2)
    @Test
    void copyWithPrefixAddTest() {
//        try {
            ClassB classB = new ClassB();
            classB.setName("ali");
            classB.setUser_test("TTTTTTTTT");
            classB.setUser_fatherName("FFFFFF");

            ClassA classA = new ClassA();
            classA = (ClassA) ObjectTools.copy(classB, classA, new String[]{},new String[]{"user_","defaultContact_"});
            assertThat(classA.getId()).isEqualTo(classB.getId());
            assertThat(classA.getMobile()).isEqualTo(classB.getDefaultContact_mobile());
            assertThat(classA.getName()).isEqualTo(classB.getName());
            assertThat(classA.getFamily()).isNull();
            assertThat(classA.getFatherName()).isEqualTo(classB.getUser_fatherName());
            assertThat(classA.getTest()).isEqualTo(classB.getUser_test());
//        } catch (Exception ex) {
//            fail(ex.toString());
//        }
    }

}
