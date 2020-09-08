package com.marios.gavriil.movierama2.controllers;

import com.marios.gavriil.movierama2.dto.UserDto;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/registration")
    public User registerUserAccount (@Valid @RequestBody UserDto userDto) throws Exception {
        return userService.registerUserAccount(userDto);
    }
}
