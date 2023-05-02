package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.services;

import java.util.List;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dto.ContactDto;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Profile("fake")
// @ConditionalOnProperty(name = "services.contact", havingValue = "simple")
public class ContactServiceSimple implements ContactService {
    // @Autowired by Ctor.
    private final ContactRepository contactRepository;

    @Override
    public ContactDto findById(final UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Unimplemented method 'findById'");
    }

    @Override
    public ContactDto createContact(final ContactDto contact) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Unimplemented method 'submitContact'");
    }

    @Override
    public List<ContactDto> findAll() {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Unimplemented method 'findAll'");
    }

    @Override
    public ContactDto updateContact(final UUID id, final ContactDto contact) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Unimplemented method 'updateContact'");
    }

    @Override
    public void deleteContact(final UUID id) {
        throw new ResponseStatusException(HttpStatus.NOT_IMPLEMENTED, "Unimplemented method 'deleteContact'");
    }
}
