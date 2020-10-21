package com.motaharinia.msutility.fso;


import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import com.motaharinia.msutility.encoding.EncodingTools;
import com.motaharinia.msutility.fso.check.FsoPathCheckModel;
import com.motaharinia.msutility.fso.check.FsoPathCheckTypeEnum;
import com.motaharinia.msutility.fso.mimetype.FsoMimeTypeEnum;
import com.motaharinia.msutility.fso.mimetype.FsoMimeTypeModel;
import com.motaharinia.msutility.fso.content.*;
import com.motaharinia.msutility.image.ImageTools;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-11<br>
 * Time: 02:33:39<br>
 * Description:<br>
 * اینترفیس متد های ابزاری فایل سیستم
 */
public interface FsoTools {

    /**
     * این متد یک مسیر کامل فایل یا دایرکتوری ورودی دریافت میکند و چک میکند آن مسیر وجود داشته باشد و مدل حاوی نوع مسیر (فایل یا دایرکتوری) و مرجع فایل را خروجی میدهد
     *
     * @param path مسیر کامل فایل یا دایرکتوری ورودی
     * @return خروجی: مدل حاوی نوع مسیر (فایل یا دایرکتوری) و مرجع فایل
     */
    @NotNull
    static FsoPathCheckModel pathExistCheck(@NotNull String path) {
        if (ObjectUtils.isEmpty(path)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "path");
        }
        FsoPathCheckModel fsoPathCheckModel = new FsoPathCheckModel();
        File file = new File(path);
        fsoPathCheckModel.setFile(file);
        if (file.exists()) {
            if (!file.isDirectory()) {
                fsoPathCheckModel.setTypeEnum(FsoPathCheckTypeEnum.FILE);
            } else {
                fsoPathCheckModel.setTypeEnum(FsoPathCheckTypeEnum.DIRECTORY);
            }
        } else {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_PATH_IS_NOT_EXISTED, "path:" + path);
        }
        return fsoPathCheckModel;
    }

    /**
     * این متد یک مسیر و نوع آن و یک سوال که در صورت وجود مسیر در مقصد یک نام جدید با -copy بسازد را از ورودی دریافت میکند و در صورت تغییر مسیر آن را خروجی میدهد
     *
     * @param path                 مسیر
     * @param fsoPathCheckTypeEnum نوع مسیر دایرکتوری یا فایل
     * @param withRenameOnExist    در صورت وجود مسیر در مقصد یک نام جدید با -copy بسازد
     * @return خروجی: مسیر نهایی
     */
    @NotNull
    static String pathCreateCheck(@NotNull String path, @NotNull FsoPathCheckTypeEnum fsoPathCheckTypeEnum, @NotNull Boolean withRenameOnExist) {
        if (ObjectUtils.isEmpty(path)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "path");
        }
        if (ObjectUtils.isEmpty(fsoPathCheckTypeEnum)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fsoPathCheckTypeEnum");
        }
        if (ObjectUtils.isEmpty(withRenameOnExist)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withRenameOnExist");
        }
        File fileTo = new File(path);
        String[] pathDirectoryArray = path.split("/");
        if (fileTo.exists()) {
            if (withRenameOnExist) {
                if (fsoPathCheckTypeEnum.equals(FsoPathCheckTypeEnum.DIRECTORY)) {
                    path = FsoTools.recursiveCreateName(path, 0);
                } else {
                    String pathParentDirectory = Arrays.stream(Arrays.copyOf(pathDirectoryArray, pathDirectoryArray.length - 1)).collect(Collectors.joining("/"));
                    String filefullName = pathDirectoryArray[pathDirectoryArray.length - 1];
                    path = FsoTools.recursiveCreateName(pathParentDirectory, getFileNameWithoutExtension(filefullName), getFileExtension(filefullName), 0);
                }
            } else {
                throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_DESTINATION_PATH_EXISTED, "path:" + path);
            }
        }
        return path;
    }

    /**
     * این متد یک مسیر و فیلترهای فایل و دایرکتوری محتویات آن را از ورودی دریافت میکند و طبق فیلترهای ورودی اطلاعات محتویات فایل و دایرکتوری های زیر مجموعه مسیر را در قالب مدل خروجی میدهد
     *
     * @param directoryPath        مسیر ورودی
     * @param acceptNameArray      آرایه نام فایلهای مجاز برای فیلتر
     * @param acceptExtensionArray آرایه پسوند فایلهای مجاز برای فیلتر
     * @param denyNameArray        آرایه نام فایلهای غیر مجاز برای فیلتر
     * @param denyExtensionArray   آرایه پسوند فایلهای غیر مجاز برای فیلتر
     * @param showHidden           مجاز بودن فایل و دایرکتوری های مخفی در فیلتر
     * @return خروجی: مدل حاوی اطلاعات فایلها و دایرکتوری های داخل یک مسیر
     * @throws IOException این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static FsoPathContentModel pathContent(@NotNull String directoryPath, @NotNull String[] acceptNameArray, @NotNull String[] acceptExtensionArray, @NotNull String[] denyNameArray, @NotNull String[] denyExtensionArray, @NotNull Boolean showHidden) throws IOException {
        if (ObjectUtils.isEmpty(directoryPath)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "directoryPath");
        }
        if (ObjectUtils.isEmpty(showHidden)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "showHidden");
        }
        FsoPathCheckModel fsoPathCheckModel = pathExistCheck(directoryPath);
        File directory = fsoPathCheckModel.getFile();
        if (!fsoPathCheckModel.getTypeEnum().equals(FsoPathCheckTypeEnum.DIRECTORY)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_PATH_IS_NOT_DIRECTORY, "directoryPath:" + directoryPath);
        }

        long directorySize = 0l;

        //فرمت دهنده برای تبدیل تاریخ به رشته تاریخ
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //ایجاد مدل خروجی
        FsoPathContentModel fsoPathContentModel = new FsoPathContentModel();
        //ایجاد یک فایل لیست برای محدود سازی اطلاعات مورد نظر
        FsoFileListFilter fsoFileListFilter = new FsoFileListFilter(acceptNameArray, acceptExtensionArray, denyNameArray, denyExtensionArray, showHidden);
        //دریافت لیست اطلاعات فایلها و دایرکتوری های داخل مسیر
        File[] fileArray = directory.listFiles(fsoFileListFilter);
        if (!ObjectUtils.isEmpty(fileArray)) {
            //مرتب سازی فایلهای مسیر
            Arrays.sort(fileArray);
            //حلقه روی محتویات مسیر
            for (File subFile : fileArray) {
                if (subFile.isDirectory()) {
                    //اگر دایرکتوری است
                    directorySize = pathGetFileListRecursive(directoryPath + "/" + subFile.getName(), fsoFileListFilter, new ArrayList<>()).stream()
                            .mapToLong(file -> file.length())
                            .sum();
                    fsoPathContentModel.getDirectoryModelList().add(new FsoPathContentDirectoryModel(subFile.getName(), directoryPath, directoryPath + "/" + subFile.getName(), new Date(subFile.lastModified()), String.valueOf(sdf.format(subFile.lastModified())), directorySize));
                } else {
                    //اگر فایل است
                    fsoPathContentModel.getFileModelList().add(new FsoPathContentFileModel(getFileNameWithoutExtension(subFile.getName()), getFileExtension(subFile.getName()), subFile.getName(), directoryPath, directoryPath + "/" + subFile.getName(), new Date(subFile.lastModified()), String.valueOf(sdf.format(subFile.lastModified())), subFile.length(), getMimeTypeModel(directoryPath + "/" + subFile.getName()).getMimeType()));
                }
            }
        }
        return fsoPathContentModel;
    }

    /**
     * این متد یک مسیر ورودی و فیلتر جستجو و یک لیست خالی مرجع فایل دریافت میکند و به صورت بازگشتی داخل دایرکتوری های مسیر ورودی جستجو میکند و لیست مرجع فایلهای داخل آن را خروجی میدهد
     *
     * @param directoryPath     مسیر ورودی
     * @param fileFullPathList  لیست مرجع فایل
     * @param fsoFileListFilter فیلتر جستجو
     * @return خروجی: لیست مراجع فایلهای داخل مسیر ورودی
     */
    @NotNull
    static List<File> pathGetFileListRecursive(@NotNull String directoryPath, @NotNull FsoFileListFilter fsoFileListFilter, @NotNull List<File> fileFullPathList) {
        if (ObjectUtils.isEmpty(directoryPath)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "directoryPath");
        }
        if (ObjectUtils.isEmpty(fsoFileListFilter)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fsoFileListFilter");
        }
        FsoPathCheckModel fsoPathCheckModel = pathExistCheck(directoryPath);
        File file = fsoPathCheckModel.getFile();
        File[] fileArray = file.listFiles(fsoFileListFilter);
        //حلقه روی محتویات مسیر
        for (File subFile : fileArray) {
            if (subFile.isDirectory()) {
                //اگر دایرکتوری است
                fileFullPathList = pathGetFileListRecursive(directoryPath + "/" + subFile.getName(), fsoFileListFilter, fileFullPathList);
            } else {
                //اگر فایل است
                fileFullPathList.add(subFile);
            }
        }
        return fileFullPathList;
    }

    /**
     * این متد یک مسیر را از ورودی دریافت میکند و اگر وجود نداشته باشد آن را ایجاد میکند
     *
     * @param directoryPath مسیر ورودی
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    static void createDirectoryIfNotExist(@NotNull String directoryPath) throws Exception {
        if (ObjectUtils.isEmpty(directoryPath)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "directoryPath");
        }
        File file = new File(directoryPath);
        if (!file.exists()) {
            FileUtils.forceMkdir(file);
        }
    }

    /**
     * این متد یک رشته مسیر دایرکتوری ورودی دریافت میکند و سعی میکند اگر آن مسیر دایرکتوری وجود ندارد آن را کامل ایجاد کند
     *
     * @param directoryPath رشته مسیر دایرکتوری ورودی
     */
    static void pathDirectoryPrepare(@NotNull String directoryPath) {
        if (ObjectUtils.isEmpty(directoryPath)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "directoryPath");
        }
        String[] directoryPathArray = directoryPath.split("/");
        String directoryPathCheck = "";
        File directory;
        for (String subPath : directoryPathArray) {
            if (!ObjectUtils.isEmpty(subPath)) {
                directoryPathCheck += "/" + subPath;
                directory = new File(directoryPathCheck);
                if (!directory.exists()) {
                    directory.mkdir();
                }
            }
        }
    }

    /**
     * این متد یک مسیر ورودی و یک سوال که آیا مسیر ورودی تصویر بندانگشتی دارد یا خیر را از ورودی دریافت میکند و در صورت وجود مسیر ورودی، آن را حذف مینماید
     *
     * @param path           مسیر ورودی
     * @param withThumbnail  مسیر مبدا حاوی تصویر بندانگشتی
     * @param fsoConfigModel مدل تنظیمات ابزار فایل
     */
    static void delete(@NotNull String path, @NotNull Boolean withThumbnail, FsoConfigModel fsoConfigModel) {
        if (ObjectUtils.isEmpty(path)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "path");
        }
        if (ObjectUtils.isEmpty(withThumbnail)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withThumbnail");
        }
        String thumbPath;
        File file = new File(path);
        if ((file.exists())) {
            FileUtils.deleteQuietly(file);
            if (withThumbnail) {
                for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                    thumbPath = path + "-" + size + "." + fsoConfigModel.getThumbExtension();
                    delete(thumbPath, false, fsoConfigModel);
                }
            }
        }
    }


    /**
     * این متد یک مسیر مبدا و یک مسیر مقصد و یک سوال که آیا مسیر مبدا تصویر بندانگشتی دارد یا خیر و و یک سوال که در صورت وجود نداشتن مسیر آن را بسازد یا خیر از ورودی دریافت میکند و مسیر مبدا را به مسیر مقصد انتقال/تغییرنام میدهد
     *
     * @param pathFrom              مسیر مبدا
     * @param pathTo                مسیر مقصد
     * @param withThumbnail         مسیر مبدا حاوی تصویر بندانگشتی
     * @param fsoConfigModel        مدل تنظیمات ابزار فایل
     * @param withDirectoryCreation در صورت عدم وجود مسیر مقصد آن را ایجاد کند؟
     */
    static void move(@NotNull String pathFrom, @NotNull String pathTo, @NotNull Boolean withThumbnail, FsoConfigModel fsoConfigModel, @NotNull Boolean withDirectoryCreation) {
        if (ObjectUtils.isEmpty(pathFrom)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "pathFrom");
        }
        if (ObjectUtils.isEmpty(pathTo)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "pathTo");
        }
        if (ObjectUtils.isEmpty(withThumbnail)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withThumbnail");
        }
        if (ObjectUtils.isEmpty(withDirectoryCreation)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withDirectoryCreation");
        }
        FsoPathCheckModel fsoPathCheckModel = pathExistCheck(pathFrom);
        File fileFrom = fsoPathCheckModel.getFile();
        String thumbPathFrom = "";
        String thumbPathTo = "";
        File fileTo = new File(pathTo);
        if (fileTo.exists()) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_DESTINATION_PATH_EXISTED, "pathTo:" + pathTo);
        } else {

            //به دست آوردن مسیر دایرکتوری والد مقصد
            String[] pathToDirectoryArray = pathTo.split("/");
            String pathToParentDirectory = Arrays.stream(Arrays.copyOf(pathToDirectoryArray, pathToDirectoryArray.length - 1)).collect(Collectors.joining("/"));

            //در صورتی که در ورودی نیاز به ایجاد مسیر کامل مقصد باشد
            if (withDirectoryCreation) {
                //در صورت عدم وجود دایرکتوری پدر آن را ایجاد میکنیم
                pathDirectoryPrepare(pathToParentDirectory);
            }

            if (fileFrom.renameTo(fileTo)) {
                if (withThumbnail) {
                    for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                        thumbPathFrom = pathFrom + "-" + size + "." + fsoConfigModel.getThumbExtension();
                        thumbPathTo = pathTo + "-" + size + "." + fsoConfigModel.getThumbExtension();
                        move(thumbPathFrom, thumbPathTo, false, fsoConfigModel, false);
                    }
                }
            }
        }
    }

    /**
     * این متد مسیر مبدا و مسیر مقصد و یک سوال که آیا مسیر ورودی تصویر بندانگشتی دارد یا خیر و یک سوال که آیا مسیر مقصد در صورت عدم وجود ایجاد شود و یک سوال که در صورت وجود مسیر در مقصد یک نام جدید با -copy بسازد را از ورودی دریافت میکند و مسیر مبدا را در مسیر مقصد کپی میکند <br>
     * اگر مسیر مقصد از قبل وجود داشته باشد مانند ویندوز نام مقصد را غیرتکراری میکند و کپی را انجام میدهد
     *
     * @param pathFrom              مسیر مبدا که میتواند دایرکتوری یا فایل باشد
     * @param pathTo                مسیر مقصد که اگر مسیر مبدا فایل بوده باید این مسیر نیز مسیر کامل فایل باشد
     * @param withThumbnail         مسیر مبدا حاوی تصویر بندانگشتی
     * @param fsoConfigModel        مدل تنظیمات ابزار فایل
     * @param withDirectoryCreation در صورت عدم وجود مسیر مقصد آن را ایجاد کند؟
     * @param withRenameOnExist     در صورت وجود مسیر در مقصد یک نام جدید با -copy بسازد
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    static void copy(@NotNull String pathFrom, @NotNull String pathTo, @NotNull Boolean withThumbnail,FsoConfigModel fsoConfigModel, @NotNull Boolean withDirectoryCreation, @NotNull Boolean withRenameOnExist) throws Exception {
        if (ObjectUtils.isEmpty(pathFrom)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "pathFrom");
        }
        if (ObjectUtils.isEmpty(pathTo)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "pathTo");
        }
        if (ObjectUtils.isEmpty(withThumbnail)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withThumbnail");
        }
        if (ObjectUtils.isEmpty(withDirectoryCreation)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withDirectoryCreation");
        }
        if (ObjectUtils.isEmpty(withRenameOnExist)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "withRenameOnExist");
        }
        FsoPathCheckModel fileFromFsoPathCheckModel = pathExistCheck(pathFrom);
        File fileFrom = fileFromFsoPathCheckModel.getFile();

        //به دست آوردن مسیر دایرکتوری والد مبدا
        String[] pathFromDirectoryArray = pathFrom.split("/");
        String pathFromParentDirectory = Arrays.stream(Arrays.copyOf(pathFromDirectoryArray, pathFromDirectoryArray.length - 1)).collect(Collectors.joining("/"));

        //به دست آوردن مسیر دایرکتوری والد مقصد
        String[] pathToDirectoryArray = pathTo.split("/");
        String pathToParentDirectory = Arrays.stream(Arrays.copyOf(pathToDirectoryArray, pathToDirectoryArray.length - 1)).collect(Collectors.joining("/"));

        //در صورتی که در ورودی نیاز به ایجاد مسیر کامل مقصد باشد
        if (withDirectoryCreation) {
            //در صورت عدم وجود دایرکتوری پدر آن را ایجاد میکنیم
            pathDirectoryPrepare(pathToParentDirectory);
        }

        //در صورت وجود فایل یا دایرکتوری با نام تکراری در مقصد در صورتی که درخواست تغییر نام از ورودی متد داشته باشیم نام جدید مانند ویندوز به آن میدهیم که روی قبلی کپی نشود ، در غیر این صورت خطا صادر میکنیم
        if (fileFromFsoPathCheckModel.getTypeEnum().equals(FsoPathCheckTypeEnum.DIRECTORY)) {
            String pathFromDirectoryName = pathFromDirectoryArray[pathFromDirectoryArray.length - 1];
            pathTo = pathCreateCheck(pathTo + "/" + pathFromDirectoryName, fileFromFsoPathCheckModel.getTypeEnum(), withRenameOnExist);
        } else {
            pathTo = pathCreateCheck(pathTo, fileFromFsoPathCheckModel.getTypeEnum(), withRenameOnExist);
        }
        File fileTo = new File(pathTo);

        //کپی اطلاعات
        if (fileFromFsoPathCheckModel.getTypeEnum().equals(FsoPathCheckTypeEnum.DIRECTORY)) {
            FileUtils.copyDirectory(fileFrom, fileTo);
        } else {
            FileUtils.copyFile(fileFrom, fileTo);
            if (withThumbnail) {
                String pathFromFileName = pathFromDirectoryArray[pathFromDirectoryArray.length - 1];
                pathToDirectoryArray = pathTo.split("/");
                String pathToFileName = pathToDirectoryArray[pathToDirectoryArray.length - 1];
                for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                    fileFrom = new File(pathFromParentDirectory + "/" + pathFromFileName + "-" + size + "." + fsoConfigModel.getThumbExtension());
                    fileTo = new File(pathToParentDirectory + "/" + pathToFileName + "-" + size + "." +  fsoConfigModel.getThumbExtension());
                    if (fileTo.exists()) {
                        delete(pathToParentDirectory + "/" + pathToFileName + "-" + size + "." +  fsoConfigModel.getThumbExtension(), false , fsoConfigModel);
                    }
                    FileUtils.copyFile(fileFrom, fileTo);
                }
            }
        }
    }

    /**
     * این متد یک نشانی وب فایل را میخواند و محتویات آن را به صورت آرایه ای از بایت خروجی میدهد
     *
     * @param url نشانی وب
     * @return خروجی:  آرایه ای از بایت نشانی وب داده شده
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static byte[] downloadUrlAndRead(@NotNull String url) throws Exception {
        if (ObjectUtils.isEmpty(url)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "url");
        }
        InputStream inputStream = new URL(url).openStream();
        return IOUtils.toByteArray(inputStream);
    }


    /**
     * این متد یک مسیر فایل از ورودی دریافت میکند و  محتویات آن را به صورت آرایه ای از بایت خروجی میدهد
     *
     * @param fileFullPath مسیر فایل
     * @return خروجی: آرایه ای از بایت مسیر فایل داده شده
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    @NotNull
    static byte[] downloadPathAndRead(@NotNull String fileFullPath) throws Exception {
        if (ObjectUtils.isEmpty(fileFullPath)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fileFullPath");
        }
        File file = new File(fileFullPath);
        if (file.exists()) {
            return FileUtils.readFileToByteArray(file);
        } else {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_PATH_IS_NOT_EXISTED, "fileFullPath:" + fileFullPath);
        }
    }

    /**
     * این متد یک مسیر دایرکتوری و نام کامل فایل و آرایه بایت داده را از ورودی دریافت میکند و بعد از ثبت آرایه بایت در مسیر مورد نظر ، مسیر رمزگذاری شده فایل ثبت شده را خروجی میدهد
     *
     * @param directoryPath مسیر دایرکتوری
     * @param fileFullName  نام کامل فایل
     * @param fileBytes     آرایه بایت داده فایل
     * @param withThumbnail مسیر مبدا حاوی تصویر بندانگشتی
     * @param fsoConfigModel        مدل تنظیمات ابزار فایل
     * @return خروجی: مسیر رمزگذاری شده فایل ثبت شده
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    static String uploadWriteToPath(@NotNull String directoryPath, @NotNull String fileFullName, byte[] fileBytes, @NotNull Boolean withThumbnail,FsoConfigModel fsoConfigModel) throws Exception {
        File file = new File(directoryPath + fileFullName);
        FileUtils.writeByteArrayToFile(file, fileBytes);
        if (withThumbnail) {
            for (Integer size : fsoConfigModel.getThumbSizeArray()) {
                ImageTools.createThumb(directoryPath, fileFullName, size, size);
            }
        }
        return EncodingTools.base64Encode(directoryPath + fileFullName);
    }

    /**
     * این متد رشته نام کامل فایل(نام و پسوند) را از ورودی دریافت میکند و رشته پسوند آن را خروجی میدهد
     *
     * @param fileName رشته نام کامل فایل(نام و پسوند)
     * @return خروجی: رشته پسوند فایل
     */
    @NotNull
    static String getFileExtension(@NotNull String fileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fileName");
        }
        Integer lastDot = fileName.lastIndexOf(".");
        if (ObjectUtils.isEmpty(lastDot) || lastDot < 1) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_FILE_NAME_FORMAT_IS_INVALID, "fileName:" + fileName);
        }
        return fileName.substring(lastDot + 1);
    }

    /**
     * این متد رشته نام کامل فایل(نام و پسوند) را از ورودی دریافت میکند و رشته نام فایل بدون پسوند آن را خروجی میدهد
     *
     * @param fileName رشته نام کامل فایل(نام و پسوند)
     * @return خروجی: رشته نام فایل بدون پسوند آن
     */
    @NotNull
    static String getFileNameWithoutExtension(@NotNull String fileName) {
        if (ObjectUtils.isEmpty(fileName)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fileName");
        }
        Integer lastDot = fileName.lastIndexOf(".");
        if (ObjectUtils.isEmpty(lastDot) || lastDot < 1) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_FILE_NAME_FORMAT_IS_INVALID, "fileName:" + fileName);
        }
        return fileName.substring(0, lastDot);
    }

    /**
     * این متد شناسه فایل را از ورودی دریافت میکند و بر اساس محدودیت پوشه عدد متناسب را خروجی میدهد
     *
     * @param fileId شناسه فایل
     * @param folderLimit  حداکثر تعداد مجاز فایل در یک دایرکتوری
     * @return خروجی: عدد متناسب
     */
    @NotNull
    static int getRightLocationForSave(@NotNull Integer fileId,Integer folderLimit) {
        if (ObjectUtils.isEmpty(fileId)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fileId");
        }
        return (((fileId - 1) / folderLimit) + 1);
    }


    /**
     * این متد رشته مسیری برای ایجاد یک پوشه در فایل سیستم و عدد صفر را ورودی میگیرد و مسیر پیشنهادی غیر تکراری پوشه با توجه به ورودی را خروجی میدهد
     *
     * @param directoryFullPath رشته مسیری برای ایجاد یک پوشه در فایل سیستم
     * @param counter           به صورت ثابت عدد صفر ورودی داده شود
     * @return خروجی:  مسیر پیشنهادی غیر تکراری پوشه با توجه به ورودی
     */
    @NotNull
    static String recursiveCreateName(@NotNull String directoryFullPath, @NotNull Integer counter) {
        if (ObjectUtils.isEmpty(directoryFullPath)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "directoryFullPath");
        }
        if (ObjectUtils.isEmpty(counter)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "counter");
        }
        if (!(new File(directoryFullPath + " - Copy").exists())) {
            return directoryFullPath + " - Copy";
        } else {
            if ((new File(directoryFullPath + " - Copy" + " (" + counter + ")").exists())) {
                counter++;
                return recursiveCreateName(directoryFullPath, counter);
            } else {
                return directoryFullPath + " - Copy" + " (" + counter + ")";
            }
        }
    }

    /**
     * این متد رشته مسیر پوشه ، نام و پسوند فایل برای ایجاد یک فایل در فایل سیستم و عدد صفر را ورودی میگیرد و مسیر پیشنهادی غیر تکراری فایل با توجه به ورودی را خروجی میدهد
     *
     * @param pathTo    رشته مسیر پوشه
     * @param fileName  رشته نام فایل
     * @param extension رشته پسوند فایل
     * @param counter   به صورت ثابت عدد صفر ورودی داده شود
     * @return خروجی:  مسیر پیشنهادی غیر تکراری فایل با توجه به ورودی
     */
    @NotNull
    static String recursiveCreateName(@NotNull String pathTo, @NotNull String fileName, @NotNull String extension, @NotNull Integer counter) {
        System.out.println("pathTo:" + pathTo + " fileName:" + fileName + " extension:" + extension);
        if (ObjectUtils.isEmpty(pathTo)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "pathTo");
        }
        if (ObjectUtils.isEmpty(fileName)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "fileName");
        }
        if (ObjectUtils.isEmpty(extension)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "extension");
        }
        if (ObjectUtils.isEmpty(counter)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "counter");
        }
        if (!(new File(pathTo + "/" + fileName + " - Copy" + "." + extension).exists())) {
            return pathTo + "/" + fileName + " - Copy" + "." + extension;
        } else {
            if ((new File(pathTo + "/" + fileName + " - Copy" + " (" + counter + ")" + "." + extension).exists())) {
                counter++;
                return recursiveCreateName(pathTo, fileName, extension, counter);
            } else {
                return pathTo + "/" + fileName + " - Copy" + " (" + counter + ")" + "." + extension;
            }
        }
    }


    /**
     * این متد مسیر یا نام یک فایل را از ورودی دریافت میکند و مدل mimeType آن را خروجی میدهد
     *
     * @param filePath مسیر یا نام کامل فایل
     * @return خروجی: مدل mimeType فایل ورودی
     */
    @NotNull
    static FsoMimeTypeModel getMimeTypeModel(@NotNull String filePath) {
        if (ObjectUtils.isEmpty(filePath)) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "filePath");
        }
        try {
            String mimeType = Files.probeContentType(Paths.get(filePath));
            String[] mimeTypeArray = mimeType.split("/");
            switch (String.valueOf(mimeTypeArray[0]).toLowerCase()) {
                case "image":
                    return new FsoMimeTypeModel(mimeType, FsoMimeTypeEnum.IMAGE, getFileExtension(filePath));
                case "application":
                    return new FsoMimeTypeModel(mimeType, FsoMimeTypeEnum.APPLICATION, getFileExtension(filePath));
                default:
                    return new FsoMimeTypeModel(mimeType, FsoMimeTypeEnum.GENERAL, getFileExtension(filePath));
            }
        } catch (IOException e) {
            throw new UtilityException(FsoTools.class, UtilityExceptionKeyEnum.FSO_MIMETYPE_NOT_VALID_FILE_PATH, "filePath:" + filePath);
        }
    }

}
