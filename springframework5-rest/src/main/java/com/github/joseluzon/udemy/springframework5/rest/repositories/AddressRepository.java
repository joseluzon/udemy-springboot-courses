package com.github.joseluzon.udemy.springframework5.rest.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.github.joseluzon.udemy.springframework5.rest.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    
    @Query("SELECT a FROM Address a WHERE a.profile.user.id = ?1 AND a.profile.id = ?2")
    public List<Address> findByUserIdAndProfileId(final Integer userId, final Integer profileId);
}
