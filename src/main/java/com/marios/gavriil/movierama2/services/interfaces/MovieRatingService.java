package com.marios.gavriil.movierama2.services.interfaces;

import com.marios.gavriil.movierama2.dto.MovieRatingDto;
import com.marios.gavriil.movierama2.model.MovieRating;

public interface MovieRatingService {

    MovieRating voteForMovie (MovieRatingDto movieRatingdto) throws Exception;
}
