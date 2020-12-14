package ir.micser.login.presentation.async;


import com.motaharinia.msutility.json.PrimitiveResponse;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.ActiveProfiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 13:41:19<br>
 * Description:<br>
 * کلاس تست کنترلر ناهمزمانی
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class AsyncControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    @Qualifier("asyncExecutor1")
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;


    @Test()
    public void testAsyncBlock() {
        try {

            //درخواست وب
            String uri = "http://localhost:" + port + "/v1/async/block";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyy-mm-dd hh:mm:ss");
            //زمان شروع
            Date firstDate = sdf.parse(sdf.format(new Date()));
            ResponseEntity<PrimitiveResponse> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, PrimitiveResponse.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);

            //زمان پایان
            Date secondDate = sdf.parse(sdf.format(new Date()));

            //اختلاف زمان شروع و زمان پایان
            long diffInMillies = Math.abs(secondDate.getTime() - firstDate.getTime());
            System.out.println("diffInMillies:::" + diffInMillies);

            //در این تست اختلاف زمان شروع و زمان پایان متدهای آسینک را محاسبه میکند
            //چون در متدهای آسینک برای همه شان 2000میلی ثانیه sleep را ست کرده ایم بنابراین انتظار داریم که زیر 5000میلی ثانیه جواب را دریافت نماییم
            assertThat(diffInMillies).isLessThan(5000L);


        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


    @Test
    public void testAsyncThenApply() {
        try {

            //درخواست وب
            String uri = "http://localhost:" + port + "/v1/async/thenApply";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity entity = new HttpEntity(headers);
            ResponseEntity<PrimitiveResponse> response = this.restTemplate.exchange(uri, HttpMethod.GET, entity, PrimitiveResponse.class);
            assertThat(response).isNotEqualTo(null);
            assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
            assertThat(response.getBody()).isNotEqualTo(null);

            //چک میکند که تعداد تردها صفر است یا نه
            assertThat((int) response.getBody().getResponse()).isEqualTo(0);
            //چک میکند تعداد تردهای فعال صفر است یا نه
//            assertThat(threadPoolTaskExecutor.getActiveCount()).isEqualTo(0);

            //نمایش نام نخ فعلی
            System.out.println("testAsyncThenApply Thread.currentThread().getName():" + Thread.currentThread().getName());

        } catch (Exception ex) {
            fail(ex.toString());
        }
    }


}
