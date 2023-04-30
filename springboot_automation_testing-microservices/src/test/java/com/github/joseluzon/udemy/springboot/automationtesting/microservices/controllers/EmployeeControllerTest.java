package com.github.joseluzon.udemy.springboot.automationtesting.microservices.controllers;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Address;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Employee;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.repositories.AddressRepository;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.repositories.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @LocalServerPort
    private int port;

    private Faker faker = new Faker();

    private final List<Employee> employees = new ArrayList<>();

    private final List<Address> addresses = new ArrayList<>();

    @BeforeEach
    public void beforeTestClass() {
        IntStream.range(0, 3).forEach(i -> {
            final var address = Address.builder().country(faker.address().country())
                    .city(faker.address().city()).street(faker.address().streetAddress()).build();
            final var savedAddress = addressRepository.save(address);
            addresses.add(savedAddress);
            log.info("Address created : {}", savedAddress);
        });
        IntStream.range(0, 10).forEach(i -> {
            final var address = addressRepository.findById(new Random().nextInt(1, 4));
            final var employee = Employee.builder().name(faker.harryPotter().character())
                    .email(faker.internet().emailAddress()).phone(faker.phoneNumber().phoneNumber())
                    .address(address.get()).build();
            final var savedEmployee = employeeRepository.save(employee);
            employees.add(savedEmployee);
            log.info("Employee created : {}", savedEmployee);
        });
    }

    @Test
    void testCreateEmployee() {
        final var address = Address.builder().country(faker.address().country())
                .city(faker.address().city()).street(faker.address().streetAddress()).build();
        final var savedAddress = addressRepository.save(address);

        final var employee = Employee.builder().id(employees.size() + 1)
                .name(faker.harryPotter().character()).email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber()).address(savedAddress).build();

        final var baseUrl = String.format("http://localhost:%d/employee", port);
        final var employeeCreated =
                testRestTemplate.postForObject(baseUrl, employee, Employee.class);
        assertEquals(employee, employeeCreated);
    }

    @Test
    void testRestAssuredCreateEmployee() {
        final var address = Address.builder().country(faker.address().country())
                .city(faker.address().city()).street(faker.address().streetAddress()).build();
        final var savedAddress = addressRepository.save(address);

        final var employee = Employee.builder().id(employees.size() + 1)
                .name(faker.harryPotter().character()).email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber()).address(savedAddress).build();

        final var employeeCreated = given().baseUri("http://localhost").port(port)
                .basePath("/employee").contentType("application/json").body(employee).post().body()
                .as(Employee.class);

        assertEquals(employee, employeeCreated);
    }

    @Test
    void testDeleteEmployee() {
        final var endPointPattern = "http://localhost:%d/employee/%d";
        employees.stream().forEach(e -> {
            final var baseUrl = String.format(endPointPattern, port, e.getId());
            testRestTemplate.delete(baseUrl);
            final var employee = testRestTemplate.getForObject(baseUrl, Employee.class);
            assertEquals(0, employee.getId()); // returned a dummy Employee
        });
    }

    @Test
    void testGetEmployee() {
        final var endPointPattern = "http://localhost:%d/employee/%d";
        employees.stream().forEach(e -> {
            final var baseUrl = String.format(endPointPattern, port, e.getId());
            final var employee = testRestTemplate.getForObject(baseUrl, Employee.class);
            assertEquals(e, employee);
        });
    }

    @Test
    void testRestAssuredGetEmployee() {
        employees.stream().forEach(e -> {
            final var employee = given().baseUri("http://localhost").port(port)
                    .basePath(String.format("/employee/%d", e.getId())).get().body().as(Employee.class);
            assertEquals(e, employee);
        });
    }

    @Test
    void testRestAssuredGetEmployeeBis() {
        employees.stream().forEach(e -> {
            given().baseUri("http://localhost").port(port)
                    .basePath(String.format("/employee/%d", e.getId())).get().then().assertThat()
                    .body("id", Matchers.equalTo(e.getId()));
        });
    }

    @Test
    void testGetEmployees() {
        final var baseUrl = String.format("http://localhost:%d/employee/all", port);
        final var employeesGot = testRestTemplate.getForObject(baseUrl, Employee[].class);
        assertTrue(Arrays.stream(employeesGot).toList().containsAll(employees));
    }

    @Test
    void testRestAssuredGetEmployees() {
        final var employeesGot = given().baseUri("http://localhost").port(port)
                .basePath("/employee/all").get().as(Employee[].class);
        assertTrue(Arrays.stream(employeesGot).toList().containsAll(employees));
    }

    @Test
    void testUpdateEmployee() {
        final var endPointPattern = "http://localhost:%d/employee/%d";
        employees.stream().forEach(e -> {
            final var baseUrl = String.format(endPointPattern, port, e.getId());
            final var employee = testRestTemplate.getForObject(baseUrl, Employee.class);
            employee.setPhone(faker.phoneNumber().phoneNumber());
            testRestTemplate.put(baseUrl, employee);
            final var employeeUpdated = testRestTemplate.getForObject(baseUrl, Employee.class);
            assertEquals(employee, employeeUpdated);
            assertEquals(employee.getPhone(), employeeUpdated.getPhone());
        });
    }

    @Test
    void testRestAssuredUpdateEmployee() {
        employees.stream().forEach(e -> {
            final var employee = given().baseUri("http://localhost").port(port)
                    .basePath(String.format("/employee/%d", e.getId())).get().body().as(Employee.class);
            employee.setPhone(faker.phoneNumber().phoneNumber());
            employee.getAddress().setCity(faker.address().city());
            final var employeeUpdated = given().baseUri("http://localhost").port(port)
                    .basePath(String.format("/employee/%d", e.getId()))
                    .contentType("application/json").body(employee).put().body().as(Employee.class);
            assertEquals(employee, employeeUpdated);
            assertEquals(employee.getPhone(), employeeUpdated.getPhone());
            assertEquals(employee.getAddress().getCity(), employeeUpdated.getAddress().getCity());
        });
    }
}
