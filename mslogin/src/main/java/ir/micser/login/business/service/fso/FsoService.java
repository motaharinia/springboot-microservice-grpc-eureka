package ir.micser.login.business.service.fso;


import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.customfield.CustomDate;
import com.motaharinia.msutility.encoding.EncodingTools;
import com.motaharinia.msutility.fso.FsoConfigModel;
import com.motaharinia.msutility.fso.FsoTools;
import com.motaharinia.msutility.fso.check.FsoPathCheckModel;
import com.motaharinia.msutility.fso.check.FsoPathCheckTypeEnum;
import com.motaharinia.msutility.fso.content.FsoPathContentModel;
import com.motaharinia.msutility.fso.download.FileDownloadModel;
import com.motaharinia.msutility.fso.mimetype.FsoMimeTypeEnum;
import com.motaharinia.msutility.fso.mimetype.FsoMimeTypeModel;
import com.motaharinia.msutility.fso.upload.FileUploadedModel;
import com.motaharinia.msutility.fso.view.FileViewModel;
import com.motaharinia.msutility.zip.ZipTools;
import ir.micser.login.business.service.loguploadedfile.LogUploadedFsoEnum;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 02:33:39<br>
 * Description:<br>
 * سرویس فایل سیستم
 */
@Service
public class FsoService {

    /**
     * پسوند فایلهای بندانگشتی تصاویر
     */
    @Value("${fso.image.thumb.extension}")
    private String FSO_IMAGE_THUMB_EXTENSION = "thumb";
    /**
     * ابعاد فایلهای بندانگشتی کوچک تصاویر
     */
    @Value("${fso.image.thumb.size.small}")
    private Integer FSO_THUMB_SIZE_SMALL = 60;
    /**
     * ابعاد فایلهای بندانگشتی بزرگ تصاویر
     */
    @Value("${fso.image.thumb.size.large}")
    private Integer FSO_THUMB_SIZE_LARGE = 120;
    /**
     * حداکثر تعداد مجاز فایل در یک دایرکتوری
     */
    @Value("${fso.directory.file.limit}")
    private Integer FSO_DIRECTORY_FILE_LIMIT = 100;
    /**
     * مسیر فایلهای ماژولهای پروزه
     */
    @Value("${fso.path.module}")
    private String FSO_PATH_MODULE = "/fso/module";


    private FsoConfigModel FSO_CONFIG_MODEL = new FsoConfigModel(new Integer[]{FSO_THUMB_SIZE_SMALL, FSO_THUMB_SIZE_LARGE}, FSO_IMAGE_THUMB_EXTENSION, FSO_DIRECTORY_FILE_LIMIT);

    /**
     * این مند یک مسیر اصلی و فرعی را دریافت میکند و لیست مدل نمایش تمام فایلهای داخل آن را خروجی میدهد
     *
     * @param subSystemEntityEntityIdFileKindPath "/common/socialgroup/27/logo/" مسیر حاوی زیرسیستم و انتیتی و شناسه انتیتی و نوع فایل. مثال
     * @param entityIdFileKindPath                "/27/logo/" مسیر حاوی شناسه انتیتی و نوع فایل. مثال
     * @return خروجی: لیست مدل نمایش تمام فایلهای داخل مسیر
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    public List<FileViewModel> fileViewModelList(@NotNull String subSystemEntityEntityIdFileKindPath, @NotNull String entityIdFileKindPath) throws Exception {
        List<FileViewModel> fileViewModelList = new ArrayList<>();
        FileViewModel fileViewModel = null;
        String fullPath = FSO_PATH_MODULE + subSystemEntityEntityIdFileKindPath;
        FsoPathContentModel fsoPathContentModel = FsoTools.pathContent(fullPath, new String[]{}, new String[]{}, new String[]{"Thumbs.db"}, new String[]{"thumb"}, false);
        for (int i = 0; i < fsoPathContentModel.getFileModelList().size(); i++) {
            fileViewModel = new FileViewModel();
            fileViewModel.setLastModifiedDate(new CustomDate(fsoPathContentModel.getFileModelList().get(i).getLastModifiedDate()));
            fileViewModel.setFullPath(entityIdFileKindPath + fsoPathContentModel.getFileModelList().get(i).getFullName());
            fileViewModel.setHashedPath(EncodingTools.base64Encode(fileViewModel.getFullPath()));
            fileViewModel.setFullName(fsoPathContentModel.getFileModelList().get(i).getFullName());
            fileViewModel.setName(fsoPathContentModel.getFileModelList().get(i).getName());
            fileViewModel.setExtension(fsoPathContentModel.getFileModelList().get(i).getExtension());
            fileViewModel.setMimeType(fsoPathContentModel.getFileModelList().get(i).getMimeType());
            Long size=fsoPathContentModel.getFileModelList().get(i).getSize();
            fileViewModel.setSize(size);
            fileViewModelList.add(fileViewModel);
        }
        return fileViewModelList;
    }


    /**
     * این متد لیستی از مسیرها شامل فایل و پوشه را از ورودی دریافت میکند و آنها را حذف میکند
     *
     * @param pathList لیست مسیرها برای حذف
     * @throws Exception خطا
     */
    public void delete(@NotNull List<String> pathList) throws Exception {
        FsoMimeTypeModel fsoMimeTypeModel;
        for (String pathFile : pathList) {
            FsoPathCheckModel fsoPathCheckModel = null;
            try {
                fsoPathCheckModel = pathExistCheck(FSO_PATH_MODULE + pathFile);
            } catch (Exception exception) {

            }
            if (!ObjectUtils.isEmpty(fsoPathCheckModel)) {
                if (fsoPathCheckModel.getTypeEnum().equals(FsoPathCheckTypeEnum.FILE)) {
                    fsoMimeTypeModel = FsoTools.getMimeTypeModel(FSO_PATH_MODULE + pathFile);
                    if (fsoMimeTypeModel.getType().equals(FsoMimeTypeEnum.IMAGE)) {
                        FsoTools.delete(FSO_PATH_MODULE + pathFile, true, FSO_CONFIG_MODEL);
                    } else {
                        FsoTools.delete(FSO_PATH_MODULE + pathFile, false, FSO_CONFIG_MODEL);
                    }
                } else {
                    FsoTools.delete(FSO_PATH_MODULE + pathFile, false, FSO_CONFIG_MODEL);
                }
            }
        }
    }


    /**
     * این متد یک مسیر را از ورودی دریافت میکند و آن را در صورت عدم وجود می سازد
     *
     * @param directoryPath مسیر جهت ایجاد
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public void createDirectory(@NotNull String directoryPath) throws Exception {
        FsoTools.createDirectoryIfNotExist(FSO_PATH_MODULE + directoryPath);
    }


    /**
     * این متد مسیر مبدا و مسیر مقصد و یک سوال که آیا مسیر ورودی تصویر بندانگشتی دارد یا خیر و یک سوال که آیا مسیر مقصد در صورت عدم وجود ایجاد شود و یک سوال که در صورت وجود مسیر در مقصد یک نام جدید با -copy بسازد را از ورودی دریافت میکند و مسیر مبدا را در مسیر مقصد کپی میکند <br>
     * اگر مسیر مقصد از قبل وجود داشته باشد مانند ویندوز نام مقصد را غیرتکراری میکند و کپی را انجام میدهد
     *
     * @param pathFrom              مسیر مبدا که میتواند دایرکتوری یا فایل باشد
     * @param pathTo                مسیر مقصد که اگر مسیر مبدا فایل بوده باید این مسیر نیز مسیر کامل فایل باشد
     * @param withThumbnail         مسیر مبدا حاوی تصویر بندانگشتی
     * @param withDirectoryCreation در صورت عدم وجود مسیر مقصد آن را ایجاد کند؟
     * @param withRenameOnExist     در صورت وجود مسیر در مقصد یک نام جدید با -copy بسازد
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public void copy(@NotNull String pathFrom, @NotNull String pathTo, @NotNull Boolean withThumbnail, @NotNull Boolean withDirectoryCreation, @NotNull Boolean withRenameOnExist) throws Exception {
        pathTo = FSO_PATH_MODULE + pathTo;
        pathFrom = FSO_PATH_MODULE + pathFrom;
        FsoTools.copy(pathFrom, pathTo, withThumbnail, FSO_CONFIG_MODEL, withDirectoryCreation, withRenameOnExist);
    }


    /**
     * این متد یک مسیر مبدا و یک مسیر مقصد و یک سوال که آیا مسیر مبدا تصویر بندانگشتی دارد یا خیر و و یک سوال که در صورت وجود نداشتن مسیر آن را بسازد یا خیر از ورودی دریافت میکند و مسیر مبدا را به مسیر مقصد انتقال/تغییرنام میدهد
     *
     * @param pathFrom              مسیر مبدا
     * @param pathTo                مسیر مقصد
     * @param withThumbnail         مسیر مبدا حاوی تصویر بندانگشتی
     * @param withDirectoryCreation در صورت عدم وجود مسیر مقصد آن را ایجاد کند؟
     */
    public void move(@NotNull String pathFrom, @NotNull String pathTo, @NotNull Boolean withThumbnail, @NotNull Boolean withDirectoryCreation) {
        pathTo = FSO_PATH_MODULE + pathTo;
        pathFrom = FSO_PATH_MODULE + pathFrom;
        FsoTools.move(pathFrom, pathTo, withThumbnail, FSO_CONFIG_MODEL, withDirectoryCreation);
    }


    /**
     * این متد یک مسیر حاوی زیرسیستم و انتیتی و یک مسیر هش شده شناسه انتیتی تا فایل و اندازه تصویر بندانگشتی از ورودی دریافت میکند و مدل دانلود فایل را خروجی میدهد
     *
     * @param subSystemAndEntityPath مسیر حاوی زیرسیستم و انتیتی
     * @param hashedPath             مسیر هش شده شناسه انتیتی تا فایل
     * @param thumbSize              ابعاد تصویر بندانگشتی
     * @return خروجی: مدل دانلود فایل
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    public FileDownloadModel download(@NotNull String subSystemAndEntityPath, @NotNull String hashedPath, String thumbSize) throws Exception {
        String fileFullPath = FSO_PATH_MODULE + subSystemAndEntityPath + EncodingTools.base64decode(hashedPath);

        //اگر درخواست تصویر بندانگشتی شده بود آن را به جای فایل اصلی در مسیر قرار میدهیم
        if (!ObjectUtils.isEmpty(thumbSize)) {
            fileFullPath = fileFullPath + "-" + thumbSize + "." + FSO_IMAGE_THUMB_EXTENSION;
        }
        FsoPathCheckModel fsoPathCheckModel = FsoTools.pathExistCheck(fileFullPath);
        if (!fsoPathCheckModel.getTypeEnum().equals(FsoPathCheckTypeEnum.FILE)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_PATH_IS_NOT_FILE, "fileFullPath:" + fileFullPath);
        }

        //تکمیل اطلاعات مدل دانلود
        FileDownloadModel fileDownloadModel = new FileDownloadModel();
        Path path = Paths.get(fileFullPath);
        byte[] dataByteArray = FsoTools.downloadPathAndRead(fileFullPath);
        fileDownloadModel.setDataByteArray(dataByteArray);
        fileDownloadModel.setSize(Long.valueOf(dataByteArray.length));
        fileDownloadModel.setMimeType(Files.probeContentType(path));
        fileDownloadModel.setFullName(path.getFileName().toString());
        fileDownloadModel.setName(FsoTools.getFileNameWithoutExtension(fileDownloadModel.getFullName()));
        fileDownloadModel.setExtension(FsoTools.getFileExtension(fileDownloadModel.getFullName()));
        return fileDownloadModel;
    }


    /**
     * این متد یک مسیر حاوی زیرسیستم و انتیتی و یک مسیر هش شده شناسه انتیتی تا فایل و اندازه تصویر بندانگشتی از ورودی دریافت میکند و مدل دانلود فایل را خروجی میدهد
     *
     * @param subSystemAndEntityPath "/common/socialgroup/" مسیر حاوی زیرسیستم و انتیتی و شناسه انتیتی و نوع فایل. مثال
     * @param entityIdFileKindPath   "/27/logo/" مسیر حاوی شناسه انتیتی و نوع فایل. مثال
     * @param thumbSize              ابعاد تصویر بندانگشتی
     * @return خروجی: مدل دانلود فایل
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public FileDownloadModel downloadSingle(String subSystemAndEntityPath, String entityIdFileKindPath, String thumbSize) throws Exception {
        String fileFullPath = FSO_PATH_MODULE + subSystemAndEntityPath + entityIdFileKindPath;

        //به دست آوردن اولین فایل موجود در مسیر داده شده
        FsoPathContentModel fsoPathContentModel = FsoTools.pathContent(fileFullPath, new String[]{}, new String[]{}, new String[]{"Thumbs.db"}, new String[]{"thumb"}, false);
        if (!ObjectUtils.isEmpty(fsoPathContentModel.getFileModelList())) {
            fileFullPath = fileFullPath + fsoPathContentModel.getFileModelList().get(0).getFullName();
            //اگر درخواست تصویر بندانگشتی شده بود آن را به جای فایل اصلی در مسیر قرار میدهیم
            if (!ObjectUtils.isEmpty(thumbSize)) {
                fileFullPath = fileFullPath + "-" + thumbSize + "." + FSO_IMAGE_THUMB_EXTENSION;
            }
        }

        //بررسی وجود فایل
        FsoTools.pathExistCheck(fileFullPath);

        //تکمیل اطلاعات مدل دانلود
        FileDownloadModel fileDownloadModel = new FileDownloadModel();
        Path path = Paths.get(fileFullPath);
        byte[] dataByteArray = FsoTools.downloadPathAndRead(fileFullPath);
        fileDownloadModel.setDataByteArray(dataByteArray);
        fileDownloadModel.setSize(Long.valueOf(dataByteArray.length));
        fileDownloadModel.setMimeType(Files.probeContentType(path));
        fileDownloadModel.setFullName(path.getFileName().toString());
        fileDownloadModel.setName(FsoTools.getFileNameWithoutExtension(fileDownloadModel.getFullName()));
        fileDownloadModel.setExtension(FsoTools.getFileExtension(fileDownloadModel.getFullName()));
        return fileDownloadModel;
    }

    /**
     * این متد یک مسیر دایرکتوری و نام کامل فایل و آرایه بایت داده را از ورودی دریافت میکند و بعد از ثبت آرایه بایت در مسیر مورد نظر ، مسیر رمزگذاری شده فایل ثبت شده را خروجی میدهد
     *
     * @param fileUploadedModel مدل آپلود فایل
     * @return خروجی: مسیر رمزگذاری شده فایل ثبت شده
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public String upload(FileUploadedModel fileUploadedModel) throws Exception {
        FsoMimeTypeModel fsoMimeTypeModel = FsoTools.getMimeTypeModel(FSO_PATH_MODULE + fileUploadedModel.getDirectoryRealPath() + fileUploadedModel.getFullName());
        if (fsoMimeTypeModel.getType().equals(FsoMimeTypeEnum.IMAGE)) {
            return FsoTools.uploadWriteToPath(FSO_PATH_MODULE + fileUploadedModel.getDirectoryRealPath(), fileUploadedModel.getFullName(), fileUploadedModel.getDataByteArray(), true, FSO_CONFIG_MODEL);
        } else {
            return FsoTools.uploadWriteToPath(FSO_PATH_MODULE + fileUploadedModel.getDirectoryRealPath(), fileUploadedModel.getFullName(), fileUploadedModel.getDataByteArray(), false, FSO_CONFIG_MODEL);
        }
    }

    /**
     * این متد آرایه بایت داده فایل زیپ و مسیری برای استخراج فایل زیپ و رمز استخراج فایل زیپ را از ورودی دریافت میکند و در مسیر آن را استخراج مینماید
     *
     * @param sourceByteArray              آرایه بایت داده فایل زیپ
     * @param destinationDirectoryForUnzip مسیری برای استخراج فایل زیپ
     * @param password                     رمز استخراج فایل زیپ
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public void unzipFromByteArray(byte[] sourceByteArray, String destinationDirectoryForUnzip, String password) throws Exception {
        ZipTools.unzipFromByteArray(sourceByteArray, FSO_PATH_MODULE + destinationDirectoryForUnzip, password);
    }

    /**
     * این متد مسیر فایل زیپ و مسیری برای استخراج فایل زیپ و رمز استخراج فایل زیپ را از ورودی دریافت میکند و در مسیر آن را استخراج مینماید
     *
     * @param sourceZipFilePath            مسیر فایل زیپ
     * @param destinationDirectoryForUnzip مسیری برای استخراج فایل زیپ
     * @param password                     رمز استخراج فایل زیپ
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public void unzip(String sourceZipFilePath, String destinationDirectoryForUnzip, String password) throws Exception {
        ZipTools.unzip(FSO_PATH_MODULE + sourceZipFilePath, FSO_PATH_MODULE + destinationDirectoryForUnzip, password);
    }


    /**
     * این متد یک مسیر کامل فایل یا دایرکتوری ورودی دریافت میکند و چک میکند آن مسیر وجود داشته باشد و مدل حاوی نوع مسیر (فایل یا دایرکتوری) و مرجع فایل را خروجی میدهد
     *
     * @param path مسیر کامل فایل یا دایرکتوری ورودی
     * @return خروجی: مدل حاوی نوع مسیر (فایل یا دایرکتوری) و مرجع فایل
     */
    public FsoPathCheckModel pathExistCheck(String path) {
        return FsoTools.pathExistCheck(FSO_PATH_MODULE + path);
    }


    /**
     * این متد یک مسیر فایل از ورودی دریافت میکند و آرایه بایت داده داخل آن را خروجی میدهد
     *
     * @param realPath مسیر فایل
     * @return خروجی:  آرایه بایت داده داخل آن
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public byte[] readFile(String realPath) throws Exception {
        File file = new File(FSO_PATH_MODULE + realPath);
        return readFromFile(file);
    }

    /**
     * این متد یک شیی فایل از ورودی دریافت میکند و آرایه بایت داده داخل آن را خروجی میدهد
     *
     * @param file شیی فایل
     * @return خروجی:  آرایه بایت داده داخل آن
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    public byte[] readFromFile(File file) throws Exception {
        return FileUtils.readFileToByteArray(file);
    }

    //این متد طبق شرایط ورودی لیست مدلهای کامل اطلاعات فایلهای یک ماژول را خروجی میدهد
    //شرح ورودی ها:
    //LogUploadedFsoEnum logUploadedFsoEnum : چه فایلی از چه ماژولی را میخواهیم
    //Integer entityId : شناسه انتیتی مورد نظر که فایلهای آن را میخواهیم

    /**
     * @param logUploadedFsoEnum چه فایلی از چه ماژولی را میخواهیم
     * @param entityId           شناسه انتیتی مورد نظر که فایلهای آن را میخواهیم
     * @return خروجی: لیستی از مدل مشاهده فایل
     * @throws Exception خطا
     */
    public List<FileViewModel> getFileViewModelList(LogUploadedFsoEnum logUploadedFsoEnum, Integer entityId) throws Exception {
        //example: "/eshop/product/27/image3dfile/"
        String entityKindDirectoryPath = logUploadedFsoEnum.getEntityKindDirectoryPath(entityId);
        //example: "/27/image3dfile/"
        String kindDirectoryPath = logUploadedFsoEnum.getKindDirectoryPath(entityId);
        FsoPathCheckModel fsoPathCheckModel = null;
        try {
            fsoPathCheckModel = FsoTools.pathExistCheck(FSO_PATH_MODULE + entityKindDirectoryPath);
        } catch (Exception exception) {

        }
        if ((!ObjectUtils.isEmpty(fsoPathCheckModel)) && (fsoPathCheckModel.getTypeEnum().equals(FsoPathCheckTypeEnum.DIRECTORY))) {
            return this.fileViewModelList(entityKindDirectoryPath, kindDirectoryPath);
        } else {
            return new ArrayList<>();
        }
    }
}