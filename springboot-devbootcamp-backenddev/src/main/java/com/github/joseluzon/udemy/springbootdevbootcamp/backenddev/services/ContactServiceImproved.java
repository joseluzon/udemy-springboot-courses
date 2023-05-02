package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.services;

import java.util.List;
import java.util.UUID;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dto.ContactDto;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.mappers.ContactMapper;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.repositories.ContactRepository;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.services.exceptions.ContactNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Profile("!fake")
// @ConditionalOnProperty(name = "services.contact", havingValue = "improved")
public class ContactServiceImproved implements ContactService {
    // @Autowired by Ctor.
    private final ContactRepository contactRepository;
    // @Autowired by Ctor.
    private final ContactMapper contactMapper;

    @Override
    public ContactDto findById(final UUID id) {
        final var contactFound = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        return contactMapper.daoToDto(contactFound);
    }

    @Override
    public ContactDto createContact(final ContactDto contact) {
        return contactMapper.daoToDto(contactRepository.save(contactMapper.dtoTodDao(contact)));
    }

    @Override
    public List<ContactDto> findAll() {
        return contactRepository.findAll().stream().map(dao -> contactMapper.daoToDto(dao))
                .toList();
    }

    @Override
    public ContactDto updateContact(final UUID id, final ContactDto contact) {
        final var currentContactDao = contactRepository.findById(id)
                .orElseThrow(() -> new ContactNotFoundException(id));
        final var updatedContactDao = contactMapper.dtoTodDao(contact);
        currentContactDao.setName(updatedContactDao.getName());
        currentContactDao.setPhoneNumber(updatedContactDao.getPhoneNumber());
        return contactMapper.daoToDto(contactRepository.save(currentContactDao));
    }

    @Override
    public void deleteContact(final UUID id) {
        if (!contactRepository.existsById(id)) {
            throw new ContactNotFoundException(id);
        }
        contactRepository.deleteById(id);
    }
}
