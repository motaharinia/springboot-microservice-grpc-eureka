package ir.micser.login.scheduling.business.discount;


import ir.micser.config.scheduling.business.discount.DiscountSchedule;
import org.joda.time.DateTime;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-06-12<br>
 * Time: 01:05:58<br>
 * Description:<br>
 * کلاس تست ماژول زمان بندی تخفیفها
 */
@SpringBootTest
@ActiveProfiles("dev")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DiscountScheduleTest {

    @Autowired
    DiscountSchedule discountSchedule;

    Integer hours = 0;

    /**
     * این متد مقادیر پیش فرض قبل از هر تست این کلاس تست را مقداردهی اولیه میکند
     */
    @BeforeEach
    void initUseCase() {
        DateTime dt = new DateTime();  // current time
        hours = dt.getHourOfDay(); // gets hour of day
    }

    @Test
    @Order(1)
    public void fixedTest() {
        try {
            Thread.sleep(16000L);
            System.out.println("discountSchedule.fixedRateCount:" + discountSchedule.fixedRateCount.get());
            System.out.println("discountSchedule.fixedDelayCount:" + discountSchedule.fixedDelayCount.get());
            assertThat(discountSchedule.fixedRateCount.get()).isGreaterThanOrEqualTo(3);
            assertThat(discountSchedule.fixedDelayCount.get()).isGreaterThanOrEqualTo(1);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    @Order(2)
    public void cronJobSchTest() {
        try {
            if (hours == 9) {
                System.out.println("discountSchedule.cronJobSchCount:" + discountSchedule.cronJobSchCount.get());
                assertThat(discountSchedule.cronJobSchCount.get()).isGreaterThanOrEqualTo(0);
           } else {
                System.out.println("discountSchedule.cronJobSchCount:" + discountSchedule.cronJobSchCount.get());
                assertThat(discountSchedule.cronJobSchCount.get()).isEqualTo(0);
            }
            Thread.sleep(2000000000);
        } catch (Exception ex) {
            fail(ex.toString());
        }
    }

    @Test
    public void givenFixedClock_whenNow_thenGetFixedInstant() throws Exception {
//        String instantExpected = "2014-12-22T10:15:30Z";
//        Clock clock = Clock.fixed(Instant.parse(instantExpected), ZoneId.of("UTC"));
//
//        Instant instant = Instant.now(clock);
//
//        assertThat(instant.toString()).isEqualTo(instantExpected);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String value = "2015-04-12 20:26:14";
        Date date = dateFormat.parse(value);
        value = dateFormat.format(date);
        System.out.println("for debugging");
        final Process dateProcess = Runtime.getRuntime().exec("cmd /c date -s "+value.substring(0, value.lastIndexOf(' ')));
        System.out.println("step 1");
        System.out.println(" CurrentTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        dateProcess.waitFor();


        //https://stackoverflow.com/questions/6203857/how-can-i-set-the-system-time-in-java
        //https://www.baeldung.com/java-override-system-time
    }

}
