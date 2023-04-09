package com.github.joseluzon.udemy.springframework5.rest.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.joseluzon.udemy.springframework5.rest.services.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
    
    private UsersService usersService;

    @Autowired
    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }
}
