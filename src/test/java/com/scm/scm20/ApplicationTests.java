package com.scm.scm20;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private mailService service;

	@Test

	void MailSenderTest() {
		service.sendMail("sonawanenitin726@gmail.com", "Just Testing email", "This is testing email service");
	}
}
