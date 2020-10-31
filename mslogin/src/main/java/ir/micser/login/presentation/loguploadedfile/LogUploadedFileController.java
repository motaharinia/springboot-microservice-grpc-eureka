package ir.micser.login.presentation.loguploadedfile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.motaharinia.msutility.json.CustomObjectMapper;
import com.motaharinia.msutility.json.PrimitiveResponse;
import ir.micser.login.business.service.loguploadedfile.LogUploadedFileService;
import ir.micser.login.presentation.loguploadedfile.backuploader.FileUploadChunkModel;
import ir.micser.login.presentation.loguploadedfile.frontuploader.FineUploaderChunkModel;
import ir.micser.login.presentation.loguploadedfile.frontuploader.FineUploaderResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@RestController
@RequestMapping({"/fso"})
public class LogUploadedFileController {

    private final LogUploadedFileService logUploadedFileService;

    @Autowired
    public LogUploadedFileController(LogUploadedFileService logUploadedFileService) {
        this.logUploadedFileService = logUploadedFileService;
    }


    @RequestMapping(value = "/upload/{subSystem}/{entity}", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody PrimitiveResponse uploadBackPanel(HttpServletRequest request, Locale locale,
                                                           @RequestPart(value = "qqfile", required = true) MultipartFile qqfile,
                                                           @RequestParam String params, @PathVariable("subSystem") SubSystemEnum subSystem,
                                                           @PathVariable("entity") String entity) throws Exception {
        ObjectMapper mapper = new CustomObjectMapper();
        System.out.println("params:"+params);
        FileUploadChunkModel fileUploadChunkModel = mapper.readValue(params, FileUploadChunkModel.class);
        fileUploadChunkModel.setSubSystem(subSystem);
        fileUploadChunkModel.setEntity(entity);
        //ارسال اطلاعات قسمتی از اپلود برای سرویس آپلود فایل
        logUploadedFileService.uploadToFileModel(qqfile, fileUploadChunkModel);
        //ایجاد خروجی برای آپلودر بک پنل
        return new PrimitiveResponse(true);
    }

    @RequestMapping(value = "/upload/{subSystem}/{entity}/fine", method = RequestMethod.POST, produces = "application/json")
    public @ResponseBody
    FineUploaderResponseModel uploadFrontPanel(HttpServletRequest request, Locale locale, @RequestParam(required = true) String qquuid,
                                               @RequestParam(required = true) String qqfilename, @RequestParam(required = true) Long qqtotalfilesize,
                                               @RequestPart(value = "qqfile", required = true) MultipartFile qqfile, @RequestParam(required = false, defaultValue = "0") Integer qqpartindex,
                                               @RequestParam(required = false, defaultValue = "0") Integer qqpartbyteoffset,
                                               @RequestParam(required = false, defaultValue = "0") Long qqchunksize,
                                               @RequestParam(required = false, defaultValue = "1") Integer qqtotalparts,
                                               @PathVariable("subSystem") SubSystemEnum subSystem, @PathVariable("entity") String entity) throws Exception {
        FineUploaderChunkModel fineUploaderChunkModel = new FineUploaderChunkModel();
        fineUploaderChunkModel.setQquuid(qquuid);
        fineUploaderChunkModel.setQqfilename(qqfilename);
        fineUploaderChunkModel.setQqtotalfilesize(qqtotalfilesize);
        fineUploaderChunkModel.setQqfile(qqfile);
        fineUploaderChunkModel.setQqpartindex(qqpartindex);
        fineUploaderChunkModel.setQqpartbyteoffset(qqpartbyteoffset);
        fineUploaderChunkModel.setQqchunksize(qqchunksize);
        fineUploaderChunkModel.setQqtotalparts(qqtotalparts);
        fineUploaderChunkModel.setSubSystem(subSystem);
        fineUploaderChunkModel.setEntity(entity);
        //ارسال اطلاعات قسمتی از اپلود برای سرویس آپلود فایل
        logUploadedFileService.uploadToFileModel(qqfile, fineUploaderChunkModel);
        //ایجاد خروجی جهت آپلود فایل در فرانت پنل
        FineUploaderResponseModel fineUploaderResponseModel = new FineUploaderResponseModel();
        fineUploaderResponseModel.setSuccess(Boolean.TRUE);
        return fineUploaderResponseModel;
    }

}
