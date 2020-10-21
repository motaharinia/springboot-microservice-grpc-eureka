package com.motaharinia.msutility.string;

import org.junit.jupiter.api.*;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-13<br>
 * Time: 16:40:56<br>
 * Description:<br>
 * کلاس تست StringTools
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StringToolsTests {
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
    void generateMD5HashTest() {
        try {
            assertThat(StringTools.generateMD5Hash("Mostafa Motaharinia", 20)).isNotBlank();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(2)
    @Test
    void removeHtmlTest() {
        try {
            assertThat(StringTools.removeHtml("Mostafa<br>Motaharinia").contains("<br>")).isNotEqualTo(true);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(3)
    @Test
    void summarizeStringTest() {
        try {
            assertThat(StringTools.summarizeString("Hello Mostafa Motaharinia", 20)).isEqualTo("Hello Mostafa Mot...");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(4)
    @Test
    void highlightTest() {
        try {
            assertThat(StringTools.highlight("Hello Mostafa Motaharinia", "Hello")).isEqualTo("<span class='highlight'>Hello</span> Mostafa Motaharinia");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(5)
    @Test
    void generateRandomStringCharacterAllTest() {
        try {
            assertThat(StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_ALL, 5, false).matches("^[a-zA-Z]*$")).isEqualTo(true);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(6)
    @Test
    void generateRandomStringNumberTest() {
        try {
            assertThat(StringTools.generateRandomString(RandomGenerationTypeEnum.NUMBER, 5, false).matches("^[0-9]*$")).isEqualTo(true);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(7)
    @Test
    void generateRandomStringCharacterNumberTest() {
        try {
            assertThat(StringTools.generateRandomString(RandomGenerationTypeEnum.CHARACTER_NUMBER, 5, false).matches("^[a-zA-Z0-9]*$")).isEqualTo(true);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }
}
