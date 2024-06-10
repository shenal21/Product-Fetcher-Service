package com.example.productfetcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProductFetcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductFetcherApplication.class, args);
    }

}
