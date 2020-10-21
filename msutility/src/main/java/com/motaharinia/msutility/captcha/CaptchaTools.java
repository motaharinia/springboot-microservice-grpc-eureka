package com.motaharinia.msutility.captcha;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.Random;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 02:31:29<br>
 * Description:<br>
 * کلاس کپچا ابزارهای لازم برای ساخت تصویر کپچای تصادفی را ارائه مینماید
 */
public interface CaptchaTools {


    /**
     * ایت متد کد اتفاقی و مسیر پیش فرض و نوع تصویر مورد نظر را از ورودی دریافت میکند و یک تصویر کپچا مطابق با آنها خروجی میدهد
     *
     * @param captchaConfigModel مدل تنظیمات تولید کپچا
     * @param code               کد اتفاقی
     * @param imageType          BufferedImage.TYPE_INT_** (Default:BufferedImage.TYPE_INT_RGB)
     * @return خروجی: تصویر کپچا
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static BufferedImage generateCaptcha(@NotNull CaptchaConfigModel captchaConfigModel, @NotNull String code, @NotNull Integer imageType) throws Exception {
        if (ObjectUtils.isEmpty(code)) {
            throw new UtilityException(CaptchaTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "code");
        }
        if (ObjectUtils.isEmpty(imageType)) {
            throw new UtilityException(CaptchaTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "imageType");
        }
        InputStream inputStream;
        Random random = new Random();
        int fontIndex;
        if (captchaConfigModel.getFontVarious()) {
            fontIndex = random.nextInt(captchaConfigModel.getFontCount()) + 1;
        } else {
            fontIndex = captchaConfigModel.getFontFileDefaultIndex();
        }
//        Font font = new Font("Monospaced", Font.ITALIC | Font.BOLD, fontSize);

        inputStream = CaptchaTools.class.getClassLoader().getResourceAsStream("static/captcha/font/" + fontIndex + ".ttf");
        Font customFont = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(getFontSize(fontIndex));

//        URL fontUrl = new URL(contextPath + "/vutility/resources/fonts/captcha/" + fontIndex + ".ttf");
//        Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontUrl.openStream()).deriveFont(getFontSize(fontIndex));

//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        ge.registerFont(customFont);
        BufferedImage img = new BufferedImage(captchaConfigModel.getWidth(), captchaConfigModel.getHeight(), imageType);
        Graphics2D g = img.createGraphics();

        g.setColor(captchaConfigModel.getBackgroundColor());
        g.fillRect(0, 0, captchaConfigModel.getWidth(), captchaConfigModel.getHeight());

        //انتخاب تصویر اتفاقی پس زمینه
        int backgroundIndex;
        if (captchaConfigModel.getBackgroundImageVarious()) {
            backgroundIndex = random.nextInt(captchaConfigModel.getBackgroundImageCount()) + 1;
        } else {
            backgroundIndex = captchaConfigModel.getBackgroundImageFileDefaultIndex();
        }
        inputStream = CaptchaTools.class.getClassLoader().getResourceAsStream("static/captcha/background/" + backgroundIndex + ".png");
        BufferedImage back = ImageIO.read(inputStream);
//        BufferedImage back = ImageIO.read(new URL(contextPath + "/vutility/resources/img/captcha/background/" + backgroundIndex + ".png"));
        g.drawImage(back, 0, 0, captchaConfigModel.getWidth(), captchaConfigModel.getHeight(), null);

        //تولید متن کپچا
        g.setColor(captchaConfigModel.getFontDefaultColor());
        g.setFont(customFont);
        int angle = 0;
        int textPositionX = 30;
        int textPositionY = (int) (captchaConfigModel.getHeight() / 1.5);
        for (int i = 0; i < code.length(); i++) {
            angle = random.nextInt(30 + 30) - 30;
            AffineTransform original = g.getTransform();
            Font theFont = g.getFont();
            original.rotate(angle * Math.PI / 180);
            Font theDerivedFont = theFont.deriveFont(original);
            g.setFont(theDerivedFont);
            if (captchaConfigModel.getFontColorful()) {
                g.setColor(captchaConfigModel.getFontColorfulArray()[random.nextInt(8)]);
            }
            g.drawString(code.charAt(i) + "", textPositionX, textPositionY);
            g.setFont(theFont);
            textPositionX += captchaConfigModel.getFontSize() + 5;
        }

        //تولید خطهای تصادفی
        if (captchaConfigModel.getLinesIncluded()) {
            int rand, dest;
            int[] sourceCordinates;
            int[] destCordinates;
            g.setColor(captchaConfigModel.getLinesColor());
            for (int i = 0; i < captchaConfigModel.getLinesNumber(); i++) {
                rand = random.nextInt(4) + 1;
                sourceCordinates = getCoordinates(rand, captchaConfigModel.getWidth(), captchaConfigModel.getHeight());
                dest = rand + random.nextInt(3) + 1;
                destCordinates = getCoordinates(dest, captchaConfigModel.getWidth(), captchaConfigModel.getHeight());
                g.drawLine(sourceCordinates[0], sourceCordinates[1], destCordinates[0], destCordinates[1]);
            }
        }

        //تولید نقطه های تصادفی
        if (captchaConfigModel.getDotsIncluded()) {
            int randomX = 0, randomY = 0;
            g.setColor(captchaConfigModel.getDotsColor());
            for (int i = 0; i < captchaConfigModel.getDotsNumber(); i++) {
                randomX = random.nextInt(captchaConfigModel.getWidth());
                randomY = random.nextInt(captchaConfigModel.getHeight());
                g.drawLine(randomX, randomY, randomX + 1, randomY + 1);
            }
        }

//        System.out.println("testttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt");
        return img;
    }


    /**
     * متدی که مقدار عددی لبه بالا(1) یا راست(2) یا پایین(3) یا چپ(4) را دریافت میکند و بر اساس طول و عرض تصویر کپچا مختصات نقطه ای اتفاقی را در لبه مورد نظر خروجی میدهد
     *
     * @param direction مقدار عددی لبه بالا(1) یا راست(2) یا پایین(3) یا چپ(4)
     * @param width     عرض تصویر مورد نظر جهت تولید
     * @param height    طول تصویر مورد نظر جهت تولید
     * @return خروجی: مختصات نقطه ای اتفاقی در لبه مورد نظر
     */
    @NotNull
    static int[] getCoordinates(@NotNull Integer direction, @NotNull Integer width, @NotNull Integer height) {
        if (ObjectUtils.isEmpty(direction)) {
            throw new UtilityException(CaptchaTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "direction");
        }
        int[] coordinates = new int[2];
        Random rand = new Random();
        switch (direction) {
            case 1: //لبه بالای مستطیل
                coordinates[0] = rand.nextInt(width);
                coordinates[1] = 0;
                break;

            case 2: //لبه راست مستطیل
                coordinates[0] = width;
                coordinates[1] = rand.nextInt(height);
                break;

            case 3: //لبه پایین مستطیل
                coordinates[0] = rand.nextInt(width);
                coordinates[1] = height;
                break;

            case 4: //لبه چپ مستطیل
                coordinates[0] = 0;
                coordinates[1] = rand.nextInt(height);
                break;
        }
        return coordinates;
    }


    /**
     * متدی که طبق اندیس فایل فونت موجود در ریسورس اندازه قلم متناسب با آن فابل فونت را خروجی میدهد
     *
     * @param fontIndex اندیس فایل فونت موجود در ریسورس
     * @return خروجی: اندازه قلم متناسب با فابل فونت
     */
    @NotNull
    static Float getFontSize(@NotNull Integer fontIndex) {
        Float result = 0f;
        switch (fontIndex) {
            case 1:
                result = 50f;
                break;
            case 2:
                result = 60f;
                break;
            case 3:
                result = 100f;
                break;
            case 4:
                result = 60f;
                break;
            case 5:
                result = 60f;
                break;
            case 6:
                result = 50f;
                break;
            case 7:
                result = 60f;
                break;
            case 8:
                result = 50f;
                break;
            case 9:
                result = 50f;
                break;
            case 10:
                result = 60f;
                break;
            case 11:
                result = 50f;
                break;
            case 12:
                result = 70f;
                break;
            case 13:
                result = 50f;
                break;
        }
        return result;
    }


}
