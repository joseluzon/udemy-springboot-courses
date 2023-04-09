package com.github.joseluzon.udemy.springframework5.rest.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.joseluzon.udemy.springframework5.rest.repositories.UsersRepository;

@Service
public class UsersService {
    
    private UsersRepository usersRepository;

    @Autowired
    public UsersService(final UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
}
