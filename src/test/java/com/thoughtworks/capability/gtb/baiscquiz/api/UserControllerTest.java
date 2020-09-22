package com.thoughtworks.capability.gtb.baiscquiz.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thoughtworks.capability.gtb.baiscquiz.domain.Education;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import com.thoughtworks.capability.gtb.baiscquiz.repository.EducationRepository;
import com.thoughtworks.capability.gtb.baiscquiz.repository.UserRepository;
import org.junit.jupiter.api.*;
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

    @Autowired
    EducationRepository educationRepository;

    ObjectMapper objectMapper = new ObjectMapper();

    private long user_id;

    @BeforeEach
    void init() {
        educationRepository.deleteAll();
        userRepository.deleteAll();
        User user = User.builder()
                .name("Kevin")
                .age(24)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, " +
                        "dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut " +
                        "fuga aliquam ad asperiores voluptatem dolorum! Quasi.")
                .build();

        userRepository.save(user);
        user_id = user.getId();
        Education education1 = Education.builder()
                .user(user)
                .year(1990)
                .title("I was born in Katowice")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                        "Sapiente, exercitationem, totam, dolores iste dolore est aut modi.")
                .build();
        Education education2 = Education.builder()
                .user(user)
                .year(1990)
                .title("I was born in Katowice")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                        "Sapiente, exercitationem, totam, dolores iste dolore est aut modi.")
                .build();
        educationRepository.save(education1);
        educationRepository.save(education2);
    }

    @Test
    public void should_get_user_by_id() throws Exception {
        mockMvc.perform(get("/users/" + user_id))
                .andExpect(jsonPath("$.name", is("Kevin")))
                .andExpect(jsonPath("$.age", is(24)))
                .andExpect(status().isOk());
    }


    @Nested
    public class AddUser {
        @Test
        public void should_add_user() throws Exception {
            User user = User.builder()
                    .name("xiaoming")
                    .avatar("1234567890")
                    .description("11111111111111111111111111")
                    .age(20)
                    .build();

            String jsonData = objectMapper.writeValueAsString(user);
            mockMvc.perform(post("/users")
                    .content(jsonData).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is("xiaoming")));
        }

        @Test
        public void should_throw_badRequest_when_given_invalid_age() throws Exception {
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

    @Test
    public void should_get_educations() throws Exception {
        mockMvc.perform(get("/users/" + user_id + "/educations"))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].year", is(1990)))
                .andExpect(status().isOk());
    }


    @Nested
    public class AddEducation {
        @Test
        public void should_add_education() throws Exception {
            User user = User.builder()
                    .name("xiaoming")
                    .avatar("1234567890")
                    .description("11111111111111111111111111")
                    .age(20)
                    .build();

            userRepository.save(user);
            Education education = Education.builder()
                    .user(user)
                    .year(1990)
                    .title("I was born in Katowice")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                            "Sapiente, exercitationem, totam, dolores iste dolore est aut modi.")
                    .build();

            String jsonData = objectMapper.writeValueAsString(education);

            mockMvc.perform(post("/users/" + user_id + "/educations")
                    .content(jsonData).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isCreated());
        }

        @Test
        public void should_throw_badRequest_when_given_invalid_year() throws Exception {
            User user = User.builder()
                    .name("xiaoming")
                    .avatar("1234567890")
                    .description("11111111111111111111111111")
                    .age(20)
                    .build();

            userRepository.save(user);
            Education education = Education.builder()
                    .user(user)
                    .year(500)
                    .title("I was born in Katowice")
                    .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                            "Sapiente, exercitationem, totam, dolores iste dolore est aut modi.")
                    .build();

            String jsonData = objectMapper.writeValueAsString(education);

            mockMvc.perform(post("/users/" + user_id + "/educations")
                    .content(jsonData).contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

    }

}