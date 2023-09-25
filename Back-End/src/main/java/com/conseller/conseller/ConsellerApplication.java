package com.conseller.conseller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ConsellerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsellerApplication.class, args);
    }
}
