package com.sample.timeline_service.timeline_service;

import org.springframework.boot.SpringApplication;

import com.sample.timeline_service.TimelineServiceApplication;

public class TestTimelineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.from(TimelineServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
