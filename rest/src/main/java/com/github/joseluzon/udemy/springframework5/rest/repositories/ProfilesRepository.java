package com.github.joseluzon.udemy.springframework5.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import com.github.joseluzon.udemy.springframework5.rest.entities.Profile;

public interface ProfilesRepository extends CrudRepository<Profile, Integer> {
    
}
