package com.sample.timeline_service.timeline_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class TimelineServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
