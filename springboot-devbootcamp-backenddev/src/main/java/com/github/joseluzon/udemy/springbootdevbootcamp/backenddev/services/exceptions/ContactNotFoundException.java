package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.services.exceptions;

import java.util.UUID;

public class ContactNotFoundException extends RuntimeException {    
    public ContactNotFoundException(final UUID id) {
        super(String.format("contact with uuid %s not found", id.toString()));
    }
}
