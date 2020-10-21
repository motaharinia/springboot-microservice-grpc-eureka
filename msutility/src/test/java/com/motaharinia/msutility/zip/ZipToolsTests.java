package com.motaharinia.msutility.zip;

import com.motaharinia.msutility.fso.FsoConfigModel;
import com.motaharinia.msutility.image.ImageTools;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 13:41:19<br>
 * Description:<br>
 * کلاس تست ZipTools
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ZipToolsTests {

    String parentDirPath = "/MsUtilityTests";

    String dir1Path = parentDirPath + "/dir1";
    String dir1File1Path = dir1Path + "/dir1file1.txt";
    String dir1File2Path = dir1Path + "/dir1file2.jpg";

    String dir2Path = parentDirPath + "/dir2";

    String zipFileFullPath = parentDirPath + "/dir1.zip";

    String content1 = "this is first test";
    String password = "123456";

    FsoConfigModel fsoConfigModel = new FsoConfigModel(new Integer[]{60, 120}, "thumb", 100);

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws Exception {
        File parentDir = new File(parentDirPath);
        if (parentDir.exists()) {
            FileUtils.deleteDirectory(parentDir);
        }
        FileUtils.forceMkdir(parentDir);

        //ایجاد یک تصویر نمونه
        final BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = image.createGraphics();
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, 200, 200);
        graphics2D.setPaint(Color.BLACK);
        graphics2D.drawOval(0, 0, 200, 200);
        graphics2D.dispose();

        //ایجاد پوشه اول با یک فایل متنی و یک فایل تصویری
        FileUtils.forceMkdir(new File(dir1Path));
        FileUtils.writeStringToFile(new File(dir1File1Path), content1, StandardCharsets.UTF_8);
        ImageIO.write(image, "JPG", new File(dir1File2Path));
        for (Integer size : fsoConfigModel.getThumbSizeArray()) {
            ImageTools.createThumb(dir1Path, "dir1file2.jpg", size, size);
        }

        //ایجاد پوشه دوم بدون فایل
        FileUtils.forceMkdir(new File(dir2Path));

        Locale.setDefault(new Locale("fa", "IR"));
    }

    /**
     * این متد بعد از هر تست این کلاس اجرا میشود
     */
    @AfterEach
    void finalizeEach() throws IOException {
        Locale.setDefault(Locale.US);
        File parentDir = new File(parentDirPath);
        if (parentDir.exists()) {
            FileUtils.deleteDirectory(parentDir);
        }
    }

    @Order(1)
    @Test
    void zipTest() {
        try {
            List<String> pathToAdd = new ArrayList<>();
            pathToAdd.add(dir1Path);

            //تست ایجاد فایل زیپ از دایرکتوری اول
            ZipTools.zip(pathToAdd, zipFileFullPath, CompressionMethod.DEFLATE, CompressionLevel.MAXIMUM, password, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);
            File file = new File(zipFileFullPath);
            assertThat(file.exists()).isTrue();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Order(2)
    @Test
    void unzipTest() {
        try {
            File file;
            List<String> pathToAdd = new ArrayList<>();
            pathToAdd.add(dir1Path);
            //تست ایجاد فایل زیپ از دایرکتوری اول
            ZipTools.zip(pathToAdd, zipFileFullPath, CompressionMethod.DEFLATE, CompressionLevel.MAXIMUM, password, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);
            file = new File(zipFileFullPath);
            assertThat(file.exists()).isTrue();

            //تست ایجاد استخراج فایل زیپ به دایرکتوری دوم
            ZipTools.unzip(zipFileFullPath, dir2Path, password);
            file = new File(dir2Path + "/dir1");
            assertThat(file.exists()).isTrue();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(3)
    @Test
    void unzipFromByteArrayTest() {
        try {
            File file;
            List<String> pathToAdd = new ArrayList<>();
            pathToAdd.add(dir1Path);
            //تست ایجاد فایل زیپ از دایرکتوری اول
            ZipTools.zip(pathToAdd, zipFileFullPath, CompressionMethod.DEFLATE, CompressionLevel.MAXIMUM, password, EncryptionMethod.AES, AesKeyStrength.KEY_STRENGTH_256);
            file = new File(zipFileFullPath);
            assertThat(file.exists()).isTrue();

            //تست ایجاد استخراج فایل زیپ به دایرکتوری دوم
            ZipTools.unzipFromByteArray(FileUtils.readFileToByteArray(file), dir2Path, password);
            file = new File(dir2Path + "/dir1");
            assertThat(file.exists()).isTrue();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
