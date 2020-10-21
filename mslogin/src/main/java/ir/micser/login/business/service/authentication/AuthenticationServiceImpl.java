package ir.micser.login.business.service.authentication;
import ir.micser.login.business.service.adminuserskill.AdminUserSkillService;
import ir.micser.login.business.service.etcitem.EtcItemService;
import ir.micser.login.persistence.orm.adminuser.AdminUser;
import ir.micser.login.persistence.orm.adminuser.AdminUserRepository;
import ir.micser.login.persistence.orm.adminusercontact.AdminUserContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Qualifier("AuthenticationServiceImpl")
@Service
@Transactional(rollbackFor = Exception.class)
public class AuthenticationServiceImpl implements AuthenticationService {

//    @Autowired
//    @Qualifier(value = "UserDaoImpl")
//    private UserDao userDao;
//
//    @Autowired
//    @Qualifier(value = "NotificationServiceImpl")
//    private NotificationService notificationService;
//
//    @Autowired
//    @Qualifier(value = "NotificationSendServiceImpl")
//    private NotificationSendService notificationSendService;

    /**
     * ریپازیتوری ادمین
     */
    private AdminUserRepository adminUserRepository;
    /**
     * ریپازیتوری اطلاعات تماس ادمین
     */
    private AdminUserContactRepository adminUserContactRepository;

    /**
     * سرویس مهارتها
     */
    private AdminUserSkillService adminUserSkillService;

    /**
     * سرویس مقادیر ثابت
     */
    private EtcItemService etcItemService;

    @Autowired
    public AuthenticationServiceImpl(AdminUserRepository adminUserRepository, AdminUserContactRepository adminUserContactRepository, AdminUserSkillService adminUserSkillService, EtcItemService etcItemService) {
        this.adminUserRepository = adminUserRepository;
        this.adminUserContactRepository = adminUserContactRepository;
        this.adminUserSkillService = adminUserSkillService;
        this.etcItemService = etcItemService;
    }

    @Override
    public LoginExceptionModel loginFailed(HttpServletRequest request, HttpServletResponse response, LoginExceptionEnum loginExceptionEnum) throws Exception {
        String formUsernameKey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
        String username = request.getParameter(formUsernameKey);
        Integer loginFailureCount = this.getFailureCountByUsername(username);
        loginFailureCount++;
        Boolean usernameExist = this.setFailureCountByUsername(username, loginFailureCount);
        if (!usernameExist) {
            loginExceptionEnum = LoginExceptionEnum.USERNAME_OR_PASSWORD_INVALID;
        }

        LoginExceptionModel loginExceptionModel = new LoginExceptionModel(loginExceptionEnum, loginFailureCount);

        //config response
        response.addHeader("Access-Control-Expose-Headers", "loginException, loginFailureCount");
        response.addHeader("loginException", loginExceptionModel.getLoginException().getValue());
        response.addHeader("loginFailureCount", loginExceptionModel.getLoginFailureCount().toString());



        //handle notification
//        UserNotificationSendModel userNotificationSendModel = new UserNotificationSendModel();
//        userNotificationSendModel.setUsername(username);
//        userNotificationSendModel.setMessage(loginExceptionEnum.getValue());
//        notificationService.createLoginNotification(request, NotificationTypeEnum.LOGIN_FAILED, userNotificationSendModel, new HashMap<String, String>());
//        notificationSendService.sendNotifications();
//        }
        return loginExceptionModel;
    }

    @Override
    public Integer getFailureCountByUsername(String username) throws Exception {
        AdminUser adminUser = adminUserRepository.findUserByUsername(username);
        if (adminUser != null) {
            if (adminUser.getLoginFailureCount() == null) {
                return 0;
            } else {
                return adminUser.getLoginFailureCount();
            }
        } else {
            return 0;
        }
    }

    @Override
    public Boolean setFailureCountByUsername(String username, Integer failureCount) throws Exception {
        AdminUser adminUser = adminUserRepository.findUserByUsername(username);
        return this.setFailureCountByUser(adminUser, failureCount);
    }

    @Override
    public Boolean setFailureCountByUser(AdminUser adminUser, Integer failureCount) throws Exception {
        if (adminUser != null) {
            adminUser.setLoginFailureCount(failureCount);
            adminUserRepository.save(adminUser);
            return true;
        } else {
            return false;
        }

    }
}

