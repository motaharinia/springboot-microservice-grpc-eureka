package ir.micser.login.business.service.fso;

import ir.micser.login.presentation.fso.crudfilehandle.CrudFileHandleModel;
import ir.micser.login.presentation.fso.FsoUploadedFileModel;
import ir.micser.login.presentation.fso.backuploader.FileUploadChunkModel;
import ir.micser.login.presentation.fso.frontuploader.FineUploaderChunkModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 * اینترفییس سرویس فایلهای آپلود شده<br>
 */

@Component
public interface FsoUploadedFileService {

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به بک پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile        آرایه داده ارسالی از کلاینت
     * @param fileUploadChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception خطا
     */
    FsoUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FileUploadChunkModel fileUploadChunkModel) throws Exception;

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به فرانت پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile          آرایه داده ارسالی از کلاینت
     * @param fineUploaderChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception خطا
     */
    FsoUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FineUploaderChunkModel fineUploaderChunkModel) throws Exception;


    /**
     * این متد یک لاگ دیتابیس از اطلاعات فایل آپلود شده در دیتابیس ذخیره مینماید
     *
     * @param fsoUploadedFileModel مدل فایل آپلود شده
     * @return خروجی: مدل فایل آپلود شده
     * @throws Exception خطا
     */
    FsoUploadedFileModel create(FsoUploadedFileModel fsoUploadedFileModel) throws Exception;

    /**
     * این متد کلید فایل مورد نظر فایل آپلود شده را از ورودی دریافت کرده و مدل آن را خروجی میدهد
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception خطا
     */
    FsoUploadedFileModel readByFileKey(String fileKey) throws Exception;

    /**
     * این متد کلید فایل آپلود شده مورد نظر را از ورودی دریافت کرده و آن را حذف مینماید
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @throws Exception خطا
     */
    void delete(String fileKey) throws Exception;

}
