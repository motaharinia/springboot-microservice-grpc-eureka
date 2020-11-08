package ir.micser.login.presentation.fso;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motaharinia.msutility.fso.download.FileDownloadModel;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msutility.json.PrimitiveResponse;
import ir.micser.login.business.service.fso.FsoService;
import ir.micser.login.business.service.fso.FsoThumbSizeEnum;
import ir.micser.login.business.service.fso.FsoUploadedFileService;
import ir.micser.login.business.service.SubSystemEnum;
import ir.micser.login.presentation.fso.backuploader.FileUploadChunkModel;
import ir.micser.login.presentation.fso.frontuploader.FineUploaderChunkModel;
import ir.micser.login.presentation.fso.frontuploader.FineUploaderResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

@RestController
@RequestMapping({"/fso"})
public class FsoController {

    private final FsoUploadedFileService fsoUploadedFileService;
    private final FsoService fsoService;

    @Autowired
    public FsoController(FsoUploadedFileService fsoUploadedFileService, FsoService fsoService) {
        this.fsoUploadedFileService = fsoUploadedFileService;
        this.fsoService = fsoService;
    }


    /**
     * این متد با دریافت تکه تکه داده فایل از کلاینت بک پنل فایل را آپلود و در سرور ذخیره مینماید
     *
     * @param subSystem زیرسیستم انتیتی
     * @param entity    انتیتی
     * @param file      تکه داده در حال آپلود فایل
     * @param params    اطلاعات در مورد فایل در حال آپلود
     * @return خروجی: در صورت عدم وجود خطا همیشه مقدار ترو خروجی میدهد
     * @throws Exception خطا
     */
    @RequestMapping(value = "/upload/{subSystem}/{entity}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    PrimitiveResponse uploadBackPanel(
            @PathVariable("subSystem") SubSystemEnum subSystem,
            @PathVariable("entity") String entity,
            @RequestParam(required = true) MultipartFile file,
            @RequestParam(required = true) String params
    ) throws Exception {
        ObjectMapper mapper = new CustomObjectMapper();
        FileUploadChunkModel fileUploadChunkModel = mapper.readValue(params, FileUploadChunkModel.class);
        fileUploadChunkModel.setSubSystem(subSystem);
        fileUploadChunkModel.setEntity(entity);
        //ارسال اطلاعات قسمتی از اپلود برای سرویس آپلود فایل
        fsoUploadedFileService.uploadToFileModel(file, fileUploadChunkModel);
        //ایجاد خروجی برای آپلودر بک پنل
        return new PrimitiveResponse(true);
    }

    /**
     * این متد با دریافت تکه تکه داده فایل از کلاینت بک پنل فایل را آپلود و در سرور ذخیره مینماید
     *
     * @param subSystem        زیرسیستم انتیتی
     * @param entity           انتیتی
     * @param qqfile           تکه داده در حال آپلود فایل
     * @param qquuid           کلید فایلی که در کلاینت تولید میشود و برای هر فایل در حال آپلود یونیک است
     * @param qqfilename       نام فایل در کلاینت
     * @param qqtotalparts     تعداد تکه های داده فایل
     * @param qqpartindex      شماره تکه فعلی از داده فایل در حال ارسال
     * @param qqpartbyteoffset آفست داده
     * @param qqchunksize      حجم هر تکه داده فایل جهت آپلود
     * @param qqtotalfilesize  حجم کل فایل
     * @return خروجی: در صورت عدم وجود خطا همیشه مقدار ترو خروجی میدهد
     * @throws Exception خطا
     */
    @RequestMapping(value = "/upload/{subSystem}/{entity}/fine", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    FineUploaderResponseModel uploadFrontPanel(
            @PathVariable("subSystem") SubSystemEnum subSystem,
            @PathVariable("entity") String entity,
            @RequestParam(required = true) MultipartFile qqfile,
            @RequestParam(required = true) String qquuid,
            @RequestParam(required = true) String qqfilename,
            @RequestParam(required = true, defaultValue = "1") Integer qqtotalparts,
            @RequestParam(required = true, defaultValue = "0") Integer qqpartindex,
            @RequestParam(required = false, defaultValue = "0") Integer qqpartbyteoffset,
            @RequestParam(required = false, defaultValue = "0") Long qqchunksize,
            @RequestParam(required = false) Long qqtotalfilesize
    ) throws Exception {
        FineUploaderChunkModel fineUploaderChunkModel = new FineUploaderChunkModel();
        fineUploaderChunkModel.setSubSystem(subSystem);
        fineUploaderChunkModel.setEntity(entity);
        fineUploaderChunkModel.setFileKey(qquuid);
        fineUploaderChunkModel.setName(qqfilename);
        fineUploaderChunkModel.setChunks(qqtotalparts);
        fineUploaderChunkModel.setChunk(qqpartindex);
        //ارسال اطلاعات قسمتی از اپلود برای سرویس آپلود فایل
        fsoUploadedFileService.uploadToFileModel(qqfile, fineUploaderChunkModel);
        //ایجاد خروجی جهت آپلود فایل در فرانت پنل
        FineUploaderResponseModel fineUploaderResponseModel = new FineUploaderResponseModel();
        fineUploaderResponseModel.setSuccess(Boolean.TRUE);
        return fineUploaderResponseModel;
    }


    /**
     * این متد بر اساس زیرسیستم و انتیتی و مسیر هش شده و اینکه آیا برای مشاهده یا دانلود و با چه اندازه ای نیاز است داده فایل را دانلود و در اختیار کلاینت قرار میدهد
     *
     * @param response    شیی پاسخ به کلاینت
     * @param subSystem   زیرسیستم انتیتی
     * @param entity      انتیتی
     * @param hashedPath  مسیر هش شده فایل
     * @param forDownload آیا پنجره دانلود نمایش داده شود؟
     * @param thumbSize   اندازه تصویر بندانگشتی
     * @throws Exception خطا
     */
    @RequestMapping(value = "/download/{subSystem}/{entity}/{hashedPath}/", method = RequestMethod.GET)
    public void download(HttpServletResponse response,
                         @PathVariable("subSystem") SubSystemEnum subSystem,
                         @PathVariable("entity") String entity,
                         @PathVariable("hashedPath") String hashedPath,
                         @RequestParam(required = false, defaultValue = "false") Boolean forDownload,
                         @RequestParam(required = false, defaultValue = "") FsoThumbSizeEnum thumbSize) throws Exception {
        String mainPath = "/" + removeNonAlphabetic(subSystem.getValue()) + "/" + removeNonAlphabetic(entity);
        String thumbSizeString = null;
        if (thumbSize != null) {
            thumbSizeString = thumbSize.getValue().toLowerCase();
        }
        FileDownloadModel fileDownloadModel = fsoService.download(mainPath.toLowerCase(), hashedPath, thumbSizeString);
        //تنظیمات هدر برای ارسال به کلاینت
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setDateHeader("Max-Age", 0);
        response.setContentType(fileDownloadModel.getMimeType());
        response.setContentLength(fileDownloadModel.getSize().intValue());

        String fileName = URLEncoder.encode(fileDownloadModel.getFullName(), "UTF-8");
        fileName = URLDecoder.decode(fileName, "ISO8859_1");

        //اگر درخواست شده پنجره دانلود برای کلاینت باز شود
        if (forDownload) {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileName));
        } else {
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""));
        }
        //نوشتن داده فایل بر روی شیی پاسخ به کلاینت
        OutputStream outputStream = response.getOutputStream();
        FileCopyUtils.copy(fileDownloadModel.getDataByteArray(), outputStream);
        outputStream.close();
        outputStream.flush();
    }

    /**
     * این متد بر اساس زیرسیستم و نوع تصویر مورد نیاز در انتیتی با شناسه مورد نظر و اینکه آیا برای مشاهده یا دانلود و با چه اندازه ای نیاز است داده فایل را دانلود و در اختیار کلاینت قرار میدهد<br>
     * این متد فقط برای نوع فایلهایی در انتیتی ها استفاده میشود که از ابتدا قرار بوده تک فایل باشند مثل تصویر پروفایل یک کاربر
     *
     * @param response       شیی پاسخ به کلاینت
     * @param subSystem      زیرسیستم انتیتی
     * @param entity         انتیتی
     * @param entityId       شناسه انتیتی
     * @param fileKindFolder نوع فایل انتیتی
     * @param forDownload    آیا پنجره دانلود نمایش داده شود؟
     * @param thumbSize      اندازه تصویر بندانگشتی
     * @throws Exception خطا
     */
    @RequestMapping(value = "/download/single/{subSystem}/{entity}/{entityId}/{fileKindFolder}/", method = RequestMethod.GET)
    public void downloadSingle(HttpServletResponse response,
                               @PathVariable("subSystem") SubSystemEnum subSystem,
                               @PathVariable("entity") String entity,
                               @PathVariable("entityId") Integer entityId,
                               @PathVariable("fileKindFolder") String fileKindFolder,
                               @RequestParam(required = false, defaultValue = "false") Boolean forDownload,
                               @RequestParam(required = false, defaultValue = "") FsoThumbSizeEnum thumbSize) throws Exception {
        String mainPath = "/" + removeNonAlphabetic(subSystem.getValue()) + "/" + removeNonAlphabetic(entity);
        String singleDirectoryPath = "/" + entityId.toString() + "/" + fileKindFolder + "/";
        String thumbSizeString = null;
        if (thumbSize != null) {
            thumbSizeString = thumbSize.getValue().toLowerCase();
        }
        FileDownloadModel fileDownloadModel = fsoService.downloadSingle(mainPath.toLowerCase(), singleDirectoryPath.toLowerCase(), thumbSizeString);
        //تنظیمات هدر برای ارسال به کلاینت
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setDateHeader("Max-Age", 0);
        response.setContentType(fileDownloadModel.getMimeType());
        response.setContentLength(fileDownloadModel.getSize().intValue());

        //اگر درخواست شده پنجره دانلود برای کلاینت باز شود
        if (forDownload) {
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", fileDownloadModel.getFullName()));
        } else {
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + fileDownloadModel.getFullName() + "\""));
        }
        //نوشتن داده فایل بر روی شیی پاسخ به کلاینت
        OutputStream outputStream = response.getOutputStream();
        FileCopyUtils.copy(fileDownloadModel.getDataByteArray(), outputStream);
        outputStream.close();
        outputStream.flush();
    }


    /**
     * این متد یک کلید فایل آپلود شده را از ورودی دریافت کرده و آن را از مسیر فایلهای آپلود شده حذف میکند
     *
     * @param fileKey کلید فایل
     * @throws Exception خطا
     */
    @RequestMapping(value = "/deleteUploadedFile/{fileKey}", method = RequestMethod.DELETE)
    public void deleteUploadedFile(@PathVariable("fileKey") String fileKey) throws Exception {
        fsoUploadedFileService.delete(fileKey);
    }

    /**
     * این متد یک رشته از ورودی دریافت میکند و کارکترهای غیرالفبایی انگلیسی آن را حذف کرده و خروجی میدهد
     *
     * @param input رشته ورودی
     * @return خروجی: رشته ای که فقط حروف انگلیسی دارد
     */
    private String removeNonAlphabetic(String input) {
        input = input.replaceAll("[^a-zA-Z]", "");
        return input;
    }
}
