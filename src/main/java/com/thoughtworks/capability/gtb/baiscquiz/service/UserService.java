package com.thoughtworks.capability.gtb.baiscquiz.service;

import com.thoughtworks.capability.gtb.baiscquiz.domain.Education;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import com.thoughtworks.capability.gtb.baiscquiz.exception.CommonException;
import com.thoughtworks.capability.gtb.baiscquiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) throws CommonException {
        Optional<User> user = userRepository.getUserById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new CommonException("user id not exists");
        }
    }

    public List<Education> getEducations(long userId) throws CommonException {
        List<Education> educations = userRepository.getEducationsByUserId(userId);
        if(educations.size() > 0) {
            return educations;
        } else {
            throw new CommonException("education info of the user id is not exists");
        }
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public Education addEducation(long userId, Education education) {
        education.setUserId(userId);
        return userRepository.addEducation(education);
    }
}
