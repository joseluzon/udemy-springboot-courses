package com.github.joseluzon.udemy.springframework5.rest.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
    
    public Optional<User> findByUsername(final String username);

    public Optional<User> findByUsernameAndPassword(final String username, final String password);
}
