package org.mtech;

import org.mtech.infrastructure.config.PlaysiteConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Random;

@SpringBootApplication
@EnableConfigurationProperties(PlaysiteConfig.class)
@EnableJpaRepositories(basePackages = "org.mtech.infrastructure.adapter.outbound.database.repository")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Random random() {
        return new Random();
    }

}