package com.thoughtworks.capability.gtb.baiscquiz.repository;

import com.thoughtworks.capability.gtb.baiscquiz.domain.Education;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class EducationRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private EducationRepository educationRepository;

    @Test
    public void should_find_all_by_userId(){
        User user = User.builder()
                .name("Kevin")
                .age(24)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, " +
                        "dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut " +
                        "fuga aliquam ad asperiores voluptatem dolorum! Quasi.")
                .build();
        entityManager.persistAndFlush(user);
        Long userId = user.getId();
        Education education1 = Education.builder()
                .user(user)
                .year(1990)
                .title("I was born in Katowice")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                        "Sapiente, exercitationem, totam, dolores iste dolore est aut modi.")
                .build();
        Education education2 = Education.builder()
                .user(user)
                .year(2020)
                .title("I was born in Katowice2")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. " +
                        "Sapiente, exercitationem, totam, dolores iste dolore est aut modi.")
                .build();
        entityManager.persistAndFlush(education1);
        entityManager.persistAndFlush(education2);

        List<Education> educations = educationRepository.findAllByUserId(userId);
        assertEquals(2, educations.size());
        assertEquals(1990, educations.get(0).getYear());
        assertEquals(2020, educations.get(1).getYear());

    }

}