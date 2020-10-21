package com.motaharinia.msutility.encoding;

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
 * کلاس تست EncodingTools
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EncodingToolsTests {

    String content = "this is first test";

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws IOException {
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
    void base64EncodeDecodeTest() {
        try {
            assertThat(EncodingTools.base64decode(EncodingTools.base64Encode(content))  ).isEqualTo(content);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
