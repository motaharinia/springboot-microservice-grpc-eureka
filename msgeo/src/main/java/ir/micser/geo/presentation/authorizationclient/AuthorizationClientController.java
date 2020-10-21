package ir.micser.geo.presentation.authorizationclient;


import ir.micser.geo.business.service.authorizationclient.AuthorizationClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس کنترلر تست کلاینت مجوز دسترسی
 */
@RestController
public class AuthorizationClientController {

    @Autowired
    AuthorizationClientServiceImpl authorizationClientServiceImpl;

    @GetMapping("/authorizationClient")
    public String test() {
        return "authorizationClientServiceImpl.checkAccess():" + authorizationClientServiceImpl.checkAccess("");
    }


}
