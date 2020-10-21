package com.motaharinia.msutility.zip;


import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customexception.UtilityExceptionKeyEnum;
import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * اینترفیس متدهای ابزاری کار با فایلهای فشرده
 */
public interface ZipTools {


    static void zip(List<String> pathToAdd, String zipFileFullPath, CompressionMethod compressionMethod, CompressionLevel compressionLevel, String password, EncryptionMethod passwordEncryptionMethod, AesKeyStrength passwordAesKeyStrength) throws ZipException {

        List<File> fileList = new ArrayList<>();
        List<File> directoryList = new ArrayList<>();

        File file;
        for (String path : pathToAdd) {
            file = new File(path);
            if (file.exists()) {
                if (file.isDirectory()) {
                    directoryList.add(file);
                } else {
                    fileList.add(file);
                }
            }
        }

        ZipParameters parameters = new ZipParameters();
        parameters.setCompressionMethod(compressionMethod);
        parameters.setCompressionLevel(compressionLevel);

        ZipFile zipFile;
        if (ObjectUtils.isEmpty(password)) {
            zipFile = new ZipFile(zipFileFullPath);
        } else {
            zipFile = new ZipFile(zipFileFullPath, password.toCharArray());
            parameters.setEncryptFiles(true);
            parameters.setEncryptionMethod(passwordEncryptionMethod);
            parameters.setAesKeyStrength(passwordAesKeyStrength);
        }
        if (!ObjectUtils.isEmpty(fileList)) {
            zipFile.addFiles(fileList, parameters);
        }
        for (File directory : directoryList) {
            zipFile.addFolder(directory, parameters);
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
    static void unzipFromByteArray(@NotNull byte[] sourceByteArray, @NotNull String destinationDirectoryForUnzip, String password) throws Exception {
        if (ObjectUtils.isEmpty(sourceByteArray)) {
            throw new UtilityException(ZipTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceByteArray");
        }
        if (ObjectUtils.isEmpty(destinationDirectoryForUnzip)) {
            throw new UtilityException(ZipTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDirectoryForUnzip");
        }
        File tempFile = File.createTempFile("tmp", ".zip", null);
        FileOutputStream fos = new FileOutputStream(tempFile);
        fos.write(sourceByteArray);
        ZipFile zipFile = new ZipFile(tempFile);
        if (zipFile.isEncrypted() && !ObjectUtils.isEmpty(password)) {
            zipFile.setPassword(password.toCharArray());
        }
        zipFile.extractAll(destinationDirectoryForUnzip);
    }

    /**
     * این متد مسیر فایل زیپ و مسیری برای استخراج فایل زیپ و رمز استخراج فایل زیپ را از ورودی دریافت میکند و در مسیر آن را استخراج مینماید
     *
     * @param sourceZipFilePath            مسیر فایل زیپ
     * @param destinationDirectoryForUnzip مسیری برای استخراج فایل زیپ
     * @param password                     رمز استخراج فایل زیپ
     * @throws Exception این متد ممکن است اکسپشن داشته باشد
     */
    static void unzip(@NotNull String sourceZipFilePath, @NotNull String destinationDirectoryForUnzip, String password) throws Exception {
        if (ObjectUtils.isEmpty(sourceZipFilePath)) {
            throw new UtilityException(ZipTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "sourceZipFilePath");
        }
        if (ObjectUtils.isEmpty(destinationDirectoryForUnzip)) {
            throw new UtilityException(ZipTools.class, UtilityExceptionKeyEnum.METHOD_PARAMETER_IS_NULL_OR_EMPTY, "destinationDirectoryForUnzip");
        }
        ZipFile zipFile = new ZipFile(sourceZipFilePath);
        if (zipFile.isEncrypted() && !ObjectUtils.isEmpty(password)) {
            zipFile.setPassword(password.toCharArray());
        }
        zipFile.extractAll(destinationDirectoryForUnzip);
    }

}
