package com.marios.gavriil.movierama2.services.interfaces;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.User;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Movie addMovie(MovieDto movieDto);
    Movie updateMovie(Long movieId, MovieDto movieDto) throws Exception;
    Optional<Movie> findById(Long id);
    List<Movie> findAllMovies();
    List<Movie> sortMoviesByLikes();
    List<Movie> sortMoviesByHates();
    List<Movie> sortMoviesByDate();
    List<Movie> findAllMoviesByUser(User user);
}