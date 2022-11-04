package com.acme.gym4u;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Gym4UApplication {

    public static void main(String[] args) {
        SpringApplication.run(Gym4UApplication.class, args);
    }

}
