package com.elismarket.eshop.eshopelis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.elismarket.eshop.*"})
@EntityScan(basePackages = {"com.elismarket.eshop.model.*"})
@EnableJpaRepositories(basePackages = {"com.elismarket.eshop.businessLogic.*"})
@ComponentScan(basePackages = {"com.elismarket.eshop.businessLogic.*"})
public class EshopElisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopElisApplication.class, args);
    }

}
