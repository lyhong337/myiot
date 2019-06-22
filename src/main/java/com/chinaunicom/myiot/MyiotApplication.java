package com.chinaunicom.myiot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MyiotApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyiotApplication.class, args);
    }

}
