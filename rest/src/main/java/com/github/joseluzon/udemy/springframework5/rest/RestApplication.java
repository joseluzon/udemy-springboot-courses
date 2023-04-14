package com.github.joseluzon.udemy.springframework5.rest;

import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;

@SpringBootApplication
public class RestApplication implements ApplicationRunner {

    private Faker faker;
    private UsersRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Autowired
    public RestApplication(final Faker faker, final UsersRepository userRepository) {
        this.faker = faker;
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        IntStream.range(0, 10).forEach(i -> {
            final var user = new User();
            user.setUsername(faker.name().username());
            user.setPassword(faker.dragonBall().character());
            userRepository.save(user);
        });
    }

}
