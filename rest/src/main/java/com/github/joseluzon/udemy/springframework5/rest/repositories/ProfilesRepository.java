package com.github.joseluzon.udemy.springframework5.rest.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.github.joseluzon.udemy.springframework5.rest.entities.Profile;

public interface ProfilesRepository extends CrudRepository<Profile, Integer> {

    @Query("SELECT p FROM Profile p WHERE p.id=?2 AND p.user.id=?1")
    public Optional<Profile> findByUserIdAndProfileId(Integer userId, Integer profileId);
    
}
