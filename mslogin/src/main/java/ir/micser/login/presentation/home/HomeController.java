package ir.micser.login.presentation.home;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 *  کلاس کنترلر خانه
 */
@RestController
public class HomeController {


    @GetMapping("/")
    public String home() {
        return "Hello Homepage!, mslogin";
    }


}
