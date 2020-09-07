package com.marios.gavriil.movierama2.repositories;

import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.MovieRating;
import com.marios.gavriil.movierama2.model.MovieRatingKey;
import com.marios.gavriil.movierama2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRatingRepository extends JpaRepository<MovieRating, MovieRatingKey> {

    MovieRating findByUserAndMovie(User user, Movie movie);
}
