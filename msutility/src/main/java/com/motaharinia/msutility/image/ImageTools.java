package com.motaharinia.msutility.image;


import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.fso.FsoTools;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس متدهای ابزاری کار با تصاویر
 */
public interface ImageTools {


    /**
     * این متد یک مسیر دایرکتوری و نام کامل فایل تصویر و ارتفاع و عرض تصویر را از ورودی دریافت میکند و تصویر بندانگشتی فایل تصویر را با طول و عرض داده شده ثبت مینماید
     *
     * @param directoryPath مسیر دایرکتوری
     * @param fileFullName  نام کامل فایل
     * @param height        ارتفاع تصویر
     * @param width         عرض تصویر
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    static void createThumb(@NotNull String directoryPath, @NotNull String fileFullName, @NotNull Integer height, @NotNull Integer width) throws Exception {
        if (ObjectUtils.isEmpty(directoryPath)) {
            throw new UtilityException(ImageTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "directoryPath");
        }
        if (ObjectUtils.isEmpty(fileFullName)) {
            throw new UtilityException(ImageTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fileFullName");
        }
        if (ObjectUtils.isEmpty(height)) {
            throw new UtilityException(ImageTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "height");
        }
        if (ObjectUtils.isEmpty(width)) {
            throw new UtilityException(ImageTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "width");
        }
        if (height < 1) {
            throw new UtilityException(ImageTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE, "height:" + height);
        }
        if (width < 1) {
            throw new UtilityException(ImageTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_ZERO_OR_NEGATIVE, "width:" + width);
        }
        Float z = 0f;
        Integer newWidth = 0;
        Integer newHeight = 0;
        BufferedImage originalImage = ImageIO.read(new File(directoryPath + "/" + fileFullName));
        if (ObjectUtils.isEmpty(originalImage)) {
            throw new UtilityException(ImageTools.class, UtilityExceptionKeyEnum.IMAGE_ORGINAL_READ_PROBLEM, "originalImage is null");
        }
        Integer originalWidth = originalImage.getWidth();
        Integer originalHeight = originalImage.getHeight();
        if ((originalWidth <= width) && (originalHeight <= height)) {
            newHeight = originalHeight;
            newWidth = originalWidth;
        } else {
            if ((originalWidth > width) && (originalHeight > height)) {
                if (originalWidth >= originalHeight) {
                    z = (float) width / originalWidth;
                } else {
                    z = (float) height / originalHeight;
                }
            } else if ((originalWidth > width) && (originalHeight <= height)) {
                z = (float) width / originalWidth;
            } else if ((originalWidth <= width) && (originalHeight > height)) {
                z = (float) height / originalHeight;
            }
            newHeight = (int) (originalHeight * z);
            newWidth = (int) (originalWidth * z);
        }
        int imageType = originalImage.getType();
        if (imageType == 0) {
            imageType = BufferedImage.TYPE_INT_RGB;
        }
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, imageType);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, width, height, null);
        g.dispose();

        ImageIO.write(resizedImage, FsoTools.getFileExtension(fileFullName), new File(directoryPath + "/" + fileFullName + "-" + width + ".thumb"));
    }


    /**
     * این متد داده آرایه بایت تصویر مبدا و پسوند فایل تصویر مبدا و عرض و طول و تناسب را از ورودی دریافت میکند و داده آرایه بایت تصویر تغییر اندازه شده را خروجی میدهد
     *
     * @param originalImageBytes داده آرایه بایت تصویر مبدا
     * @param originalExt        پسوند فایل تصویر مبدا
     * @param destWidth          عرض تصویر مورد نظر
     * @param destHeight         طول تصویر مورد نظر
     * @param withScale          تناسب داشته باشد؟
     * @return خروجی:  داده آرایه بایت تصویر تغییر اندازه شده
     * @throws IOException خطا
     */
    static byte[] imageResize(@NotNull byte[] originalImageBytes, @NotNull String originalExt, @NotNull Integer destWidth, @NotNull Integer destHeight, @NotNull Boolean withScale) throws IOException {
        InputStream inputStream = new ByteArrayInputStream(originalImageBytes);
        BufferedImage originalImage = ImageIO.read(inputStream);
        if (withScale) {
            Double scaleValue = 1d;
            Double tempDouble = 1d;
            if (originalImage.getHeight() > originalImage.getWidth()) {
                scaleValue = (Double.valueOf(destHeight) / Double.valueOf(originalImage.getHeight()));
            } else {
                scaleValue = (Double.valueOf(destWidth) / Double.valueOf(originalImage.getWidth()));
            }
            tempDouble = originalImage.getHeight() * scaleValue;
            destHeight = tempDouble.intValue();
            tempDouble = originalImage.getWidth() * scaleValue;
            destWidth = tempDouble.intValue();
        }
        BufferedImage resizedImage = new BufferedImage(destWidth, destHeight, originalImage.getType());
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, destWidth, destHeight, null);
        g.dispose();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(resizedImage, originalExt, baos);
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();
        return imageInByte;
    }

}
