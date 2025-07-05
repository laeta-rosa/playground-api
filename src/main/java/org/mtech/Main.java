package org.mtech;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "org.mtech.adapter.outbound.database.repository")
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}