package com.marios.gavriil.movierama2.controllers;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.services.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MovieController {

    @Autowired
    MovieService movieService;

    @PostMapping(value = "/movie/addMovie")
    public Movie addMovie(@Valid @RequestBody MovieDto movieDto){
        return movieService.addMovie(movieDto);
    }

}
