package com.github.joseluzon.udemy.springframework5.rest;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springframework5.rest.entities.Role;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.entities.UserInRole;
import com.github.joseluzon.udemy.springframework5.rest.repositories.RolesRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersInRolesRepository;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class RestApplication implements ApplicationRunner {

    private Faker faker;
    private UsersRepository userRepository;
    private RolesRepository rolesRepository;
    private UsersInRolesRepository usersInRolesRepository;
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @Autowired
    public RestApplication(final Faker faker, final UsersRepository userRepository, final RolesRepository rolesRepository, final UsersInRolesRepository usersInRolesRepository) {
        this.faker = faker;
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.usersInRolesRepository = usersInRolesRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        final var roles = new Role[] { new Role("ADMIN"), new Role("USER"), new Role("VIEWER") };
        Arrays.stream(roles).forEach(role -> {
            rolesRepository.save(role);
        });
        IntStream.range(0, 10).forEach(i -> {
            final var user = new User();
            user.setUsername(faker.name().username());
            user.setPassword(faker.dragonBall().character());
            final var actualUser = userRepository.save(user);
            final var roleSelected = roles[new Random().nextInt(roles.length)];
            usersInRolesRepository.save(new UserInRole(actualUser, roleSelected));
            log.info("User created : username: {} | password: {} | role: {}", actualUser.getUsername(), actualUser.getPassword(), roleSelected.getName());
        });
    }

}
