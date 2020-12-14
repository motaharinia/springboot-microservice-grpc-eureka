package ir.micser.login.presentation.async;


import com.motaharinia.msutility.json.PrimitiveResponse;
import ir.micser.login.business.service.async.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 13:41:19<br>
 * Description:<br>
 * کلاس کنترلر ناهمزمانی
 */
@RestController
public class AsyncController {


    @Autowired
    private CountDownLatch countDownLatch;

    /**
     * سرویس کارمند
     */
    @Autowired
    private AsyncService asyncService;

    @GetMapping(value = "/v1/async/block")
    public PrimitiveResponse testAsyncBlock() throws InterruptedException, ExecutionException {

        //سرویس های آدرس و تلفن و کارمند به دلیل اینکه آسینک هستند هم زمان اجرا میشوند
        CompletableFuture<Async1Model> async1CompletableFuture = asyncService.getModel1(countDownLatch);
        CompletableFuture<Async2Model> async2CompletableFuture = asyncService.getModel2(countDownLatch);
        CompletableFuture<Async3Model> async3CompletableFuture = asyncService.getModel3(countDownLatch,false, "employeeAccount has Exception");

        // Wait until they are all done
        //یک CompletableFuture جدید برمی گرداند که با تمام شدن CompleteableFutureهای داده شده کامل میشود.
        CompletableFuture completableFuture = CompletableFuture.allOf(async1CompletableFuture, async2CompletableFuture, async3CompletableFuture);
        // ترد جاری را تا زمانیکه اجرای ترد خودش را به اتمام برساند ، بلوکه میکند
        completableFuture.get();

        //اگر ترد با موفقیت تمام شود true خروجی میدهد در غیر اینصورت fasle خروجی میدهد
        return new PrimitiveResponse(completableFuture.isDone());

    }

    @GetMapping(value = "/v1/async/thenApply")
    public PrimitiveResponse testAsyncThenApply() throws InterruptedException {

        //سرویس های متفاوت به دلیل اینکه آسینک هستند هم زمان اجرا میشوند
        CompletableFuture<Async1Model> async1CompletableFuture = asyncService.getModel1(countDownLatch);
        CompletableFuture<Async2Model> async2CompletableFuture = asyncService.getModel2(countDownLatch);
        CompletableFuture<Async3Model> async3CompletableFuture = asyncService.getModel3(countDownLatch,false, "employeeAccount has Exception");

        // Wait until they are all done
        //یک CompletableFuture جدید برمی گرداند که با تمام شدن CompleteableFutureهای داده شده کامل میشود.
        CompletableFuture completableFuture = CompletableFuture.allOf(async1CompletableFuture, async2CompletableFuture, async3CompletableFuture);

        //بعد ار اینکه allOf اجرا شود و خطا ندهد ، کدهای نوشته شده در thenApply اجرا میشود
        completableFuture.thenApply(cf -> {
            Async1Model async1Model = async1CompletableFuture.join();
            Async2Model async2Model = async2CompletableFuture.join();
            Async3Model async3Model = async3CompletableFuture.join();
            return new PrimitiveResponse(true);
        });


        //به دلیل اینکه ما با دستور join , get ترد جاری را بلوکه نکرده ایم ، متد کنترلر زودتر از متدهای Async تمام میشود و ترو ریترن میشود
        //برای اینکه تست کنیم که متدهای Async بصورت موازی اجرا میشوند باید در متد تست یک انتظار ایجاد کنیم
        // که این کار را توسط countDownLatch.await انجام میدهیم

        //متد await تا وقتیکه count==0 شود ترد جاری را بلوک میکند
        //countDownLatch.await();
        //متد await به مدت 3000میلی ثانیه ترد جاری را بلوک میکند
        //چون در متدهای آسینک برای همه شان 2000میلی ثانیه sleep را ست کرده ایم بنابراین انتظار داریم که زیر 3000میلی ثانیه اجرای تردها تمام شوند
        countDownLatch.await(5000, TimeUnit.MILLISECONDS);


        return new PrimitiveResponse(countDownLatch.getCount());
    }

}
