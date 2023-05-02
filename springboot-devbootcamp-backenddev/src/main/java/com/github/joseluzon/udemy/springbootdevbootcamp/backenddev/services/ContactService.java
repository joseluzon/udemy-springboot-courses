package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.services;

import java.util.List;
import java.util.UUID;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dto.ContactDto;

public interface ContactService {

    ContactDto findById(UUID id);

    ContactDto createContact(ContactDto contact);

    List<ContactDto> findAll();

    ContactDto updateContact(UUID id, ContactDto contact);

    void deleteContact(UUID id);
    
}
