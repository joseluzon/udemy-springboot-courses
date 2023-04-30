package com.github.joseluzon.udemy.springboot.automationtesting.microservices;

import java.util.Random;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Address;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Employee;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.repositories.AddressRepository;
import com.github.joseluzon.udemy.springboot.automationtesting.microservices.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor // Ctor. is auto @Autowired
public class HrmsApplication implements ApplicationRunner {

    private final Faker faker;
    private final EmployeeRepository employeeRepository;
    private final AddressRepository addressRepository;


    public static void main(String[] args) {
        SpringApplication.run(HrmsApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        IntStream.range(0, 3).forEach(i -> {
            final var address = Address.builder()
                .country(faker.address().country())
                .city(faker.address().city())
                .street(faker.address().streetAddress()).build();
            final var savedAddress = addressRepository.save(address);
            log.info("Address created : {}", savedAddress);
        });
        IntStream.range(0, 10).forEach(i -> {
            final var address = addressRepository.findById(new Random().nextInt(1, 4));
            final var employee = Employee.builder()
                .name(faker.harryPotter().character())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().phoneNumber())
                .address(address.get()).build();
            final var savedEmployee = employeeRepository.save(employee);
            log.info("Employee created : {}", savedEmployee);
        });
    }

}
