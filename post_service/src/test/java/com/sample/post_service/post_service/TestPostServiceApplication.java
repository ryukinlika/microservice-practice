package com.sample.post_service.post_service;

import org.springframework.boot.SpringApplication;

import com.sample.post_service.PostServiceApplication;

public class TestPostServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(PostServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
