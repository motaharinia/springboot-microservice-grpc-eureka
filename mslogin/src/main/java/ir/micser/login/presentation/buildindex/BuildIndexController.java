package ir.micser.login.presentation.buildindex;


import com.motaharinia.msutility.json.PrimitiveResponse;
import ir.micser.login.business.service.hibernatesearch.HibernateSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class BuildIndexController {

    private final HibernateSearchService hibernateSearchService;

    @Autowired
    public BuildIndexController(HibernateSearchService hibernateSearchService) {
        this.hibernateSearchService = hibernateSearchService;
    }

    /**
     * این متد ستی از نام انتیتی ها دریافت میکند و ایندکس آنها را ایجاد میکند اگر ست خالی ارسال شود کل انتیتیها را ایندکس میکند
     * @param entitySet ستی از کلاس هایی که میخواهیم ایندکس شوند
     * @return خروجی: مقدار true برمیگرداند
     * @throws Exception این متد ممکن است اکسپشن صادر کند
     */
    @PutMapping(value = "/v1/buildIndexRun")
    public @ResponseBody  PrimitiveResponse buildIndex(@RequestBody Set<String> entitySet) throws Exception {

        return hibernateSearchService.rebuildIndexer(entitySet);
    }
}
