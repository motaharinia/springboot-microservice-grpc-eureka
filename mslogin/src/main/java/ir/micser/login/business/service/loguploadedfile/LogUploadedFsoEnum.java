package ir.micser.login.business.service.loguploadedfile;


public enum LogUploadedFsoEnum {
    //common subsystem:
    ADMIN_USER_PROFILE_IMAGE("/common/adminuser/%ENTITYID%/profileimage/"),
    ;

    private final String value;

    private LogUploadedFsoEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getEntityKindDirectoryPath(Integer entityId) {
        //example: "/eshop/product/27/image3dfile/"
        String entityKindDirectoryPath = this.getValue().replace("%ENTITYID%", entityId.toString());
        return entityKindDirectoryPath;
    }

    public String getEntityDirectoryPath() {
        Integer entityIdIndex = this.getValue().indexOf("%ENTITYID%");
        //example: "/eshop/product/"
        String entityDirectoryPath = this.getValue().substring(0, entityIdIndex);
        return entityDirectoryPath;
    }

    public String getKindDirectoryPath(Integer entityId) {
        //example: "/eshop/product/27/image3dfile/"
        String entityKindDirectoryPath = getEntityKindDirectoryPath(entityId);
        Integer entityIdIndex = this.getValue().indexOf("%ENTITYID%");
        //example: "/eshop/product/"
        String entityDirectoryPath = this.getValue().substring(0, entityIdIndex);
        //example: "/27/image3dfile/"
        String kindDirectoryPath = "/" + entityKindDirectoryPath.replace(entityDirectoryPath, "");
        return kindDirectoryPath;
    }

    public String getKindFolderName() {
        String[] tempArray = this.getValue().split("/", -1);
        if (tempArray.length > 1) {
            return tempArray[tempArray.length - 2];
        } else {
            return "";
        }
    }
}
