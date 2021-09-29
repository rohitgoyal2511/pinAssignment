package com.assignment.decomodigitial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DecomoDigitialApplication {

    public static void main(String[] args) {
        SpringApplication.run(DecomoDigitialApplication.class, args);
    }

}
