package com.scm.scm20;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.scm20.services.EmailService;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailService service;

	@Test
	void MailSenderTest() {
		service.sendEmail("sonawanenitin726@gmail.com", "Just Testing email", "This is testing email service");
	}
}
