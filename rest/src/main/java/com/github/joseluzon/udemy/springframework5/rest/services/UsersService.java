package com.github.joseluzon.udemy.springframework5.rest.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;

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

    public User getUserByUsername(final String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username %s not found", username)));
    }

    public User getUserByUsernameAndPassword(final String username, final String password) {
        return usersRepository.findByUsernameAndPassword(username, password).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Username/Password %s/%s not found", username, password)));
    }
}
