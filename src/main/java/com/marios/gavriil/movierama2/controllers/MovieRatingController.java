package com.marios.gavriil.movierama2.controllers;

import com.marios.gavriil.movierama2.dto.MovieRatingDto;
import com.marios.gavriil.movierama2.model.MovieRating;
import com.marios.gavriil.movierama2.services.interfaces.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class MovieRatingController {

    @Autowired
    MovieRatingService movieRatingService;

    @PostMapping("/vote/movie")
    public MovieRating voteForMovie (@RequestBody @Valid MovieRatingDto movieRatingdto) throws Exception {
        return movieRatingService.voteForMovie(movieRatingdto);
    }
}
