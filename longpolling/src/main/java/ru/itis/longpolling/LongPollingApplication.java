package ru.itis.longpolling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;



@SpringBootApplication
public class LongPollingApplication {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication.run(LongPollingApplication.class, args);
    }


    public static ConfigurableApplicationContext getContext() {
        return context;
    }
}
