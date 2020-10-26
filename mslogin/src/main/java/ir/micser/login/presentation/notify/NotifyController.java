package ir.micser.login.presentation.notify;

import com.motaharinia.msutility.customexception.BusinessException;
import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import ir.micser.login.business.service.BusinessExceptionEnum;
import ir.micser.login.business.service.loguploadedfile.LogUploadedFileServiceImpl;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@GraphQLApi
public class NotifyController {
    @GraphQLSubscription(name = "briefMesseages")
    public Publisher<NotifyModel> briefMesseages(Integer userId) {
        if(userId != 2){
            throw new BusinessException(LogUploadedFileServiceImpl.class, BusinessExceptionEnum.INVALID_ID, "userId:" + userId);
        }
        AtomicInteger count = new AtomicInteger(0);
        return Flux.interval(Duration.ofSeconds(2)) .map(num -> new NotifyModel(count.incrementAndGet()));
    }
}
