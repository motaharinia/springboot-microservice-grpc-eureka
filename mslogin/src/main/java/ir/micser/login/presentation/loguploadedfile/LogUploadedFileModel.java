/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ir.micser.login.presentation.loguploadedfile;

import com.motaharinia.msutility.fso.upload.FileUploadedModel;

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class LogUploadedFileModel {

    private String fileKey;
    private String fileName;
    private String fileExtension;
    private String fileFullName;
    private Date fileUploadDateTime;
    private Long fileSize;
    private String fileMimeType;
    private String fileUploadedPath;
    private String fileEntity;
    private String fileSubSystem;
    private byte[] fileByteArray;
    private String directoryRealPath;
    private String directoryHashedPath;


    public FileUploadedModel getFileUploadedModel(){
        FileUploadedModel fileUploadedModel=new FileUploadedModel();
        fileUploadedModel.setUploadDateTime(this.getFileUploadDateTime());
        fileUploadedModel.setDataByteArray(this.getFileByteArray());
        fileUploadedModel.setDirectoryHashedPath(this.getDirectoryHashedPath());
        fileUploadedModel.setDirectoryRealPath(this.getDirectoryRealPath());
        fileUploadedModel.setEntity(this.getFileEntity());
        fileUploadedModel.setSubSystem(this.getFileSubSystem());
        fileUploadedModel.setUploadedPath(this.getFileUploadedPath());
        fileUploadedModel.setExtension(this.getFileExtension());
        fileUploadedModel.setFullName(this.getFileFullName());
        fileUploadedModel.setMimeType(this.getFileMimeType());
        fileUploadedModel.setName(this.getFileName());
        Long size=this.getFileSize();
        fileUploadedModel.setSize(size);
        return fileUploadedModel;
    }



    //getter-setter:
    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension(String fileExtension) {
        this.fileExtension = fileExtension;
    }

    public String getFileFullName() {
        return fileFullName;
    }

    public void setFileFullName(String fileFullName) {
        this.fileFullName = fileFullName;
    }

    public Date getFileUploadDateTime() {
        return fileUploadDateTime;
    }

    public void setFileUploadDateTime(Date fileUploadDateTime) {
        this.fileUploadDateTime = fileUploadDateTime;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileMimeType() {
        return fileMimeType;
    }

    public void setFileMimeType(String fileMimeType) {
        this.fileMimeType = fileMimeType;
    }

    public String getFileUploadedPath() {
        return fileUploadedPath;
    }

    public void setFileUploadedPath(String fileUploadedPath) {
        this.fileUploadedPath = fileUploadedPath;
    }

    public String getFileEntity() {
        return fileEntity;
    }

    public void setFileEntity(String fileEntity) {
        this.fileEntity = fileEntity;
    }

    public String getFileSubSystem() {
        return fileSubSystem;
    }

    public void setFileSubSystem(String fileSubSystem) {
        this.fileSubSystem = fileSubSystem;
    }

    public byte[] getFileByteArray() {
        return fileByteArray;
    }

    public void setFileByteArray(byte[] fileByteArray) {
        this.fileByteArray = fileByteArray;
    }

    public String getDirectoryRealPath() {
        return directoryRealPath;
    }

    public void setDirectoryRealPath(String directoryRealPath) {
        this.directoryRealPath = directoryRealPath;
    }

    public String getDirectoryHashedPath() {
        return directoryHashedPath;
    }

    public void setDirectoryHashedPath(String directoryHashedPath) {
        this.directoryHashedPath = directoryHashedPath;
    }

}
