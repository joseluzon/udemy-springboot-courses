package com.github.joseluzon.udemy.springboot.automationtesting.microservices;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HrmsApplicationTests {

	@Test
	void contextLoads() {

	}
}
