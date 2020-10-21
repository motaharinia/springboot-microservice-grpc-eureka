package ir.micser.config.oauth2.custom;


import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.json.CustomObjectMapper;
import ir.micser.login.business.service.authentication.AuthenticationService;
import ir.micser.login.business.service.authentication.LoginExceptionEnum;
import ir.micser.login.business.service.authentication.LoginExceptionModel;
import ir.micser.login.business.service.captchacode.CaptchCodeTypeEnum;
import ir.micser.login.business.service.captchacode.CaptchaCodeBusinessExceptionKeyEnum;
import ir.micser.login.business.service.captchacode.CaptchaCodeService;
import ir.micser.login.presentation.captchacode.CaptchaCodeCheckModel;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Administrator
 */
public class CaptchaFilter extends OncePerRequestFilter {

    @Autowired
    @Qualifier(value = "AuthenticationServiceImpl")
    private AuthenticationService authenticationService;

    @Autowired
    @Qualifier(value = "CaptchaCodeServiceImpl")
    private CaptchaCodeService captchaCodeService;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //enable autowired in filter classes
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        //به دست آوردن کلمه کاربری که کاربر وارد کرده است
        String formUsernameKey = UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY;
        String username = request.getParameter(formUsernameKey);

        LoginExceptionModel loginExceptionModel = null;
        String exceptionMessage = "";

        if (ObjectUtils.isEmpty(username)) {
            //اگر کلمه کاربری خالی است این فیلتر را رد کن
            chain.doFilter(request, response);
        } else {
            try {
                //به دست آوردن تعداد دفعات ورود اشتباه کلمه کاربری و رمز عبور
                Integer failureCount = authenticationService.getFailureCountByUsername(username);
                if (failureCount > 3) {
                    //اگر تعداد دفعات بیشتر از 3 بار بود کپچا فعال میشود
                    String captchaKey = request.getHeader("captchaKey");
                    String captchaValue = request.getHeader("captchaValue");
                    if ((ObjectUtils.isNotEmpty(captchaKey)) && (ObjectUtils.isNotEmpty(captchaValue))) {
                        CaptchaCodeCheckModel captchaCodeCheckModel = captchaCodeService.check(CaptchCodeTypeEnum.LOGIN_CHECK_BY_USERNAME, null, username, captchaKey, captchaValue);
                        if (ObjectUtils.isEmpty(captchaCodeCheckModel)) {
                            //اگر کلید کپچا فیک است و دستکاری شده است
                            loginExceptionModel = authenticationService.loginFailed(request, response, LoginExceptionEnum.CAPTCHA_IGNORED);
                        } else if (!ObjectUtils.isEmpty(captchaCodeCheckModel.getCaptchaException())) {
                            //اگر خطایی در بررسی کد کپچا داریم
                            Exception exceptionIgnore = new BusinessException(CaptchaFilter.class, CaptchaCodeBusinessExceptionKeyEnum.CAPTCHA_CODE_IGNORE, "");
                            if (captchaCodeCheckModel.getCaptchaException().getMessage().equals(exceptionIgnore.getMessage())) {
                                loginExceptionModel = authenticationService.loginFailed(request, response, LoginExceptionEnum.CAPTCHA_IGNORED);
                            } else {
                                loginExceptionModel = authenticationService.loginFailed(request, response, LoginExceptionEnum.CAPTCHA_INVALID);
                            }
                            CustomObjectMapper customObjectMapper = new CustomObjectMapper(null);
                            exceptionMessage = customObjectMapper.writeValueAsString(loginExceptionModel);
                            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exceptionMessage);
                        } else {
                            //اگر خطایی در بررسی کد کپچا نداریم فیلتر را رد کن
                            chain.doFilter((ServletRequest) request, (ServletResponse) response);
                        }
                    } else {
                        //کلید و مقدار کپچا با وجود اینکه تعداد دفعات ورود اشتباه کلمه کاربری و رمز عبور بیش از 3 بار بوده خالی است
                        loginExceptionModel = authenticationService.loginFailed(request, response, LoginExceptionEnum.CAPTCHA_REQUIRED);
                        CustomObjectMapper customObjectMapper = new CustomObjectMapper(null);
                        exceptionMessage = customObjectMapper.writeValueAsString(loginExceptionModel);
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exceptionMessage);
                    }
                } else {
                    //اگر تعداد دفعات بیشتر از 3 بار نبود کپچا غیرفعال است و فیلتر را رد کن
                    chain.doFilter((ServletRequest) request, (ServletResponse) response);
                }

            } catch (Exception ex) {
                //در صورت بروز هر گونه خطای زمان اجرا
                loginExceptionModel = new LoginExceptionModel();
                loginExceptionModel.setExceptionDescription(ex.getMessage());
                CustomObjectMapper customObjectMapper = new CustomObjectMapper(null);
                exceptionMessage = customObjectMapper.writeValueAsString(loginExceptionModel);
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, exceptionMessage);
            }

        }
    }
}
