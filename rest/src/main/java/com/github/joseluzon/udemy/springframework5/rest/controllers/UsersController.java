package com.github.joseluzon.udemy.springframework5.rest.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;
import com.github.joseluzon.udemy.springframework5.rest.services.UsersService;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    private UsersService usersService;

    @Autowired
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    @Timed("users.getUsers")
    @GetMapping
    public ResponseEntity<Page<User>> getUsers(
        @RequestParam(required = false, value = "page", defaultValue = "0") final int page,
        @RequestParam(required = false, value = "size", defaultValue = "100") final int size) {
        return new ResponseEntity<>(usersService.getUsers(page, size), HttpStatus.OK);
    }

    @GetMapping("/usernames")
    public ResponseEntity<Page<String>> getUsernames(
        @RequestParam(required = false, value = "page", defaultValue = "0") final int page,
        @RequestParam(required = false, value = "size", defaultValue = "100") final int size) {
        return new ResponseEntity<>(usersService.getUsernames(page, size), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    @Operation(method = "GET", summary = "Returns a user for the given user id", responses = {
        @ApiResponse(responseCode = "200", description = "The user with user id given was found"),
        @ApiResponse(responseCode = "404", description = "The user with user id given was NOT found", content = @Content)
    })
    public ResponseEntity<User> getUserById(@PathVariable("userId") final Integer userId) {
        return new ResponseEntity<>(usersService.getUserById(userId), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") final String username) {
        return new ResponseEntity<>(usersService.getUserByUsername(username), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<User> authenticate(@RequestBody final User user) {
        return new ResponseEntity<>(usersService.getUserByUsernameAndPassword(user.getUsername(), user.getPassword()), HttpStatus.OK);
    }
}
