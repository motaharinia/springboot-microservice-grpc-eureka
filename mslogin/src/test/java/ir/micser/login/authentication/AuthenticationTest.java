package ir.micser.login.authentication;

import com.motaharinia.msutility.customexception.BusinessException;
import com.motaharinia.msutility.customexception.UtilityException;
import com.motaharinia.msutility.json.CustomObjectMapper;
import ir.micser.login.persistence.orm.captchacode.CaptchaCode;
import ir.micser.login.persistence.orm.captchacode.CaptchaCodeRepository;
import org.apache.catalina.connector.Request;
import org.apache.commons.codec.binary.Base64;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AuthenticationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    CaptchaCodeRepository captchaCodeRepository;

    private CustomObjectMapper customObjectMapper = new CustomObjectMapper();

    private String clientId = "web-client";
    private String clientSecret = "pin";


    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() throws InvocationTargetException, UtilityException, IllegalAccessException, BusinessException {
        Locale.setDefault(new Locale("fa", "IR"));
    }


    @Test
    @Order(1)
    public void authenticationSuccessTest() {
        try {
            String uri = "http://localhost:" + port + "/oauth/token";

            //درخواست بیسیک قبل از لاگین طبق استادندارد oauth2 در هدر
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = clientId + ":" + clientSecret;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.add("Authorization", authHeader);
            headers.add("captchaKey", "");
            headers.add("captchaValue", "");


            //اطلاعات احراز هویت کاربر در Html Body
            MultiValueMap<String, String> dataHashMap = new LinkedMultiValueMap<String, String>();
            dataHashMap.add("grant_type", "password");
            dataHashMap.add("username", "eng.motahari@gmail.com");
            dataHashMap.add("password", "123456789");

            //ساخت درخواست وب از هدر و اطلاعات اهراز هویت
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(dataHashMap, headers);

            //فراخوانی لاگین و دریافت پاسخ
            ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            System.out.println("response:" + response);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getBody()).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(2)
    public void authenticationFailedTest() {
        try {
            String uri = "http://localhost:" + port + "/oauth/token";

            //درخواست بیسیک قبل از لاگین طبق استادندارد oauth2 در هدر
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = clientId + ":" + clientSecret;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.add("Authorization", authHeader);
            headers.add("captchaKey", "");
            headers.add("captchaValue", "");


            //اطلاعات احراز هویت کاربر در Html Body
            MultiValueMap<String, String> dataHashMap = new LinkedMultiValueMap<String, String>();
            dataHashMap.add("grant_type", "password");
            dataHashMap.add("username", "failed@gmail.com");
            dataHashMap.add("password", "123456789");

            //ساخت درخواست وب از هدر و اطلاعات اهراز هویت
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(dataHashMap, headers);

            //فراخوانی لاگین و دریافت پاسخ
            ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);
            System.out.println("response:" + response);
            assertThat(response.getHeaders().get("loginException").get(0)).isEqualTo("loginException.usernameOrPasswordInvalid");
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Test
    @Order(3)
    public void authenticationFailureCountTest() {
        try {
            String uri = "http://localhost:" + port + "/oauth/token";

            //درخواست بیسیک قبل از لاگین طبق استادندارد oauth2 در هدر
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = clientId + ":" + clientSecret;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.add("Authorization", authHeader);
            headers.add("captchaKey", "");
            headers.add("captchaValue", "");


            //اطلاعات احراز هویت کاربر در Html Body
            MultiValueMap<String, String> dataHashMap = new LinkedMultiValueMap<String, String>();
            dataHashMap.add("grant_type", "password");
            dataHashMap.add("username", "eng.motahari@gmail.com");
            dataHashMap.add("password", "123");

            //ساخت درخواست وب از هدر و اطلاعات اهراز هویت
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(dataHashMap, headers);

            //فراخوانی لاگین و دریافت پاسخ
            ResponseEntity<String> response1 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response2 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response3 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response4 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            assertThat(response1).isNotEqualTo(null);
            assertThat(response1.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response1.getBody()).isNotEqualTo(null);
            assertThat(response2).isNotEqualTo(null);
            assertThat(response2.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response2.getBody()).isNotEqualTo(null);
            assertThat(response3).isNotEqualTo(null);
            assertThat(response3.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response3.getBody()).isNotEqualTo(null);
            assertThat(response4).isNotEqualTo(null);
            assertThat(response4.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response4.getBody()).isNotEqualTo(null);
            System.out.println("response4:" + response4);
            assertThat(Integer.valueOf(response4.getHeaders().get("loginFailureCount").get(0))).isGreaterThan(3);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Test
    @Order(4)
    public void authenticationFailureCountCaptchaImageTest() {
        try {
            String uri = "http://localhost:" + port + "/oauth/token";

            //درخواست بیسیک قبل از لاگین طبق استادندارد oauth2 در هدر
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = clientId + ":" + clientSecret;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.add("Authorization", authHeader);
            headers.add("captchaKey", "");
            headers.add("captchaValue", "");


            //اطلاعات احراز هویت کاربر در Html Body
            MultiValueMap<String, String> dataHashMap = new LinkedMultiValueMap<String, String>();
            dataHashMap.add("grant_type", "password");
            dataHashMap.add("username", "eng.motahari@gmail.com");
            dataHashMap.add("password", "123");

            //ساخت درخواست وب از هدر و اطلاعات اهراز هویت
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(dataHashMap, headers);

            //فراخوانی لاگین و دریافت پاسخ
            ResponseEntity<String> response1 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response2 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response3 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response4 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            assertThat(response1).isNotEqualTo(null);
            assertThat(response1.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response1.getBody()).isNotEqualTo(null);
            assertThat(response2).isNotEqualTo(null);
            assertThat(response2.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response2.getBody()).isNotEqualTo(null);
            assertThat(response3).isNotEqualTo(null);
            assertThat(response3.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response3.getBody()).isNotEqualTo(null);
            assertThat(response4).isNotEqualTo(null);
            assertThat(response4.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response4.getBody()).isNotEqualTo(null);
            //دریافت تصویر و کلید کپچا
            uri = "http://localhost:" + port + "/v1/captchaCode/LOGIN_CHECK_BY_USERNAME/eng.motahari@gmail.com/";
            headers = new HttpHeaders();
            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
            acceptableMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccept(acceptableMediaTypes);
            HttpEntity<Request> httpEntity = new HttpEntity<Request>(headers);
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, byte[].class);
            System.out.println("responseEntity:" + responseEntity);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                assertThat(responseEntity.getBody().length).isGreaterThan(0);
            } else {
                fail("Status code is " + responseEntity.getStatusCode());
            }

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Test
    @Order(5)
    public void authenticationFailureCountCaptchaTokenTest() {
        try {
            String uri = "http://localhost:" + port + "/oauth/token";

            //درخواست بیسیک قبل از لاگین طبق استادندارد oauth2 در هدر
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            String auth = clientId + ":" + clientSecret;
            byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            headers.add("Authorization", authHeader);
            headers.add("captchaKey", "");
            headers.add("captchaValue", "");


            //اطلاعات احراز هویت کاربر در Html Body
            MultiValueMap<String, String> dataHashMap = new LinkedMultiValueMap<String, String>();
            dataHashMap.add("grant_type", "password");
            dataHashMap.add("username", "eng.motahari@gmail.com");
            dataHashMap.add("password", "123");

            //ساخت درخواست وب از هدر و اطلاعات اهراز هویت
            HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(dataHashMap, headers);

            //فراخوانی لاگین و دریافت پاسخ
            ResponseEntity<String> response1 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response2 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response3 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
            ResponseEntity<String> response4 = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);

            assertThat(response1).isNotEqualTo(null);
            assertThat(response1.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response1.getBody()).isNotEqualTo(null);
            assertThat(response2).isNotEqualTo(null);
            assertThat(response2.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response2.getBody()).isNotEqualTo(null);
            assertThat(response3).isNotEqualTo(null);
            assertThat(response3.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response3.getBody()).isNotEqualTo(null);
            assertThat(response4).isNotEqualTo(null);
            assertThat(response4.getStatusCode()).isNotEqualTo(HttpStatus.OK);
            assertThat(response4.getBody()).isNotEqualTo(null);
            //دریافت تصویر و کلید کپچا
            String uriCaptchaImage = "http://localhost:" + port + "/v1/captchaCode/LOGIN_CHECK_BY_USERNAME/eng.motahari@gmail.com/";
            headers = new HttpHeaders();
            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.IMAGE_JPEG);
            acceptableMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
            headers.setAccept(acceptableMediaTypes);
            HttpEntity<Request> httpEntity = new HttpEntity<Request>(headers);
            ResponseEntity<byte[]> responseEntity = restTemplate.exchange(uriCaptchaImage, HttpMethod.GET, httpEntity, byte[].class);
            System.out.println("responseEntity:" + responseEntity);
            if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
                String captchaKey = responseEntity.getHeaders().get("captchaKey").get(0);
                CaptchaCode captchaCode = captchaCodeRepository.findByCaptchaKeyEquals(captchaKey);
                if (!ObjectUtils.isEmpty(captchaCode)) {
                    //درخواست بیسیک قبل از لاگین طبق استادندارد oauth2 در هدر
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
                    headers.add("Authorization", authHeader);
                    headers.add("captchaKey", captchaKey);
                    headers.add("captchaValue", captchaCode.getCaptchaValue());


                    //اطلاعات احراز هویت کاربر در Html Body
                    dataHashMap = new LinkedMultiValueMap<String, String>();
                    dataHashMap.add("grant_type", "password");
                    dataHashMap.add("username", "eng.motahari@gmail.com");
                    dataHashMap.add("password", "123456789");

                    //ساخت درخواست وب از هدر و اطلاعات اهراز هویت
                    request = new HttpEntity<MultiValueMap<String, String>>(dataHashMap, headers);

                    //فراخوانی لاگین و دریافت پاسخ
                    ResponseEntity<String> response = restTemplate.exchange(uri,HttpMethod.POST, request, String.class);
                    assertThat(response).isNotEqualTo(null);
                    assertThat(response.getBody()).isNotEqualTo(null);
                    System.out.println("response:" + response);
                    assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
                } else {
                    fail("Fail: Captcha code not found!");
                }
            } else {
                fail("Fail: Captcha code image status code is " + responseEntity.getStatusCode());
            }

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

}
