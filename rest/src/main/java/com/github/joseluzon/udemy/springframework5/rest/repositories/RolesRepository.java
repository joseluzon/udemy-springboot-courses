package com.github.joseluzon.udemy.springframework5.rest.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.github.joseluzon.udemy.springframework5.rest.entities.Role;

public interface RolesRepository extends JpaRepository<Role, Integer> {
    
}
