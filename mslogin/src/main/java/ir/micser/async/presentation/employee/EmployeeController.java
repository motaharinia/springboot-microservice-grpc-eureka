package ir.micser.async.presentation.employee;


import com.motaharinia.msutility.json.PrimitiveResponse;
import ir.micser.async.business.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-07-22<br>
 * Time: 13:41:19<br>
 * Description:<br>
 * کلاس کنترلر کارمند
 */
@RestController
public class EmployeeController {


    /**
     * سرویس کارمند
     */
    @Autowired
    private EmployeeService service;

    @GetMapping(value = "/v1/employee/async/block")
    public PrimitiveResponse testAsyncBlock() throws InterruptedException, ExecutionException {

        //سرویس های آدرس و تلفن و کارمند به دلیل اینکه آسینک هستند هم زمان اجرا میشوند
        CompletableFuture<EmployeeModel> employeeCompletableFuture = service.getEmployee();
        CompletableFuture<EmployeeContactModel> employeeContactCompletableFuture = service.getEmployeeContact();
        CompletableFuture<EmployeeAccountModel> employeeAccountCompletableFuture = service.getEmployeeAccount(false, "employeeAccount has Exception");

        // Wait until they are all done
        //یک CompletableFuture جدید برمی گرداند که با تمام شدن CompleteableFutureهای داده شده کامل میشود.
        CompletableFuture completableFuture = CompletableFuture.allOf(employeeCompletableFuture, employeeContactCompletableFuture, employeeAccountCompletableFuture);
        // ترد جاری را تا زمانیکه اجرای ترد خودش را به اتمام برساند ، بلوکه میکند
        completableFuture.get();

        //اگر ترد با موفقیت تمام شود true خروجی میدهد در غیر اینصورت fasle خروجی میدهد
        return new PrimitiveResponse(completableFuture.isDone());

    }

    @GetMapping(value = "/v1/employee/async/thenApply")
    public PrimitiveResponse testAsyncThenApply() throws InterruptedException, ExecutionException {

        //سرویس های آدرس و تلفن و کارمند به دلیل اینکه آسینک هستند هم زمان اجرا میشوند
        CompletableFuture<EmployeeModel> employeeCompletableFuture = service.getEmployee();
        CompletableFuture<EmployeeContactModel> employeeContactCompletableFuture = service.getEmployeeContact();
        CompletableFuture<EmployeeAccountModel> employeeAccountCompletableFuture = service.getEmployeeAccount(false, "employeeAccount has Exception");

        // Wait until they are all done
        //یک CompletableFuture جدید برمی گرداند که با تمام شدن CompleteableFutureهای داده شده کامل میشود.
        CompletableFuture completableFuture = CompletableFuture.allOf(employeeCompletableFuture, employeeContactCompletableFuture, employeeAccountCompletableFuture);

        //بعد ار اینکه allOf اجرا شود و خطا ندهد ، کدهای نوشته شده در thenApply اجرا میشود
        completableFuture.thenApply(cf -> {
            EmployeeModel employeeModel= employeeCompletableFuture.join();
            EmployeeContactModel employeeContactModel= employeeContactCompletableFuture.join();
            EmployeeAccountModel employeeAccountModel= employeeAccountCompletableFuture.join();
            return new PrimitiveResponse(true);
        });

        return new PrimitiveResponse(true);
    }

}
