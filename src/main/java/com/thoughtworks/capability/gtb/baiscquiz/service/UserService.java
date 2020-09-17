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
        // GTB: - 下面这种语句，可以不用 else 语句，其它几处同理
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
            // GTB: - 如果 user 就是刚刚好没有任何 educations 呢？也要抛异常？
            throw new CommonException("education info of the user id is not exists");
        }
    }

    public User addUser(User user) {
        return userRepository.addUser(user);
    }

    public Education addEducation(long userId, Education education) {
        // GTB: - 如果指定的 userId 没有对应到任何 user，怎么办？依然把 education 存下来？
        education.setUserId(userId);
        return userRepository.addEducation(education);
    }
}
