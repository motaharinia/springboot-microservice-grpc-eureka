package ir.micser.geo;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;


@SpringBootApplication(scanBasePackages = {"ir.micser"})
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
@EnableTransactionManagement
@EnableEurekaClient
public class MsGeoApplication {
//
//    @Value("${custom.default.timezone}")
//    private String defaultTimezone;
//
//    @PostConstruct
//    public void init(){
//        System.out.println("defaultTimezone:"+defaultTimezone);
//        TimeZone.setDefault(TimeZone.getTimeZone(defaultTimezone));
//    }




    public static void main(String[] args) {
        SpringApplication.run(MsGeoApplication.class, args);
    }

}
