package com.motaharinia.msutility.fso;

import com.motaharinia.msutility.fso.check.FsoPathCheckTypeEnum;
import com.motaharinia.msutility.fso.content.FsoPathContentModel;
import com.motaharinia.msutility.image.ImageTools;
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
 * کلاس تست FsoTools
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FsoToolsTests {

    String parentDirPath = "/MsUtilityTests";
    String parentDirFile1Path = parentDirPath + "/parentfile1.txt";
    String parentDirFile2Path = parentDirPath + "/parentfile2.txt";

    String dir1Path = parentDirPath + "/dir1";
    String dir1File1Path = dir1Path + "/dir1file1.txt";
    String dir1File2Path = dir1Path + "/dir1file2.jpg";

    String dir2Path = parentDirPath + "/dir2";
    String dir2File1Path = dir2Path + "/dir2file1.txt";
    String dir2File2Path = dir2Path + "/dir2file2.jpg";

    String content1 = "this is first test";
    String content2 = "this is second test";

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
        FileUtils.writeStringToFile(new File(parentDirFile1Path), content1, StandardCharsets.UTF_8);
        FileUtils.writeStringToFile(new File(parentDirFile2Path), content2, StandardCharsets.UTF_8);

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

        //ایجاد پوشه دوم با یک فایل متنی و یک فایل تصویری
        FileUtils.forceMkdir(new File(dir2Path));
        FileUtils.writeStringToFile(new File(dir2File1Path), content1, StandardCharsets.UTF_8);
        ImageIO.write(image, "JPG", new File(dir2File2Path));
        for (Integer size : fsoConfigModel.getThumbSizeArray()) {
            ImageTools.createThumb(dir2Path, "dir2file2.jpg", size, size);
        }

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
    void pathExistCheckTest() {
        try {
            assertThat(FsoTools.pathExistCheck(parentDirPath).getTypeEnum()).isEqualTo(FsoPathCheckTypeEnum.DIRECTORY);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(2)
    @Test
    void pathContentDirectoryTest() {
        try {
            FsoPathContentModel fsoPathContentModel = FsoTools.pathContent(parentDirPath, new String[0], new String[0], new String[0], new String[0], true);
            assertThat(fsoPathContentModel.getDirectoryModelList().size()).isEqualTo(2);
            assertThat(fsoPathContentModel.getFileModelList().get(0).getSize()).isEqualTo(content1.length());
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(3)
    @Test
    void pathContentFileTest() {
        try {
            FsoPathContentModel fsoPathContentModel = FsoTools.pathContent(parentDirPath, new String[0], new String[0], new String[0], new String[0], true);
            assertThat(fsoPathContentModel.getFileModelList().size()).isEqualTo(2);
            assertThat(fsoPathContentModel.getFileModelList().get(0).getFullPath()).isEqualTo(parentDirFile1Path);
            assertThat(fsoPathContentModel.getFileModelList().get(0).getExtension()).isEqualTo("txt");
            assertThat(fsoPathContentModel.getFileModelList().get(0).getSize()).isEqualTo(content1.length());
            assertThat(fsoPathContentModel.getFileModelList().get(1).getFullPath()).isEqualTo(parentDirFile2Path);
            assertThat(fsoPathContentModel.getFileModelList().get(1).getExtension()).isEqualTo("txt");
            assertThat(fsoPathContentModel.getFileModelList().get(1).getSize()).isEqualTo(content2.length());
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(4)
    @Test
    void pathGetFileListRecursiveTest() {
        try {
            List<File> fileList = FsoTools.pathGetFileListRecursive(parentDirPath, new FsoFileListFilter(), new ArrayList<>());
            assertThat(fileList).isNotNull();
            assertThat(fileList.size()).isEqualTo(10);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(5)
    @Test
    void createDirectoryIfNotExistTest() {
        try {
            //تست عدم ایجاد دایرکتوری در صورت وجود داشتن از قبل
            FsoTools.createDirectoryIfNotExist(parentDirPath);
            List<File> fileList = FsoTools.pathGetFileListRecursive(parentDirPath, new FsoFileListFilter(), new ArrayList<>());
            assertThat(fileList).isNotNull();
            assertThat(fileList.size()).isEqualTo(10);

            //تست ایجاد دایرکتوری در صورت عدم وجود
            FsoTools.createDirectoryIfNotExist(parentDirPath + "/createDirectoryIfNotExist");
            File file = new File(parentDirPath + "/createDirectoryIfNotExist");
            assertThat(file.exists()).isTrue();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(6)
    @Test
    void pathDirectoryPrepareTest() {
        try {
            FsoTools.pathDirectoryPrepare(parentDirPath + "/sub1/sub2/sub3");
            File file = new File(parentDirPath + "/sub1/sub2/sub3");
            assertThat(file.exists()).isTrue();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(7)
    @Test
    void deleteTest() {
        try {
            File file;

            //تست حذف فایل معمولی بدون تصویر بندانگشتی
            FsoTools.delete(dir1File1Path, false, fsoConfigModel);
            file = new File(dir1File1Path);
            assertThat(file.exists()).isFalse();

            //تست حذف فایل معمولی با تصویر بندانگشتی
            FsoTools.delete(dir1File2Path, true, fsoConfigModel);
            file = new File(dir1File2Path);
            assertThat(file.exists()).isFalse();
            for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                file = new File(dir1File2Path + "-" + size + "." + fsoConfigModel.getThumbExtension());
                assertThat(file.exists()).isFalse();
            }

            //تست حذف دایرکتوری
            FsoTools.delete(dir2Path, false, fsoConfigModel);
            file = new File(dir2Path);
            assertThat(file.exists()).isFalse();

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(8)
    @Test
    void moveTest() {
        try {
            File file;

            //تست انتقال/تغییرنام  فایل معمولی بدون تصویر بندانگشتی و بدون ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.move(dir1File1Path, parentDirPath + "/dir1file1Renamed.txt", false, fsoConfigModel, false);
            file = new File(parentDirPath + "/dir1file1Renamed.txt");
            assertThat(file.exists()).isTrue();

            //تست انتقال/تغییرنام فایل با تصویر بندانگشتی و بدون ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.move(dir1File2Path, parentDirPath + "/dir1file2Renamed.txt", true, fsoConfigModel, false);
            file = new File(parentDirPath + "/dir1file2Renamed.txt");
            assertThat(file.exists()).isTrue();
            for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                file = new File(parentDirPath + "/dir1file2Renamed.txt" + "-" + size + "." + fsoConfigModel.getThumbExtension());
                assertThat(file.exists()).isTrue();
            }

            //تست انتقال/تغییرنام  دایرکتوری و بدون ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.move(dir2Path, dir1Path + "/dir2Renamed", false, fsoConfigModel, false);
            file = new File(dir1Path + "/dir2Renamed");
            assertThat(file.exists()).isTrue();

            //تست انتقال/تغییرنام  فایل معمولی بدون تصویر بندانگشتی و همراه با ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.move(parentDirFile1Path, dir1Path + "/dircreation1/parentFile1Renamed.txt", false, fsoConfigModel, true);
            file = new File(dir1Path + "/dircreation1/parentFile1Renamed.txt");
            assertThat(file.exists()).isTrue();

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(9)
    @Test
    void copyTest() {
        try {
            File file;

            //تست کپی فایل معمولی بدون تصویر بندانگشتی و بدون ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.copy(dir1File1Path, parentDirPath + "/dir1file1Copied.txt", false, fsoConfigModel, false, false);
            file = new File(parentDirPath + "/dir1file1Copied.txt");
            assertThat(file.exists()).isTrue();

            //تست کپی فایل با تصویر بندانگشتی و بدون ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.copy(dir1File2Path, parentDirPath + "/dir1file2Copied.txt", true, fsoConfigModel, false, false);
            file = new File(parentDirPath + "/dir1file2Copied.txt");
            assertThat(file.exists()).isTrue();
            for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                file = new File(parentDirPath + "/dir1file2Copied.txt-" + size + "." + fsoConfigModel.getThumbExtension());
                assertThat(file.exists()).isTrue();
            }

            //تست کپی فایل معمولی بدون تصویر بندانگشتی و همراه با ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.copy(dir1File1Path, parentDirPath + "/dircreation1/dir1file1Copied.txt", false, fsoConfigModel, true, false);
            file = new File(parentDirPath + "/dircreation1/dir1file1Copied.txt");
            assertThat(file.exists()).isTrue();

            //تست کپی فایل با تصویر بندانگشتی و همراه با ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.copy(dir1File2Path, parentDirPath + "/dircreation2/dir1file2Copied.txt", true, fsoConfigModel, true, false);
            file = new File(parentDirPath + "/dircreation2/dir1file2Copied.txt");
            assertThat(file.exists()).isTrue();
            for (Integer size :fsoConfigModel.getThumbSizeArray()) {
                file = new File(parentDirPath + "/dircreation2/dir1file2Copied.txt-" + size + "." + fsoConfigModel.getThumbExtension());
                assertThat(file.exists()).isTrue();
            }

            //تست کپی دایرکتوری بدون ساخت خودکار دایرکتوری های مسیر مقصد
            FsoTools.copy(dir1Path, dir2Path, false, fsoConfigModel, false, false);
            file = new File(dir2Path + "/dir1");
            assertThat(file.exists()).isTrue();

            //تست کپی دایرکتوری با ساخت خودکار دایرکتوری های  مسیر مقصد
            FsoTools.copy(dir1Path, dir2Path + "/dircreation", false, fsoConfigModel, true, false);
            file = new File(dir2Path + "/dircreation/dir1");
            assertThat(file.exists()).isTrue();

            //تست کپی دایرکتوری بدون ساخت خودکار دایرکتوری های مسیر مقصد و تغییرنام در صورت وجود دایرکتوری همنام در مقصد
            FsoTools.copy(dir1Path, dir2Path, false, fsoConfigModel, false, true);
            file = new File(dir2Path + "/dir1 - Copy");
            assertThat(file.exists()).isTrue();

            //تست کپی فایل معمولی بدون تصویر بندانگشتی و بدون ساخت خودکار دایرکتوری های مسیر مقصد و تغییرنام در صورت وجود فایل همنام در مقصد
            FsoTools.copy(dir1File1Path, parentDirPath + "/dir1file1Copied.txt", false, fsoConfigModel, false, true);
            file = new File(parentDirPath + "/dir1file1Copied - Copy.txt");
            assertThat(file.exists()).isTrue();

            //تست کپی فایل با تصویر بندانگشتی و بدون ساخت خودکار دایرکتوری های مسیر مقصد و تغییرنام در صورت وجود فایل همنام در مقصد
            FsoTools.copy(dir2File2Path, parentDirPath + "/dir1file2Copied.txt", true, fsoConfigModel, false, true);
            file = new File(parentDirPath + "/dir1file2Copied - Copy.txt");
            assertThat(file.exists()).isTrue();
            for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                file = new File(parentDirPath + "/dir1file2Copied - Copy.txt-" + size + "." + fsoConfigModel.getThumbExtension());
                assertThat(file.exists()).isTrue();
            }

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(10)
    @Test
    void downloadUrlAndReadTest() {
        try {
            byte[] data = FsoTools.downloadUrlAndRead("http://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js");
            assertThat(data.length).isGreaterThan(0);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(11)
    @Test
    void downloadPathAndReadTest() {
        try {
            byte[] data = FsoTools.downloadPathAndRead(dir1File1Path);
            assertThat(data.length).isGreaterThan(0);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(12)
    @Test
    void uploadWriteToPathTest() {
        try {
            String hashedPath = FsoTools.uploadWriteToPath(dir1Path, "uploadWriteToPath.txt", new String("uploadWriteToPath").getBytes(StandardCharsets.UTF_8), false, fsoConfigModel);
            assertThat(hashedPath).isNotEmpty();
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(13)
    @Test
    void getFileExtensionTest() {
        try {
            assertThat(FsoTools.getFileExtension("test.jpg")).isEqualTo("jpg");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Order(14)
    @Test
    void getFileNameWithoutExtensionTest() {
        try {
            assertThat(FsoTools.getFileNameWithoutExtension("test.jpg")).isEqualTo("test");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Order(15)
    @Test
    void getRightLocationForSaveTest() {
        try {
            //فایل با شناسه بیشتر از محدودیت دایرکتوری در دایرکتوری دوم باید ذخیره شود
            assertThat(FsoTools.getRightLocationForSave(fsoConfigModel.getDirectoryFileLimit() + 1, fsoConfigModel.getDirectoryFileLimit())).isEqualTo(2);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Order(16)
    @Test
    void recursiveCreateNameTest() {
        try {
            //تست نام دایرکتوری تکراری
            assertThat(FsoTools.recursiveCreateName(dir1Path, 0)).isEqualTo(dir1Path + " - Copy");
            //تست نام فایل تکراری
            assertThat(FsoTools.recursiveCreateName(dir1Path, "dir1file1", "txt", 0)).isEqualTo(dir1Path + "/dir1file1 - Copy.txt");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
