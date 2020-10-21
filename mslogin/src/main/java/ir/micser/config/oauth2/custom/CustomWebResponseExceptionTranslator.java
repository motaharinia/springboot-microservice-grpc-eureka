package ir.micser.config.oauth2.custom;

import com.motaharinia.msutility.json.CustomObjectMapper;
import ir.micser.login.business.service.authentication.AuthenticationService;
import ir.micser.login.business.service.authentication.LoginExceptionEnum;
import ir.micser.login.business.service.authentication.LoginExceptionModel;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator implements WebResponseExceptionTranslator<OAuth2Exception> {



    @Autowired
    @Qualifier(value = "AuthenticationServiceImpl")
    private AuthenticationService authenticationService;

    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {

        String exceptionStackTrace = "";
        if (e.getStackTrace() != null) {
            exceptionStackTrace = ExceptionUtils.getStackTrace(e);
        }
        String exceptionMessage = e.getMessage();

        ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
        HttpHeaders headers = new HttpHeaders();
        headers.setAll(responseEntity.getHeaders().toSingleValueMap());

        if ((e instanceof InvalidGrantException) || (e instanceof InternalAuthenticationServiceException)) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            LoginExceptionModel loginExceptionModel = authenticationService.loginFailed(request, response, LoginExceptionEnum.USERNAME_OR_PASSWORD_INVALID);
            CustomObjectMapper customObjectMapper = new CustomObjectMapper(null);
            exceptionMessage = customObjectMapper.writeValueAsString(loginExceptionModel);
            OAuth2Exception oAuth2Exception = new OAuth2Exception(exceptionMessage);

            return new ResponseEntity<>(oAuth2Exception, headers, HttpStatus.UNAUTHORIZED);
        } else {
            System.out.println("exceptionMessage:" + exceptionMessage);
            System.out.println("exceptionStackTrace:");
            System.out.println(exceptionStackTrace);
            OAuth2Exception oAuth2Exception = new OAuth2Exception(exceptionMessage);
            return new ResponseEntity<>(oAuth2Exception, headers, HttpStatus.BAD_REQUEST);
        }
    }

}
