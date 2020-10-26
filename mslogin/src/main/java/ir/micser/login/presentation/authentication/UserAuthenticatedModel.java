package ir.micser.login.presentation.authentication;

import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.customfield.CustomDate;
import ir.micser.login.persistence.orm.adminuser.AdminUser;
import ir.micser.login.presentation.adminuser.AdminUserModel;
import ir.micser.login.presentation.adminuserskill.AdminUserSkillModel;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;

public class UserAuthenticatedModel extends AdminUserModel implements Serializable {
    public static UserAuthenticatedModel getUserAuthenticatedModel(AdminUser adminUser) throws UtilityException {
        UserAuthenticatedModel userAuthenticatedModel = new UserAuthenticatedModel();
        if (!ObjectUtils.isEmpty(adminUser)) {
            userAuthenticatedModel.setId(adminUser.getId());
            userAuthenticatedModel.setUsername(adminUser.getUsername());
            userAuthenticatedModel.setPassword(adminUser.getPassword());
            userAuthenticatedModel.setFirstName(adminUser.getFirstName());
            userAuthenticatedModel.setLastName(adminUser.getLastName());
            userAuthenticatedModel.setDateOfBirth(new CustomDate(adminUser.getDateOfBirth()));
            if (!ObjectUtils.isEmpty(adminUser.getDefaultAdminUserContact())) {
                userAuthenticatedModel.setDefaultAdminUserContact_address(adminUser.getDefaultAdminUserContact().getAddress());
            }
            if (!ObjectUtils.isEmpty(adminUser.getGender())) {
                userAuthenticatedModel.setGender_id(adminUser.getGender().getId());
            }
            if (!ObjectUtils.isEmpty(adminUser.getSkillSet())) {
                adminUser.getSkillSet().stream().forEach(item -> {
                    AdminUserSkillModel adminUserSkillModel = new AdminUserSkillModel();
                    adminUserSkillModel.setId(item.getId());
                    adminUserSkillModel.setTitle(item.getTitle());
                    userAuthenticatedModel.getSkillList().add(adminUserSkillModel);
                });
            }
        }
        return userAuthenticatedModel;
    }
}
