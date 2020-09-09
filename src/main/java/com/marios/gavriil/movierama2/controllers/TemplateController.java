package com.marios.gavriil.movierama2.controllers;

import com.marios.gavriil.movierama2.services.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
public class TemplateController {

    @Autowired
    MovieService movieService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/signup")
    public String signup() {
        return "signup";
    }

    @RequestMapping({"/index", "/"})
    public String index(Model model) {
        model.addAttribute("movies", movieService.findAllMovies());
        return "index";
    }

    @RequestMapping({"/newMovie"})
    public String newMovie() {
        return "newMovie";
    }
}
