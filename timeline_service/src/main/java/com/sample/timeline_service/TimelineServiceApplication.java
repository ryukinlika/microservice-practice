package com.sample.timeline_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.sample.timeline_service.controller.client")
public class TimelineServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(TimelineServiceApplication.class, args);
	}

}
