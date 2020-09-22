package com.thoughtworks.capability.gtb.baiscquiz.service;

import com.thoughtworks.capability.gtb.baiscquiz.domain.Education;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import com.thoughtworks.capability.gtb.baiscquiz.exception.CommonException;
import com.thoughtworks.capability.gtb.baiscquiz.repository.EducationRepository;
import com.thoughtworks.capability.gtb.baiscquiz.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EducationRepository educationRepository;

    @BeforeEach
    void setup() {
        userService = new UserService(userRepository, educationRepository);
    }

    @Test
    public void should_throw_notFound_when_get_with_userId_not_exists() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(CommonException.class, () -> userService.getUser(1L));
    }

    @Test
    public void should_throw_when_add_education_with_not_exists_userId() {
        Education education = new Education();
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(CommonException.class, () -> userService.addEducation(1L, education));
    }
}