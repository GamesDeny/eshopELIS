package com.elismarket.eshop.eshopelis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.elismarket.eshop.businesslogic"})
@EntityScan(basePackages = {"com.elismarket.eshop.model"})
public class EshopElisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopElisApplication.class, args);
    }

}
