package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.repositories;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dao.ContactDao;

@Repository
public interface ContactRepository extends JpaRepository<ContactDao, UUID>{
    
}
