package ir.micser.config.async;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;

/**
 * User: https://github.com/motaharinia<br>
 * Date: 2020-09-02<br>
 * Time: 12:02:24<br>
 * Description:<br>
 * کلاس تنظیمات ناهمزمانی در پروژه
 */
@Configuration
@EnableAsync
public class AsyncConfiguration implements AsyncConfigurer {

    /**
     * شمارنده نزولی برای متد تست
     */
    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(3);
    }

    /**
     * تنظیم یک اجراکننده استخر نخ های نا همزمان
     *
     * @return خروجی:  اجراکننده استخر نخ های نا همزمان
     */
    @Bean(name = "asyncExecutor1")
    public ThreadPoolTaskExecutor asyncExecutor1() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("AsynchThreadExecutor1-");
        executor.initialize();
        return executor;
    }

    /**
     * تنظیم یک اجراکننده استخر نخ های نا همزمان
     *
     * @return خروجی:  اجراکننده استخر نخ های نا همزمان
     */
    @Bean(name = "asyncExecutor2")
    public ThreadPoolTaskExecutor asyncExecutor2() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(12);
        executor.setMaxPoolSize(15);
        executor.setQueueCapacity(50);
        executor.setThreadNamePrefix("AsynchThreadExecutor2-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncCustomUncaughtExceptionHandler();
    }
}
