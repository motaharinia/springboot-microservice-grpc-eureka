package ir.micser.login.business.service.async;


import ir.micser.login.presentation.async.Async3Model;
import ir.micser.login.presentation.async.Async2Model;
import ir.micser.login.presentation.async.Async1Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;


/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-02<br>
 * Time: 11:52:12<br>
 * Description:<br>
 * کلاس سرویس نا همزمانی
 */
@Service
public class AsyncService {


    /**
     * شمارنده نزولی برای متد تست
     */
    @Autowired
    private CountDownLatch countDownLatch;


    /**
     * این متد مدل اول را خروجی میدهد
     *
     * @param countDownLatch شمارنده
     * @return خروجی: مدل اول
     * @throws InterruptedException
     */
    @Async("asyncExecutor1")
    public CompletableFuture<Async1Model> getModel1(CountDownLatch countDownLatch) throws InterruptedException {
        //پر کردن اطلاعات مدل
        Async1Model async1Model = new Async1Model();
        async1Model.setFirstName("Mostafa");
        async1Model.setLastName("Motaharinia");

        //ایجاد وقفه 2 ثانیه ای برای متد تست
        Thread.sleep(2000L);

        //متد countDown تعداد را برای متد تست کاهش می دهد
        countDownLatch.countDown();

        //نمایش نام نخ فعلی
        System.out.println("getModel1 Thread.currentThread().getName():" + Thread.currentThread().getName() + " countDownLatch.getCount():" + countDownLatch.getCount());

        return CompletableFuture.completedFuture(async1Model);
    }

    /**
     * این متد مدل دوم را خروجی میدهد
     *
     * @param countDownLatch شمارنده
     * @return خروجی: مدل دوم
     * @throws InterruptedException
     */
    @Async("asyncExecutor1")
    public CompletableFuture<Async2Model> getModel2(CountDownLatch countDownLatch) throws InterruptedException {
        //پر کردن اطلاعات مدل
        Async2Model async2Model = new Async2Model();
        async2Model.setAddress("address1");
        async2Model.setPhone("+989124376251");

        //ایجاد وقفه 2 ثانیه ای برای متد تست
        Thread.sleep(2000L);

        //متد countDown تعداد را برای متد تست کاهش می دهد
        countDownLatch.countDown();

        //نمایش نام نخ فعلی
        System.out.println("getModel2 Thread.currentThread().getName():" + Thread.currentThread().getName() + " countDownLatch.getCount():" + countDownLatch.getCount());

        return CompletableFuture.completedFuture(async2Model);
    }

    /**
     * این متد مدل دوم را خروجی میدهد
     *
     * @param countDownLatch شمارنده
     * @param withException  آیا متد تست نیاز به بروز خطا دارد؟
     * @param message        پیام خطای مورد نیاز متد تست
     * @return خروجی: مدل سوم
     * @throws InterruptedException خطا
     */
    @Async("asyncExecutor1")
    public CompletableFuture<Async3Model> getModel3(CountDownLatch countDownLatch, Boolean withException, String message) throws InterruptedException {
        //پر کردن اطلاعات مدل
        Async3Model async3Model = new Async3Model();
        async3Model.setBankAccountNo("54545-1985458");
        async3Model.setBankName("BMWBank");


        //ایجاد وقفه 2 ثانیه ای برای متد تست
        Thread.sleep(2000L);

        //در صورت دلخواه متد تست یک خطا خروجی میدهیم
        if (withException) {
            throw new InterruptedException("message : " + message);
        }

        //متد countDown تعداد را برای متد تست کاهش می دهد
        countDownLatch.countDown();

        //نمایش نام نخ فعلی
        System.out.println("getModel3 Thread.currentThread().getName():" + Thread.currentThread().getName() + " countDownLatch.getCount():" + countDownLatch.getCount());


        return CompletableFuture.completedFuture(async3Model);
    }

}
