package com.marios.gavriil.movierama2.services.interfaces;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.model.Movie;

import java.util.Optional;

public interface MovieService {

    Movie addMovie(MovieDto movieDto);
    Movie voteMovie(MovieDto movieDto, boolean vote);
    Optional<Movie> findById(Long id);
}
