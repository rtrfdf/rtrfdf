package com.evan.lycxm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class LycxmApplication {

    public static void main(String[] args) {
        SpringApplication.run(LycxmApplication.class, args);
    }

}
