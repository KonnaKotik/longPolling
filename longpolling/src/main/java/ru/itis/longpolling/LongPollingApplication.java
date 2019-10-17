package ru.itis.longpolling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;


@EnableJpaRepositories(basePackages = {"ru.itis.longpolling.repository"})
@EntityScan(basePackages = {"ru.itis.longooling.model"})
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class LongPollingApplication {

    public static void main(String[] args) {
        SpringApplication.run(LongPollingApplication.class, args);
    }

}
