package com.github.joseluzon.udemy.springboot.automationtesting.microservices.controllers;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Address;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Employee;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.services.EmployeeService;

@WebMvcTest(EmployeeController.class)
public class EmployeeControllerMockMvcTest {

    private Faker faker = new Faker();
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployee() {
        final var address = Address.builder().country(faker.address().country())
                    .city(faker.address().city()).street(faker.address().streetAddress()).build();
        final var employee = Employee.builder().name(faker.harryPotter().character())
                    .email(faker.internet().emailAddress()).phone(faker.phoneNumber().phoneNumber())
                    .address(address).build();
        // setup mock behaviour
        when(employeeService.findById(1)).thenReturn(employee);

        assertDoesNotThrow(() -> mockMvc.perform(MockMvcRequestBuilders.get("employee/1")).andExpect(MockMvcResultMatchers.status().isOk()));
    }

}
