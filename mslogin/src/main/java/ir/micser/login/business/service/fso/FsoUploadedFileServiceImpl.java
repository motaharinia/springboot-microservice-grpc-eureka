package ir.micser.login.business.service.fso;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msjpautility.entity.EntityTools;
import com.motaharinia.msutility.fso.FsoTools;
import com.motaharinia.msutility.fso.upload.FileUploadedModel;
import com.motaharinia.msutility.fso.view.FileViewModel;
import com.motaharinia.msutility.fso.view.FileViewModelStatusEnum;
import com.motaharinia.msutility.image.ImageTools;
import ir.micser.login.business.service.BusinessExceptionEnum;
import ir.micser.login.persistence.orm.fso.FsoUploadedFile;
import ir.micser.login.persistence.orm.fso.FsoUploadedFileRepository;
import ir.micser.login.presentation.fso.fsouploadedhandle.FsoUploadedFileHandleDetailModel;
import ir.micser.login.presentation.fso.fsouploadedhandle.FsoUploadedFileHandleModel;
import ir.micser.login.presentation.fso.FsoUploadedFileModel;
import ir.micser.login.presentation.fso.backuploader.FileUploadChunkModel;
import ir.micser.login.presentation.fso.frontuploader.FineUploaderChunkModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-16<br>
 * Time: 23:09:56<br>
 * Description:<br>
 * کلاس پیاده سازی سرویس فایلهای آپلود شده<br>
 */

@Component
@Qualifier("LogUploadedFileServiceImpl")
@Service
@Transactional(rollbackFor = Exception.class)
public class FsoUploadedFileServiceImpl implements FsoUploadedFileService {

    /**
     * مسیر موقت جهت آپلود فایلهای پروزه
     */
    @Value("${fso.path.upload.directory}")
    private String FSO_PATH_UPLOAD_DIRECTORY = "/mbazardata/uploadedfile";

    /**
     * ریپازیتوری ادمین
     */
    private FsoUploadedFileRepository fsoUploadedFileRepository;

    @Autowired
    private FsoService fsoService;


    /**
     * متد سازنده
     */
    @Autowired
    public FsoUploadedFileServiceImpl(FsoUploadedFileRepository fsoUploadedFileRepository) {
        this.fsoUploadedFileRepository = fsoUploadedFileRepository;
    }

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به بک پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile        آرایه داده ارسالی از کلاینت
     * @param fileUploadChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    @Override
    public FsoUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FileUploadChunkModel fileUploadChunkModel) throws Exception {
        //تعریف و ساخت پوشه ای برای فایلهای در حال آپلود"uploading" در پوشه آپلود
        String uploadingDirectory = FSO_PATH_UPLOAD_DIRECTORY + "/" + "uploading";
        FsoTools.createDirectoryIfNotExist(uploadingDirectory);

        //مسیر فایل در حال آپلود طبق کلید فایل که از سمت کلاینت می آبد
        String uploadingFilePath = uploadingDirectory + "/" + fileUploadChunkModel.getFileKey();
        File uploadingFile = new File(uploadingFilePath);
        FileUtils.writeByteArrayToFile(uploadingFile, multipartFile.getBytes(), true);

        //ایجاد مدل فایل آپلود
        FsoUploadedFileModel fsoUploadedFileModel = new FsoUploadedFileModel();
        if (fileUploadChunkModel.getChunks() - 1 == fileUploadChunkModel.getChunk()) {
            fsoUploadedFileModel.setFileKey(fileUploadChunkModel.getFileKey());
            fsoUploadedFileModel.setFileByteArray(FileUtils.readFileToByteArray(uploadingFile));
            fsoUploadedFileModel.setFileSize(Long.valueOf(fsoUploadedFileModel.getFileByteArray().length));
            fsoUploadedFileModel.setFileUploadDateTime(new Date());
            fileUploadChunkModel.setName(this.fixFailedFileNameCharacter(fileUploadChunkModel.getName()));
            fsoUploadedFileModel.setFileFullName(fileUploadChunkModel.getName());
            fsoUploadedFileModel.setFileEntity(fileUploadChunkModel.getEntity());
            fsoUploadedFileModel.setFileSubSystem(fileUploadChunkModel.getSubSystem().getValue());
            fsoUploadedFileModel.setFileExtension(FsoTools.getFileExtension(fileUploadChunkModel.getName()));
            fsoUploadedFileModel.setFileName(FsoTools.getFileNameWithoutExtension(fileUploadChunkModel.getName()));
            String mainPath = "/" + removeNonAlphabetic(fileUploadChunkModel.getSubSystem().getValue()) + "/" + removeNonAlphabetic(fileUploadChunkModel.getEntity());
            fsoUploadedFileModel.setDirectoryRealPath(checkLastCharOfPath(mainPath + fileUploadChunkModel.getFilePath()));

            //ذخیره فایل بر روی فایل سیستم از روی مدل دیتابیسی فایل آپلود شده
            fsoUploadedFileModel = this.saveUploadedFile(fsoUploadedFileModel, fsoUploadedFileModel.getFileKey());
            //ذخیره اطلاعات در دیتابیس از روی مدل دیتابیسی فایل آپلود شده
            this.create(fsoUploadedFileModel);

            //حذف فایل موقت در حال آپلود
            uploadingFile.delete();

            return fsoUploadedFileModel;
        } else {
            return null;
        }
    }

    /**
     * این متد قسمتی از اطلاعات آپلود مربوط به فرانت پنل کلاینت ساید را از ورودی دریافت میکند و فایل و اطلاعات دیتابیسی آن را ذخیره مینماید
     *
     * @param multipartFile          آرایه داده ارسالی از کلاینت
     * @param fineUploaderChunkModel مدل داده ارسالی از کلاینت
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    @Override
    public FsoUploadedFileModel uploadToFileModel(MultipartFile multipartFile, FineUploaderChunkModel fineUploaderChunkModel) throws Exception {
        //تعریف و ساخت پوشه ای برای فایلهای در حال آپلود"uploading" در پوشه آپلود
        String uploadingDirectory = FSO_PATH_UPLOAD_DIRECTORY + "/" + "uploading";
        FsoTools.createDirectoryIfNotExist(uploadingDirectory);

        //مسیر فایل در حال آپلود طبق کلید فایل که از سمت کلاینت می آبد
        String uploadingFilePath = uploadingDirectory + "/" + fineUploaderChunkModel.getFileKey();
        File uploadingFile = new File(uploadingFilePath);
        FileUtils.writeByteArrayToFile(uploadingFile, multipartFile.getBytes(), true);

        //ایجاد مدل فایل آپلود
        FsoUploadedFileModel fsoUploadedFileModel = new FsoUploadedFileModel();
        if (fineUploaderChunkModel.getChunks() - 1 == fineUploaderChunkModel.getChunk()) {
            fsoUploadedFileModel.setFileKey(fineUploaderChunkModel.getFileKey());
            fsoUploadedFileModel.setFileByteArray(FileUtils.readFileToByteArray(uploadingFile));
            fsoUploadedFileModel.setFileSize(Long.valueOf(fsoUploadedFileModel.getFileByteArray().length));
            fsoUploadedFileModel.setFileUploadDateTime(new Date());
            fineUploaderChunkModel.setName(this.fixFailedFileNameCharacter(fineUploaderChunkModel.getName()));
            fsoUploadedFileModel.setFileFullName(fineUploaderChunkModel.getName());
            fsoUploadedFileModel.setFileEntity(fineUploaderChunkModel.getEntity());
            fsoUploadedFileModel.setFileSubSystem(fineUploaderChunkModel.getSubSystem().getValue());
            fsoUploadedFileModel.setFileExtension(FsoTools.getFileExtension(fineUploaderChunkModel.getName()));
            fsoUploadedFileModel.setFileName(FsoTools.getFileNameWithoutExtension(fineUploaderChunkModel.getName()));
            String mainPath = "/" + removeNonAlphabetic(fineUploaderChunkModel.getSubSystem().getValue()) + "/" + removeNonAlphabetic(fineUploaderChunkModel.getEntity());
//            logUploadedFileModel.setDirectoryRealPath(checkLastCharOfPath(mainPath + fineUploaderChunkModel.getFilePath()));

            //ذخیره فایل بر روی فایل سیستم از روی مدل دیتابیسی فایل آپلود شده
            fsoUploadedFileModel = this.saveUploadedFile(fsoUploadedFileModel, fsoUploadedFileModel.getFileKey());
            //ذخیره اطلاعات در دیتابیس از روی مدل دیتابیسی فایل آپلود شده
            this.create(fsoUploadedFileModel);

            return fsoUploadedFileModel;
        } else {
            return null;
        }
    }

    @Override
    public FsoUploadedFileModel create(FsoUploadedFileModel fsoUploadedFileModel) throws Exception {
        //ساخت انتیتی فایل آپلود شده از مدل فایل آپلود شده
        FsoUploadedFile fsoUploadedFile = new FsoUploadedFile();
        fsoUploadedFile.setFileExtension(fsoUploadedFileModel.getFileExtension());
        fsoUploadedFile.setFileFullName(fsoUploadedFileModel.getFileFullName());
        fsoUploadedFile.setFileKey(fsoUploadedFileModel.getFileKey());
        fsoUploadedFile.setFileMimeType(fsoUploadedFileModel.getFileMimeType());
        fsoUploadedFile.setFileName(fsoUploadedFileModel.getFileName());
        fsoUploadedFile.setFileSize(fsoUploadedFileModel.getFileSize());
        fsoUploadedFile.setFileUploadDateTime(new Date());
        fsoUploadedFile.setFileUploadedPath(fsoUploadedFileModel.getFileUploadedPath());
        fsoUploadedFile.setFileEntity(fsoUploadedFileModel.getFileEntity());
        fsoUploadedFile.setFileSubSystem(fsoUploadedFileModel.getFileSubSystem());
        fsoUploadedFileRepository.save(fsoUploadedFile);
        return fsoUploadedFileModel;
    }

    /**
     * این متد کلید فایل مورد نظر فایل آپلود شده را از ورودی دریافت کرده و مدل آن را خروجی میدهد
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @return خروجی: مدل اطلاعات فایل آپلود شده
     * @throws Exception
     */
    @Override
    public FsoUploadedFileModel readByFileKey(String fileKey) throws Exception {
        if ((fileKey == null) || (fileKey.length() == 0)) {
            throw new BusinessException(FsoUploadedFileServiceImpl.class, BusinessExceptionEnum.INVALID_FILE_KEY, "fileKey:" + fileKey);
        }

        FsoUploadedFileModel fsoUploadedFileModel = new FsoUploadedFileModel();
        FsoUploadedFile fsoUploadedFile = fsoUploadedFileRepository.findByFileKey(fileKey);

        if (fsoUploadedFile != null) {
            fsoUploadedFileModel = (FsoUploadedFileModel) EntityTools.convertToModel(fsoUploadedFileModel, fsoUploadedFile);
            //خواندن اطلاعات فایل در مدل
            File uploadedFile = new File(fsoUploadedFileModel.getFileUploadedPath());
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(uploadedFile);
            byte fileContent[] = new byte[(int) uploadedFile.length()];
            fileInputStream.read(fileContent);
            fsoUploadedFileModel.setFileByteArray(fileContent);
        } else {
            throw new BusinessException(FsoUploadedFileServiceImpl.class, BusinessExceptionEnum.INVALID_FILE_KEY, "fileKey:" + fileKey);
        }

        return fsoUploadedFileModel;
    }

    /**
     * این متد کلید فایل آپلود شده مورد نظر را از ورودی دریافت کرده و آن را حذف مینماید
     *
     * @param fileKey کلید فایل آپلود شده مورد نظر
     * @throws Exception
     */
    @Override
    public void delete(String fileKey) throws Exception {
        FsoUploadedFile fsoUploadedFile = fsoUploadedFileRepository.findByFileKey(fileKey);
        if (fsoUploadedFile != null) {
            fsoUploadedFileRepository.delete(fsoUploadedFile);
            File file = new File(FSO_PATH_UPLOAD_DIRECTORY + "/" + fsoUploadedFile.getFileKey());
            if ((file.exists())) {
                FileUtils.deleteQuietly(file);
            }
        } else {
            throw new BusinessException(FsoUploadedFileServiceImpl.class, BusinessExceptionEnum.INVALID_FILE_KEY, "fileKey:" + fileKey);
        }
    }


    /**
     * این متد مطابق مدل دیتابیسی اطلاعات فایل آپلود شده آن را در فایل سیستم ذخیره مینماید
     *
     * @param fsoUploadedFileModel مدل فایل آپلود شده
     * @param fileKey              کلید فایل آپلود شده
     * @return خروجی: مدل فایل آپلود شده کامل شده
     * @throws Exception
     */
    private FsoUploadedFileModel saveUploadedFile(FsoUploadedFileModel fsoUploadedFileModel, String fileKey) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String fileName = "uploaded_" + sdf.format(cal.getTime()) + "_" + fsoUploadedFileModel.getFileFullName();
        String fileKeyDirectoryPath = FSO_PATH_UPLOAD_DIRECTORY + "/" + fileKey;
        File directory = new File(fileKeyDirectoryPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
        FileUtils.writeByteArrayToFile(new File(fileKeyDirectoryPath + "/" + fileName), fsoUploadedFileModel.getFileByteArray());
        fsoUploadedFileModel.setFileMimeType(FsoTools.getMimeTypeModel(fileKeyDirectoryPath + "/" + fileName).getMimeType());
        fsoUploadedFileModel.setFileUploadedPath(fileKeyDirectoryPath + "/" + fileName);
        return fsoUploadedFileModel;
    }


    /**
     * این متد کارکترهای غیر الفبایی را از رشته ورودی حذف میکند
     *
     * @param inputString رشته ورودی
     * @return خروجی: رشته بررسی شده و اصلاح شده
     */
    private static String removeNonAlphabetic(String inputString) {
        inputString = inputString.replaceAll("[^a-zA-Z]", "");
        return inputString;
    }

    /**
     * این متد انتهای مسیر دایرکتوری را چک میکند اگر اسلش ندارد به آن اضافه میکند
     *
     * @param directoryPath مسیر دایرکتوری
     * @return خروجی: مسیر دایرکتوری که حتما در انتهای آن اسلش دارد
     */
    private static String checkLastCharOfPath(String directoryPath) {
        String lastChar = directoryPath.substring(directoryPath.length() - 1);
        if (!"/".equals(lastChar)) {
            directoryPath = directoryPath + "/";
        }
        return directoryPath;
    }

    /**
     * این متد کارکترهای عربی را با معادل فارسی آن جایگزین میکند
     *
     * @param fileName نام فایل ورودی
     * @return خروجی: نام فایل اصلاح شده و بدون کارکترهای عربی
     */
    private static String fixFailedFileNameCharacter(String fileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            return "";
        }
        HashMap<String, String> replaceHashMap = new HashMap<>();
        replaceHashMap.put(Character.toString((char) 63), "ي");
        replaceHashMap.put(Character.toString((char) 1740), "ي");
        replaceHashMap.put(Character.toString((char) 1705), "ك");
        replaceHashMap.put(Character.toString((char) 1607), "ھ");
        replaceHashMap.put(Character.toString((char) 1575), "أ");
        replaceHashMap.put(Character.toString((char) 1570), "أ");
        replaceHashMap.put(Character.toString((char) 1608), "ؤ");
        for (Map.Entry<String, String> entry : replaceHashMap.entrySet()) {
            while (fileName.contains(entry.getKey())) {
                fileName = fileName.replace(entry.getKey(), entry.getValue());
            }
        }
        return fileName;
    }


    /**
     * این متد مدل یک فایل آپلود شده را میگیرد و فایل را نسبت به فعل ثبت یا ویرایش یا حذف در فایل سیستم سامانه مدیریت میکند
     *
     * @param fsoUploadedFileHandleModel مدل فایل آپلود شده
     * @throws Exception خطا
     */
    @Override
    public void logUploadedFileHandle(FsoUploadedFileHandleModel fsoUploadedFileHandleModel) throws Exception {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        Calendar cal = Calendar.getInstance();
        String dateTime = sdf.format(cal.getTime());

//        if (CollectionUtils.isEmpty(logUploadedFileHandleModel.getLogUploadedFileHandleFsoModelList())) {
//            throw new BusinessException(this.getEntityClass(), CommonBusinessExceptionKeyImpl.INVALID_LOG_UPLOADED_FSO_MODEL_LIST_ENUM);
//        }
        if (fsoUploadedFileHandleModel.getEntityId() == null) {
            throw new BusinessException(FsoUploadedFileServiceImpl.class, BusinessExceptionEnum.LOG_UPLOADED_FILE_HANDLE_ENTITY_ID_INVALID, "logUploadedFileHandleModel.getEntityId() ");
        }
        switch (fsoUploadedFileHandleModel.getFsoUploadedFileHandleActionEnum()) {
            case ENTITY_CREATE:
                //در صورتی که فایلی برای اضافه شدن در ثبت وجود دارد پوشه انتیتی آن را حذف میکنیم
                for (FileViewModel fileViewModel : fsoUploadedFileHandleModel.getFileViewModelList()) {
                    if (fileViewModel.getStatusEnum().equals(FileViewModelStatusEnum.ADDED)) {
                        for (FsoUploadedFileHandleDetailModel fsoUploadedFileHandleDetailModel : fsoUploadedFileHandleModel.getFsoUploadedFileHandleDetailModelList()) {
                            //به دست آوردن مسیر پوشه نوع فایل مورد نظر در انتیتی مورد نظر
                            ///common/socialgroup/120/logo/
                            String entityDirectoryPath = fsoUploadedFileHandleDetailModel.getFsoModuleEnum().getEntityDirectoryPath();
                            //چون در حالت ثبت ماژول هستیم پس اگر از قبل دایرکتوری در مسیر پوشه نوع فایل انتیتی ثبت شده است را کلا حذف میکنیم
                            List<String> deleteDirectoryList = new ArrayList<>();
                            deleteDirectoryList.add(entityDirectoryPath + fsoUploadedFileHandleModel.getEntityId().toString());
                            fsoService.delete(deleteDirectoryList);
                        }
                    }
                }
                //آپلود فایلهای جدید اضافه شده
                for (FileViewModel fileViewModel : fsoUploadedFileHandleModel.getFileViewModelList()) {
                    if (fileViewModel.getStatusEnum().equals(FileViewModelStatusEnum.ADDED)) {
                        FsoUploadedFileModel fsoUploadedFileModel = this.readByFileKey(fileViewModel.getKey());
                        for (FsoUploadedFileHandleDetailModel fsoUploadedFileHandleDetailModel : fsoUploadedFileHandleModel.getFsoUploadedFileHandleDetailModelList()) {
                            //به دست آوردن مسیر پوشه نوع فایل مورد نظر در انتیتی مورد نظر
                            ///common/socialgroup/120/logo/
                            String directoryPath = fsoUploadedFileHandleDetailModel.getFsoModuleEnum().getValue().replace("%ENTITYID%", fsoUploadedFileHandleModel.getEntityId().toString());
                            //بررسی و در صورت نیاز ساخت مسیر پوشه نوع فایل مورد نظر انتیتی
                            FsoTools.pathDirectoryPrepare(directoryPath);
                           //تبدیل مدل فایل آپلود به مدل آپلود جهت آپلود
                            FileUploadedModel fileUploadedModel= fsoUploadedFileModel.getFileUploadedModel();
                            fileUploadedModel.setDirectoryRealPath(directoryPath);
                            //بررسی نیاز به تغییر اندازه داشتن فایلهای تصویری
                            if ((fsoUploadedFileHandleDetailModel.getHeight() != null) && (fsoUploadedFileHandleDetailModel.getWidth() != null) && (fsoUploadedFileHandleDetailModel.getHeight() > 0) && (fsoUploadedFileHandleDetailModel.getWidth() > 0)) {
                                //اگر فایل مورد نظر تصویر باشد و در ورودی خواسته شده باشد که آن فایل تغییر اندازه بشود
                                byte[] resizedFileByteArray = ImageTools.imageResize(fileUploadedModel.getDataByteArray(), fileUploadedModel.getExtension(), fsoUploadedFileHandleDetailModel.getWidth(), fsoUploadedFileHandleDetailModel.getHeight(), true);
                                fileUploadedModel.setDataByteArray(resizedFileByteArray);
                                fileUploadedModel.setSize(Long.valueOf(fileUploadedModel.getDataByteArray().length));
                            } else {
                                //اگر فایل مورد نظر تصویر نباشد و یا نیاز به تغییر اندازه نداشته باشد
                                fileUploadedModel.setName(dateTime + "_" + fileUploadedModel.getName());
                                fileUploadedModel.setFullName(dateTime + "_" + fileUploadedModel.getFullName());
                            }
                            fsoService.upload(fileUploadedModel);
                        }
                        this.delete(fileViewModel.getKey());
                    }
                }
                break;
            case ENTITY_UPDATE:
                //حذف فایلهای حذف شده در کلاینت
                for (FileViewModel fileViewModel : fsoUploadedFileHandleModel.getFileViewModelList()) {
                    //اگر فایلی در کلاینت اضافه شده است
                    if (fileViewModel.getStatusEnum().equals(FileViewModelStatusEnum.ADDED)) {
                        FsoUploadedFileModel fsoUploadedFileModel = this.readByFileKey(fileViewModel.getKey());
                        for (FsoUploadedFileHandleDetailModel fsoUploadedFileHandleDetailModel : fsoUploadedFileHandleModel.getFsoUploadedFileHandleDetailModelList()) {
                            //به دست آوردن مسیر پوشه نوع فایل مورد نظر در انتیتی مورد نظر
                            ///common/socialgroup/120/logo/
                            String directoryPath = fsoUploadedFileHandleDetailModel.getFsoModuleEnum().getValue().replace("%ENTITYID%", fsoUploadedFileHandleModel.getEntityId().toString());
                            //اگر فایلی برای آپلود ارسال شده است و آن فایل سینگل میباشد پوشه اش را حذف میکنیم
                            if (fsoUploadedFileHandleDetailModel.getIsSingle()) {
                                List<String> deleteDirectoryList = new ArrayList<>();
                                deleteDirectoryList.add(directoryPath);
                                fsoService.delete(deleteDirectoryList);
                            }
                            //بررسی و در صورت نیاز ساخت مسیر پوشه نوع فایل مورد نظر انتیتی
                            FsoTools.pathDirectoryPrepare(directoryPath);
                            //تبدیل مدل فایل آپلود به مدل آپلود جهت آپلود
                            FileUploadedModel fileUploadedModel= fsoUploadedFileModel.getFileUploadedModel();
                            fileUploadedModel.setDirectoryRealPath(directoryPath);

                            //بررسی نیاز به تغییر اندازه داشتن فایلهای تصویری
                            if ((fsoUploadedFileHandleDetailModel.getHeight() != null) && (fsoUploadedFileHandleDetailModel.getWidth() != null) && (fsoUploadedFileHandleDetailModel.getHeight() > 0) && (fsoUploadedFileHandleDetailModel.getWidth() > 0)) {
                                //اگر فایل مورد نظر تصویر باشد و در ورودی خواسته شده باشد که آن فایل تغییر اندازه بشود
                                //logUploadedFileModel.setFileName(dateTime + "_" + logUploadedFileModel.getFileName());
                                //logUploadedFileModel.setFileFullName(dateTime + "_" + logUploadedFileModel.getFileFullName());
                                byte[] resizedFileByteArray = ImageTools.imageResize(fileUploadedModel.getDataByteArray(), fileUploadedModel.getExtension(), fsoUploadedFileHandleDetailModel.getWidth(), fsoUploadedFileHandleDetailModel.getHeight(), true);
                                fileUploadedModel.setDataByteArray(resizedFileByteArray);
                                fileUploadedModel.setSize(Long.valueOf(fileUploadedModel.getDataByteArray().length));
                            } else {
                                //اگر فایل مورد نظر تصویر نباشد و یا نیاز به تغییر اندازه نداشته باشد
                                fsoUploadedFileModel.setFileName(dateTime + "_" + fsoUploadedFileModel.getFileName());
                                fsoUploadedFileModel.setFileFullName(dateTime + "_" + fsoUploadedFileModel.getFileFullName());
                            }
                            fsoService.upload(fileUploadedModel);
                        }
                        this.delete(fileViewModel.getKey());
                    }
                    //اگر فایلی در کلاینت حذف شده است
                    if (fileViewModel.getStatusEnum().equals(FileViewModelStatusEnum.DELETED)) {
                        //حذف فایل های حذف شده در کلاینت
                        for (FsoUploadedFileHandleDetailModel fsoUploadedFileHandleDetailModel : fsoUploadedFileHandleModel.getFsoUploadedFileHandleDetailModelList()) {

                            String fileForDeletePath = fsoUploadedFileHandleDetailModel.getFsoModuleEnum().getValue().replace("%ENTITYID%", fsoUploadedFileHandleModel.getEntityId().toString()) + fileViewModel.getFullName();

                            List<String> fileForDeletePathList = new ArrayList<>();
                            fileForDeletePathList.add(fileForDeletePath);
                            fsoService.delete(fileForDeletePathList);
                        }

                    }
                }
                break;
            case ENTITY_DELETE:
                List<String> deleteDirectoryList = new ArrayList<>();
                String entityDirectoryPath = "";
                for (FsoUploadedFileHandleDetailModel fsoUploadedFileHandleDetailModel : fsoUploadedFileHandleModel.getFsoUploadedFileHandleDetailModelList()) {
                    Integer entityIdIndex = fsoUploadedFileHandleDetailModel.getFsoModuleEnum().getValue().indexOf("%ENTITYID%");
                    entityDirectoryPath = fsoUploadedFileHandleDetailModel.getFsoModuleEnum().getValue().substring(0, entityIdIndex);
                    deleteDirectoryList.add(entityDirectoryPath + fsoUploadedFileHandleModel.getEntityId());
                    break;
                }
                fsoService.delete(deleteDirectoryList);
                break;
            default:
                throw new BusinessException(FsoUploadedFileServiceImpl.class, BusinessExceptionEnum.LOG_UPLOADED_FILE_HANDLE_ACTION_ENUM_INVALID, "logUploadedFileHandleModel.getLogUploadedFileHandleActionEnum():" + fsoUploadedFileHandleModel.getFsoUploadedFileHandleActionEnum());
        }
    }

}
