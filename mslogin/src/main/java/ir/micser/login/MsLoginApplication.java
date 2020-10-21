package ir.micser.login;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;


@SpringBootApplication(scanBasePackages = {"ir.micser"})
@EnableJpaRepositories(repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
@EnableTransactionManagement
@EnableEurekaClient
@EnableScheduling
@EnableAsync
public class MsLoginApplication {
//
//    @Value("${custom.default.timezone}")
//    private String defaultTimezone;
//
//    @PostConstruct
//    public void init(){
//        System.out.println("defaultTimezone:"+defaultTimezone);
//        TimeZone.setDefault(TimeZone.getTimeZone(defaultTimezone));
//    }


//    @Autowired
//    AuthorizationService authorizationService;


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }




    public static void main(String[] args) {
        SpringApplication.run(MsLoginApplication.class, args);
        System.out.println("MsApplication main after SpringApplication.run");
    }

}
