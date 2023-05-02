package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev;

import java.util.stream.IntStream;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dao.ContactDao;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class BackendDevApplicationRunner {
    
    private final Faker faker;
    private final ContactRepository contactRepository;

    @Bean
    @Profile("development")
    public ApplicationRunner developmentApplicationRunner() {
        return (args) -> {
            IntStream.range(0, 10).forEach(i -> {
                final var contact = ContactDao.builder().name(faker.artist().name())
                        .phoneNumber(faker.phoneNumber().phoneNumber()).build();
                final var contactCreated = contactRepository.save(contact);
                log.info("Contact created : {}", contactCreated);
            });
        };
    }
}
