package ir.micser.login.presentation.notify;

import io.leangen.graphql.annotations.GraphQLSubscription;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
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
        AtomicInteger count = new AtomicInteger(0);
        return Flux.interval(Duration.ofSeconds(2)) .map(num -> new NotifyModel(count.incrementAndGet()));
    }
}
