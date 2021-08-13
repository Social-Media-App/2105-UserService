package org;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import rev.UserMicroserviceApplication;

@SpringBootTest( classes = UserMicroserviceApplication.class) // we need to tell it which class we are looking to test

class UserMicroserviceApplicationTests {

	@Test
	void contextLoads() {
	}
}
