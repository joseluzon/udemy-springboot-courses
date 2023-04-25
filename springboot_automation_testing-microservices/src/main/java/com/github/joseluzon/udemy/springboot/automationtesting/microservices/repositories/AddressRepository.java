package com.github.joseluzon.udemy.springboot.automationtesting.microservices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.github.joseluzon.udemy.springboot.automationtesting.microservices.models.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    
}
