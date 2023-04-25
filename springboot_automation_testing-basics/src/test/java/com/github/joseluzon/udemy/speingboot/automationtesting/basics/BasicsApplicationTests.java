package com.github.joseluzon.udemy.speingboot.automationtesting.basics;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasicsApplicationTests {

	@Autowired
	private WebDriver webDriver;

	@Test
	void contextLoads() {
		webDriver.navigate().to("");
	}

}
