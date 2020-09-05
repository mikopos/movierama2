package com.marios.gavriil.movierama2.services.interfaces;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.model.Movie;

public interface MovieService {

    Movie addMovie(MovieDto movieDto);
    Movie voteMovie(MovieDto movieDto, boolean vote);
}
