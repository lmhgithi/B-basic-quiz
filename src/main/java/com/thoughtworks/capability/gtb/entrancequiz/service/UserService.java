package com.thoughtworks.capability.gtb.entrancequiz.service;

import com.thoughtworks.capability.gtb.entrancequiz.domain.User;
import com.thoughtworks.capability.gtb.entrancequiz.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Long id) {
        Optional<User> user = userRepository.getUserById(id);
        if(user.isPresent()) {
            return user.get();
        } else {
            throw new InvalidParameterException();
        }
    }
}
