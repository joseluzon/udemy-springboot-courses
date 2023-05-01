package com.github.joseluzon.udemy.springbootdevbootcamp.gradesubmission;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.github.javafaker.Faker;

/*
 * Integration testing for test the whole request-response lifecycle --> needs the Spring context.
 * 
 * First Unit testing -> then Integration testing.
 */
@SpringBootTest // For integration testing.
@AutoConfigureMockMvc
class GradeSubmissionApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    private Faker faker = new Faker();

    @Test
    void contextLoads() {
        assertNotNull(mockMvc);
    }

    @Test
    public void testGetGrades() throws Exception {
        final var requestBuilder = MockMvcRequestBuilders.get("/grades");

        mockMvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.view().name("grades"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("grades"));
    }

    @Test
    public void testGetForm() throws Exception {
        final var requestBuilder = MockMvcRequestBuilders.get("/grades/form/?id=123");

        mockMvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.view().name("form"))
            .andExpect(MockMvcResultMatchers.model().attributeExists("grade"));
    }

    @Test
    public void testSuccesfulSubmission() throws Exception {
        final var requestBuilder = MockMvcRequestBuilders.post("/grades/submitGrade")
            .param("name", faker.harryPotter().character())
            .param("subject", faker.harryPotter().spell())
            .param("score", "A+");

        mockMvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
            .andExpect(MockMvcResultMatchers.redirectedUrl("/grades"));
    }

    @Test
    public void testUnsuccesfulSubmission() throws Exception {
        final var requestBuilder = MockMvcRequestBuilders.post("/grades/submitGrade")
            .param("name", faker.harryPotter().character())
            .param("subject", faker.harryPotter().spell())
            .param("score", "JJJ");

        mockMvc.perform(requestBuilder)
            .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
            .andExpect(MockMvcResultMatchers.view().name("form"));
    }

}
