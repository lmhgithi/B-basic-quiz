package com.thoughtworks.capability.gtb.baiscquiz.service;

import com.thoughtworks.capability.gtb.baiscquiz.domain.Education;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import com.thoughtworks.capability.gtb.baiscquiz.exception.CommonException;
import com.thoughtworks.capability.gtb.baiscquiz.repository.EducationRepository;
import com.thoughtworks.capability.gtb.baiscquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EducationRepository educationRepository;

    public UserService(UserRepository userRepository, EducationRepository educationRepository) {
        this.userRepository = userRepository;
        this.educationRepository = educationRepository;
    }

    @PostConstruct
    public void init() {
        User user = User.builder()
                .name("Kevin")
                .age(24)
                .avatar("https://inews.gtimg.com/newsapp_match/0/3581582328/0")
                .description("Lorem ipsum dolor sit amet, consectetur adipisicing elit. Repellendus, non, " +
                        "dolorem, cumque distinctio magni quam expedita velit laborum sunt amet facere tempora ut " +
                        "fuga aliquam ad asperiores voluptatem dolorum! Quasi.")
                .build();
        userRepository.save(user);

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

    public User getUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new CommonException("user id not exists");
        }
    }

    public List<Education> getEducations(long userId) {
        return educationRepository.findAllByUserId(userId);
    }

    public User addUser(User user) {
        userRepository.save(user);
        return userRepository.findById(user.getId()).get();
    }

    public Education addEducation(long userId, Education education) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()){
            education.setUser(user.get());
            educationRepository.save(education);
            return education;
        }
        throw new CommonException("user id not exists");
    }
}
