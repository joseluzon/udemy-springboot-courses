package com.github.joseluzon.udemy.springbootdevbootcamp.backenddev;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.github.javafaker.Faker;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.model.dao.ContactDao;
import com.github.joseluzon.udemy.springbootdevbootcamp.backenddev.repositories.ContactRepository;

/*
 * Integration testing for test the whole request-response lifecycle --> needs the Spring context.
 * 
 * First Unit testing -> then Integration testing.
 */
@SpringBootTest // For integration testing.
@AutoConfigureMockMvc
class BackendDevApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private Faker faker;

    private List<ContactDao> contacts = new ArrayList<>();

    @BeforeEach
    void setup() {
        IntStream.range(0, 5).forEach(i -> {
            final var contact =
                    contactRepository.save(ContactDao.builder().name(faker.artist().name())
                            .phoneNumber(faker.phoneNumber().phoneNumber()).build());
            contacts.add(contact);
        });
    }

    @AfterEach
    void clear() {
        contactRepository.deleteAll();
        contacts.clear();
    }

    @Test
	public void testGetContact() throws Exception {
        for(final var c: contacts) {
            final var requestBuilder = MockMvcRequestBuilders.get(String.format("/contacts/%s", c.getId().toString()));

            mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(c.getId().toString()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(c.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phoneNumber").value(c.getPhoneNumber()));
        }
	}

    @Test
    public void testGetAllContacts() throws Exception {
        final var requestBuilder = MockMvcRequestBuilders.get(String.format("/contacts/all"));

    }

    @Test
    public void testCreateContact() throws Exception {

    }

    @Test
    public void testInvalidContactCreation() throws Exception {

    }

    @Test
    public void testContactNotFound() throws Exception {

    }

}
