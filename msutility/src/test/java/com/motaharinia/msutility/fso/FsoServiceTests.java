package com.motaharinia.msutility.fso;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.util.Locale;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-19<br>
 * Time: 16:19:59<br>
 * Description:کلاس تست سرویس فایل سیستم
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FsoServiceTests {
    @LocalServerPort
    private int port;

    @Autowired
    private FsoService fsoService;

    String parentDirPath = fsoService.getFSO_CONFIG_MODEL().getThumbExtension() + "/MsUtilityTests";
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
//        File parentDir = new File(parentDirPath);
//        if (parentDir.exists()) {
//            FileUtils.deleteDirectory(parentDir);
//        }
//        FileUtils.forceMkdir(parentDir);
//        FileUtils.writeStringToFile(new File(parentDirFile1Path), content1, StandardCharsets.UTF_8);
//        FileUtils.writeStringToFile(new File(parentDirFile2Path), content2, StandardCharsets.UTF_8);
//
//        //ایجاد یک تصویر نمونه
//        final BufferedImage image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
//        final Graphics2D graphics2D = image.createGraphics();
//        graphics2D.setPaint(Color.WHITE);
//        graphics2D.fillRect(0, 0, 200, 200);
//        graphics2D.setPaint(Color.BLACK);
//        graphics2D.drawOval(0, 0, 200, 200);
//        graphics2D.dispose();
//
//        //ایجاد پوشه اول با یک فایل متنی و یک فایل تصویری
//        FileUtils.forceMkdir(new File(dir1Path));
//        FileUtils.writeStringToFile(new File(dir1File1Path), content1, StandardCharsets.UTF_8);
//        ImageIO.write(image, "JPG", new File(dir1File2Path));
//        for (Integer size : fsoConfigModel.getThumbSizeArray()) {
//            ImageTools.createThumb(dir1Path, "dir1file2.jpg", size, size);
//        }
//
//        //ایجاد پوشه دوم با یک فایل متنی و یک فایل تصویری
//        FileUtils.forceMkdir(new File(dir2Path));
//        FileUtils.writeStringToFile(new File(dir2File1Path), content1, StandardCharsets.UTF_8);
//        ImageIO.write(image, "JPG", new File(dir2File2Path));
//        for (Integer size : fsoConfigModel.getThumbSizeArray()) {
//            ImageTools.createThumb(dir2Path, "dir2file2.jpg", size, size);
//        }

        Locale.setDefault(new Locale("fa", "IR"));
    }

    /**
     * این متد بعد از هر تست این کلاس اجرا میشود
     */
    @AfterEach
    void finalizeEach() throws IOException {
        Locale.setDefault(Locale.US);
//        File parentDir = new File(parentDirPath);
//        if (parentDir.exists()) {
//            FileUtils.deleteDirectory(parentDir);
//        }
    }

//    @Order(1)
//    @Test
//    void pathExistCheckTest() {
//        try {
////            assertThat(FsoTools.pathExistCheck(parentDirPath).getTypeEnum()).isEqualTo(FsoPathCheckTypeEnum.DIRECTORY);
//        } catch (Exception ex) {
//            fail(ex.toString());
//        }
//    }


}
