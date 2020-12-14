package ir.micser.login.business.service.scheduling;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-02<br>
 * Time: 15:02:05<br>
 * Description:<br>
 * کلاس زمان بندی تخفیفها
 */
@Service
public class DiscountService {

    /**
     * شمارنده متد زمانبندی ثابت هر 5  ثانیه یک بار
     */
    public AtomicInteger fixedRateCount = new AtomicInteger(0);


    /**
     * شمارنده متد زمانبندی ثابت هر 5  ثانیه یک بار همراه با تاخیر اولیه 10 ثانیه
     */
    public AtomicInteger fixedDelayCount = new AtomicInteger(0);

    /**
     * شمارنده متد زمانبندی که از ساعت 9 تا 9:59 اجرا میشود
     */
    public AtomicInteger cronJobSchCount = new AtomicInteger(0);

    /**
     * متد زمان بندی ثابت هر 5  ثانیه یک بار
     */
    @Scheduled(fixedRate = 5000)
    public void fixedRate() {
        //به شمارنده اضافه میکنیم
        this.fixedRateCount.incrementAndGet();

        //نمایش نام نخ فعلی
        System.out.println("fixedRate Thread.currentThread().getName():" + Thread.currentThread().getName() + " CurrentTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
    }


    /**
     * متد زمان بندی ثابت هر 5  ثانیه یک بار همراه با تاخیر اولیه 10 ثانیه
     */
    @Scheduled(fixedDelay = 5000 , initialDelay = 10000)
    public void fixedDelay() {
        //به شمارنده اضافه میکنیم
        this.fixedDelayCount.incrementAndGet();

        //نمایش نام نخ فعلی
        System.out.println("fixedDelay Thread.currentThread().getName():" + Thread.currentThread().getName()+ " CurrentTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
    }

    /**
     * متد زمان بندی که هر روز از ساعت 9 تا 9:59 هر دقیقه فراخوانی میشود.
     */
    @Scheduled(cron = "0 * 9 * * ?")
    public void cronJobSch() {
        //به شمارنده اضافه میکنیم
        this.cronJobSchCount.incrementAndGet();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        Date now = new Date();
        String strDate = sdf.format(now);
        System.out.println("Java cron job expression:: " + strDate);
    }
}
