package com.example.proedax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.example.proedax", "com.example.controller"})
public class ProEdaxApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProEdaxApplication.class, args);
    }
}
