package com.depromeet.oversweet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class OversweetApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(OversweetApiApplication.class, args);
    }

}
