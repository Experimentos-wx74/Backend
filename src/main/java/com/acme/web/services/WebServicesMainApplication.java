package com.acme.web.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class WebServicesMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServicesMainApplication.class, args);
    }
}

