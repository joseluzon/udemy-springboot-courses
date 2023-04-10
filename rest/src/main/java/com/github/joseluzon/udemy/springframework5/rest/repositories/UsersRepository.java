package com.github.joseluzon.udemy.springframework5.rest.repositories;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.github.joseluzon.udemy.springframework5.rest.entities.User;

public interface UsersRepository extends JpaRepository<User, Integer> {
    
    public Optional<User> findByUsername(final String username);

    public Optional<User> findByUsernameAndPassword(final String username, final String password);

    /*
     * NOT SQL --> JPQL : Java Persistence Query Language
     */
    @Query("SELECT u.username FROM User u")
    public Page<String> findUsernames(final Pageable pageable);
}
