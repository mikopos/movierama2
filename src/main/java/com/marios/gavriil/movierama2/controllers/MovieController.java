package com.marios.gavriil.movierama2.controllers;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.services.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping(value = "/movie/addMovie")
    public String addMovie(@Valid MovieDto movieDto, Model model){
        model.addAttribute("movie", movieService.addMovie(movieDto));
        return "redirect:/index";
    }

    @GetMapping(value = "/movie/findAllMovies")
    public List<Movie> findAllMovies(){
        return movieService.findAllMovies();
    }

    @PostMapping(value = "/movie/findAllMoviesByUser")
    public List<Movie> findAllMoviesByUser(@RequestBody MovieDto movieDto){
        return movieService.findAllMoviesByUser(movieDto.getUser());
    }

}
