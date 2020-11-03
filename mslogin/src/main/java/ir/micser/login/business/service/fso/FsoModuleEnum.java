package ir.micser.login.business.service.fso;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:مقادیر ثابت عنوان (شامل نام و نوع فایل مربوط به انتیتی) و مقدار (مسیر قرار گیری فایلهای ماژول)
 *
 */
public enum FsoModuleEnum {
    //common subsystem:
    ADMIN_USER_PROFILE_IMAGE("/common/adminuser/%ENTITYID%/profileimage/"),
    ;

    private final String value;

    private FsoModuleEnum(String value) {
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
