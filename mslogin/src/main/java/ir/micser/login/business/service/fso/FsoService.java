package ir.micser.login.business.service.fso;

import com.motaharinia.msutility.fso.check.FsoPathCheckModel;
import com.motaharinia.msutility.fso.download.FileDownloadModel;
import com.motaharinia.msutility.fso.upload.FileUploadedModel;
import com.motaharinia.msutility.fso.view.FileViewModel;
import ir.micser.login.presentation.fso.crudfilehandle.CrudFileHandleModel;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 02:33:39<br>
 * Description:<br>
 * اینترفیس سرویس فایل سیستم
 */
public interface FsoService {

    /**
     * این مند یک مسیر اصلی و فرعی را دریافت میکند و لیست مدل نمایش تمام فایلهای داخل آن را خروجی میدهد
     *
     * @param subSystemEntityEntityIdFileKindPath "/common/socialgroup/27/logo/" مسیر حاوی زیرسیستم و انتیتی و شناسه انتیتی و نوع فایل. مثال
     * @param entityIdFileKindPath                "/27/logo/" مسیر حاوی شناسه انتیتی و نوع فایل. مثال
     * @return خروجی: لیست مدل نمایش تمام فایلهای داخل مسیر
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    List<FileViewModel> fileViewModelList(@NotNull String subSystemEntityEntityIdFileKindPath, @NotNull String entityIdFileKindPath) throws Exception;

    /**
     * این متد لیستی از مسیرها شامل فایل و پوشه را از ورودی دریافت میکند و آنها را حذف میکند
     *
     * @param pathList لیست مسیرها برای حذف
     */
    void delete(@NotNull List<String> pathList);

    /**
     * این متد یک مسیر را از ورودی دریافت میکند و آن را در صورت عدم وجود می سازد
     *
     * @param directoryPath مسیر جهت ایجاد
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    void createDirectory(@NotNull String directoryPath) throws Exception;

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
    void copy(@NotNull String pathFrom, @NotNull String pathTo, @NotNull Boolean withThumbnail, @NotNull Boolean withDirectoryCreation, @NotNull Boolean withRenameOnExist) throws Exception;


    /**
     * این متد یک مسیر مبدا و یک مسیر مقصد و یک سوال که آیا مسیر مبدا تصویر بندانگشتی دارد یا خیر و و یک سوال که در صورت وجود نداشتن مسیر آن را بسازد یا خیر از ورودی دریافت میکند و مسیر مبدا را به مسیر مقصد انتقال/تغییرنام میدهد
     *
     * @param pathFrom              مسیر مبدا
     * @param pathTo                مسیر مقصد
     * @param withThumbnail         مسیر مبدا حاوی تصویر بندانگشتی
     * @param withDirectoryCreation در صورت عدم وجود مسیر مقصد آن را ایجاد کند؟
     */
    void move(@NotNull String pathFrom, @NotNull String pathTo, @NotNull Boolean withThumbnail, @NotNull Boolean withDirectoryCreation);

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
    FileDownloadModel download(@NotNull String subSystemAndEntityPath, @NotNull String hashedPath, String thumbSize) throws Exception;


    /**
     * این متد یک مسیر حاوی زیرسیستم و انتیتی و یک مسیر هش شده شناسه انتیتی تا فایل و اندازه تصویر بندانگشتی از ورودی دریافت میکند و مدل دانلود فایل را خروجی میدهد
     *
     * @param subSystemAndEntityPath "/common/socialgroup/" مسیر حاوی زیرسیستم و انتیتی و شناسه انتیتی و نوع فایل. مثال
     * @param entityIdFileKindPath   "/27/logo/" مسیر حاوی شناسه انتیتی و نوع فایل. مثال
     * @param thumbSize              ابعاد تصویر بندانگشتی
     * @return خروجی: مدل دانلود فایل
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    FileDownloadModel downloadSingle(@NotNull String subSystemAndEntityPath, @NotNull String entityIdFileKindPath, String thumbSize) throws Exception;

    /**
     * این متد یک مدل فایل آپلود شده در مسیر آپلود را از ورودی دریافت میکند و بعد از انتقال آن در مسیر ماژول نظر ، مسیر رمزگذاری شده فایل جابجا شده را خروجی میدهد
     *
     * @param fileUploadedModel مدل فایل آپلود شده
     * @return خروجی: مسیر رمزگذاری شده فایل ثبت شده
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    String uploadedFileHandleToModule(@NotNull FileUploadedModel fileUploadedModel) throws Exception;

    /**
     * این متد آرایه بایت داده فایل زیپ و مسیری برای استخراج فایل زیپ و رمز استخراج فایل زیپ را از ورودی دریافت میکند و در مسیر آن را استخراج مینماید
     *
     * @param sourceByteArray              آرایه بایت داده فایل زیپ
     * @param destinationDirectoryForUnzip مسیری برای استخراج فایل زیپ
     * @param password                     رمز استخراج فایل زیپ
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    void unzipFromByteArray(@NotNull byte[] sourceByteArray, @NotNull String destinationDirectoryForUnzip, String password) throws Exception;


    /**
     * این متد مسیر فایل زیپ و مسیری برای استخراج فایل زیپ و رمز استخراج فایل زیپ را از ورودی دریافت میکند و در مسیر آن را استخراج مینماید
     *
     * @param sourceZipFilePath            مسیر فایل زیپ
     * @param destinationDirectoryForUnzip مسیری برای استخراج فایل زیپ
     * @param password                     رمز استخراج فایل زیپ
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    void unzip(@NotNull String sourceZipFilePath, @NotNull String destinationDirectoryForUnzip, String password) throws Exception;

    /**
     * این متد یک مسیر کامل فایل یا دایرکتوری ورودی دریافت میکند و چک میکند آن مسیر وجود داشته باشد و مدل حاوی نوع مسیر (فایل یا دایرکتوری) و مرجع فایل را خروجی میدهد
     *
     * @param path مسیر کامل فایل یا دایرکتوری ورودی
     * @return خروجی: مدل حاوی نوع مسیر (فایل یا دایرکتوری) و مرجع فایل
     */
    @NotNull
    FsoPathCheckModel pathExistCheck(@NotNull String path);

    /**
     * این متد یک مسیر فایل از ورودی دریافت میکند و آرایه بایت داده داخل آن را خروجی میدهد
     *
     * @param realPath مسیر فایل
     * @return خروجی:  آرایه بایت داده داخل آن
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    byte[] readFile(@NotNull String realPath) throws Exception;

    /**
     * این متد یک شیی فایل از ورودی دریافت میکند و آرایه بایت داده داخل آن را خروجی میدهد
     *
     * @param file شیی فایل
     * @return خروجی:  آرایه بایت داده داخل آن
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    byte[] readFromFile(@NotNull File file) throws Exception;

    /**
     * این متد طبق شرایط ورودی لیست مدلهای کامل اطلاعات فایلهای یک ماژول را خروجی میدهد
     *
     * @param fsoModuleEnum چه فایلی از چه ماژولی را میخواهیم
     * @param entityId      شناسه انتیتی مورد نظر که فایلهای آن را میخواهیم
     * @return خروجی: لیستی از مدل مشاهده فایل
     * @throws Exception خطا
     */
    @NotNull
    List<FileViewModel> getFileViewModelList(@NotNull FsoModuleEnum fsoModuleEnum, @NotNull Integer entityId) throws Exception;

    /**
     * این متد یک مدل کراد فایل را میگیرد و فایل را نسبت به فعل ثبت یا ویرایش یا حذف در فایل سیستم سامانه مدیریت میکند
     *
     * @param crudFileHandleModel مدل کراد فایل
     * @throws Exception خطا
     */
    void crudHandle(@NotNull CrudFileHandleModel crudFileHandleModel) throws Exception;
}
