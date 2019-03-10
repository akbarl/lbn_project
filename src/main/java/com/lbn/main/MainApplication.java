package com.lbn.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.lbn.repository.UserRepository;

@SpringBootApplication
@ComponentScan(basePackages = {
		"com.lbn.config",
        "com.lbn.controller",
        "com.lbn.repository",
        "com.lbn.service",
        "com.lbn.service.impl"})
@EnableJpaRepositories(basePackageClasses= {UserRepository.class})
@EntityScan("com.lbn.domain")
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}

