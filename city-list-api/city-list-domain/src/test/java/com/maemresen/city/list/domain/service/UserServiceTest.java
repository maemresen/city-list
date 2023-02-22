package com.maemresen.city.list.domain.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

	@Test
	void getSelf() {
		String x = "emre";
		Assertions.assertEquals("emre2", x);
	}
}
