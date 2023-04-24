package com.github.joseluzon.udemy.springframework5.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UsersService {
    
    private UsersRepository usersRepository;

    @Autowired
    public UsersService(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public Page<User> getUsers(final int page, final int size) {
        return usersRepository.findAll(PageRequest.of(page, size));
    }

    public Page<String> getUsernames(final int page, final int size) {
        return usersRepository.findUsernames(PageRequest.of(page, size));
    }

    public User getUserById(final Integer userId) {
        return usersRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("User id %d not found", userId)));
    }

    @Cacheable(value = "users") // In a real application, this method is not suitable for being cacheable, since a User can change over time.
    public User getUserByUsername(final String username) {
        log.info("getUserByUsername( {} )", username);
        // simulate heavy process
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return usersRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username %s not found", username)));
    }

    public User getUserByUsernameAndPassword(final String username, final String password) {
        return usersRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username/Password %s/%s not found", username, password)));
    }

    @CacheEvict("users")
    public void deleteUser(final String username) {
        final var user = getUserByUsername(username);
        usersRepository.deleteById(user.getId());
    }
}
