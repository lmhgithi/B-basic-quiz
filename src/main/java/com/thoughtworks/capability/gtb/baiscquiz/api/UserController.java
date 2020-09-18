package com.thoughtworks.capability.gtb.baiscquiz.api;


import com.thoughtworks.capability.gtb.baiscquiz.domain.Education;
import com.thoughtworks.capability.gtb.baiscquiz.domain.User;
import com.thoughtworks.capability.gtb.baiscquiz.exception.CommonException;
import com.thoughtworks.capability.gtb.baiscquiz.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping("/{userId}/educations")
    public List<Education> getEducations(@PathVariable long userId) {
        return userService.getEducations(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User addUser(@RequestBody @Valid User user){
        return userService.addUser(user);
    }

    @PostMapping("/{userId}/educations")
    @ResponseStatus(HttpStatus.CREATED)
    public Education addEducation(@PathVariable long userId, @RequestBody @Valid Education education){
        return userService.addEducation(userId, education);
    }
}
