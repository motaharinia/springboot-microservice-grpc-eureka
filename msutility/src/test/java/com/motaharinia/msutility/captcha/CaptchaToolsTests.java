package com.motaharinia.msutility.captcha;

import org.junit.jupiter.api.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 13:41:19<br>
 * Description:<br>
 * کلاس تست CaptchaTools
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CaptchaToolsTests {


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
    void createThumbTest() {
        try {
            //تست ایجاد تصویر کپچا از روی تنظیمات پیش فرض
            CaptchaConfigModel captchaConfigModel = new CaptchaConfigModel();
            BufferedImage bufferedImage = CaptchaTools.generateCaptcha(captchaConfigModel, "123456", BufferedImage.TYPE_INT_RGB);
            assertThat(bufferedImage).isNotNull();
            assertThat(bufferedImage.getWidth()).isEqualTo(captchaConfigModel.getWidth());
            assertThat(bufferedImage.getHeight()).isEqualTo(captchaConfigModel.getHeight());
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


}
