package com.thoughtworks.capability.gtb.baiscquiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import com.thoughtworks.capability.gtb.baiscquiz.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    UserRepository userRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void shouldGetUser() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(jsonPath("$.name", is("KAMIL")))
                .andExpect(jsonPath("$.age", is(24)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldThrowNotFunWhenUserIdNotExists() throws Exception {
        mockMvc.perform(get("/users/0"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldGetEducations() throws Exception {
        mockMvc.perform(get("/users/1/educations"))
                .andExpect(jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0].year", is(1990)))
                .andExpect(status().isOk());
    }

    @Test
    void shouldAddUser() throws Exception {
        User user = User.builder()
                .name("xiaoming")
                .avatar("1234567890")
                .description("11111111111111111111111111")
                .age(20)
                .build();

        String jsonData = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users")
                .content(jsonData).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        assertEquals("xiaoming", userRepository.getUserById((long) 2).get().getName());
    }

    @Test
    void shouldThrowBadRequestWhenAddUserWithInvalidParameters() throws Exception {
        User user = User.builder()
                .name("xiaoming")
                .avatar("1234567890")
                .description("11111111111111111111111111")
                .age(10)
                .build();

        String jsonData = objectMapper.writeValueAsString(user);
        mockMvc.perform(post("/users")
                .content(jsonData).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}