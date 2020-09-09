package com.marios.gavriil.movierama2.controllers;

import com.marios.gavriil.movierama2.dto.MovieRatingDto;
import com.marios.gavriil.movierama2.model.MovieRating;
import com.marios.gavriil.movierama2.services.interfaces.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
public class MovieRatingController {

    @Autowired
    MovieRatingService movieRatingService;

    @PostMapping("/vote/movie")
    public String voteForMovie (@Valid MovieRatingDto movieRatingdto, Model model)
            throws Exception {
        model.addAttribute("movieRating", movieRatingService.voteForMovie(movieRatingdto));
        return "redirect:/index";
    }
}
