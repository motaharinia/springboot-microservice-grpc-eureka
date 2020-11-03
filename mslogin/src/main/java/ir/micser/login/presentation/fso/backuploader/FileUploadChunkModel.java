package ir.micser.login.presentation.fso.backuploader;


import ir.micser.login.business.service.SubSystemEnum;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:کلاس مدل ارسال تکه ای فایل برای آپلودر بک پنل
 *
 */
public class FileUploadChunkModel {

    /**
     * زیرسیستم انتیتی
     */
    private SubSystemEnum subSystem;
    /**
     * انتیتی
     */
    private String entity;
    /**
     * کلید فایلی که در کلاینت تولید میشود و برای هر فایل در حال آپلود یونیک است
     */
    private String fileKey;
    /**
     * نام فایل در کلاینت
     */
    private String name;
    /**
     * تعداد تکه های داده فایل
     */
    private Integer chunks = -1;
    /**
     * شماره تکه فعلی از داده فایل در حال ارسال
     */
    private Integer chunk = -1;
    /**
     * مسیر فایل در کلاینت
     */
    private String filePath;

    //getter-setter:
    public SubSystemEnum getSubSystem() {
        return subSystem;
    }

    public void setSubSystem(SubSystemEnum subSystem) {
        this.subSystem = subSystem;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getChunks() {
        return chunks;
    }

    public void setChunks(Integer chunks) {
        this.chunks = chunks;
    }

    public Integer getChunk() {
        return chunk;
    }

    public void setChunk(Integer chunk) {
        this.chunk = chunk;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
