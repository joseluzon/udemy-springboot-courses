package com.github.joseluzon.udemy.springframework5.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springframework5.rest.model.User;
import com.github.joseluzon.udemy.springframework5.rest.services.UsersServiceLists;

@RestController
@RequestMapping("/users-list")
public class UsersControllerLists {
    
    private final UsersServiceLists usersService;

    @Autowired
    public UsersControllerLists(final UsersServiceLists usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(value = "startsWith", required = false) final String startsWith) {
        return new ResponseEntity<>(usersService.getUsers(startsWith), HttpStatus.OK);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") final String username) {
        return new ResponseEntity<>(usersService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final User user) {
        return new ResponseEntity<>(usersService.createUser(user), HttpStatus.OK);
    }

    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable("username") final String username, @RequestBody final User user) {
        return new ResponseEntity<>(usersService.updateUser(user, username), HttpStatus.OK);
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable("username") final String username) {
        usersService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
