package com.github.joseluzon.udemy.springframework5.rest.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springframework5.rest.model.User;
import lombok.Getter;

@Service
public class UsersServiceLists {

    private final Faker faker;

    private final List<User> users;

    @Autowired // unnecesarry
    public UsersServiceLists(final Faker faker) {
        this.faker = faker;
        this.users = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        IntStream.range(0, 100).forEach(i -> {
            users.add(User.builder().nickName(faker.funnyName().name())
                    .userName(faker.name().username()).password(faker.dragonBall().character())
                    .build());
        });
    }

    public List<User> getUsers(final String startsWith) {
        if (startsWith == null) {
            return users;
        }
        return users.stream().filter(u -> u.getUserName().startsWith(startsWith)).collect(Collectors.toList());
    }

    public User getUserByUsername(final String username) {
        return users.stream().filter(u -> u.getUserName().equals(username)).findAny()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("User %s not found", username)));
    }

    public User createUser(final User user) {
        if (users.stream().anyMatch(u -> u.getUserName().equals(user.getUserName())))  {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("User % already exists", user.getUserName()));
        }
        users.add(user);
        // final var userCreated = usersRepository.save(user);
        return user;
    }

    public User updateUser(final User user, final String username) {
        final var userToBeUpdated = getUserByUsername(username);
        userToBeUpdated.setNickName(user.getNickName());
        userToBeUpdated.setPassword(user.getPassword());
        // final var userUpdated = usersRepository.update(user);
        return userToBeUpdated;
    }

    public void deleteUser(final String username) {
        final var user = getUserByUsername(username);
        users.remove(user);
    }
}
