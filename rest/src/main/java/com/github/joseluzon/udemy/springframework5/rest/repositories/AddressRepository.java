package com.github.joseluzon.udemy.springframework5.rest.repositories;

import org.springframework.data.repository.CrudRepository;
import com.github.joseluzon.udemy.springframework5.rest.entities.Address;

public interface AddressRepository extends CrudRepository<Address, Integer> {
    
}
