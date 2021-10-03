package com.company.my_web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan
public class MyWebApplication {

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(MyWebApplication.class, args);
	}

}
