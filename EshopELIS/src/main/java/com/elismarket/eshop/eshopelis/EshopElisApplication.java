package com.elismarket.eshop.eshopelis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.elismarket.eshop.*"})
public class EshopElisApplication {

    public static void main(String[] args) {
        SpringApplication.run(EshopElisApplication.class, args);
    }

}
