package com.marios.gavriil.movierama2.services.interfaces;

import com.marios.gavriil.movierama2.dto.MovieRatingdto;
import com.marios.gavriil.movierama2.model.MovieRating;

public interface MovieRatingService {

    MovieRating voteForMovie (MovieRatingdto movieRatingdto) throws Exception;
}
