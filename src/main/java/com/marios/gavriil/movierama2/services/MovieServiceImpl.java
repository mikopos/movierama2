package com.marios.gavriil.movierama2.services;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.repositories.MovieRepository;
import com.marios.gavriil.movierama2.services.interfaces.MovieService;
import com.marios.gavriil.movierama2.services.interfaces.UserService;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Movie addMovie(MovieDto movieDto) {

        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setPublicationDate(new LocalDate());
        movie.setUser(userService.loadUserDetails(movieDto.getUser().getUsername()));
        movie.setNumberOfLikes(0);
        movie.setNumberOfHates(0);

        return movieRepository.save(movie);
    }

    @Override
    public Movie voteMovie(MovieDto movieDto, boolean vote) {
        return null;
    }
}
