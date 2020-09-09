package com.marios.gavriil.movierama2.controllers;

import com.marios.gavriil.movierama2.dto.UserDto;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/user/registration")
    public String registerUserAccount (@Valid UserDto userDto, Model model) throws Exception {
        model.addAttribute("user", userService.registerUserAccount(userDto));
        return "redirect:/index";
    }
}
