package com.marios.gavriil.movierama2.services;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.exceptions.MovieNotExistException;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.repositories.MovieRepository;
import com.marios.gavriil.movierama2.services.interfaces.MovieService;
import com.marios.gavriil.movierama2.services.interfaces.UserService;
import org.joda.time.LocalDate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserService userService;

    @Autowired
    ModelMapper modelMapper;

    @Override
    @Transactional
    public Movie addMovie(MovieDto movieDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Movie movie = new Movie();
        movie.setTitle(movieDto.getTitle());
        movie.setDescription(movieDto.getDescription());
        movie.setPublicationDate(new LocalDate());
        movie.setUser(userService.loadUserDetails(currentPrincipalName));
        movie.setNumberOfLikes(0);
        movie.setNumberOfHates(0);

        return movieRepository.save(movie);
    }

    @Override
    @Transactional
    public Movie updateMovie(Long movieId, MovieDto movieDto) throws Exception {
        Optional<Movie> optionalMovie = movieRepository.findById(movieId);
        Movie movie;

        if(optionalMovie.isPresent()){
            movie = optionalMovie.get();

            movie.setTitle(movieDto.getTitle());
            movie.setDescription(movieDto.getDescription());
            movie.setNumberOfLikes(movieDto.getNumberOfLikes());
            movie.setNumberOfHates(movieDto.getNumberOfHates());
        }
        else{
            throw new MovieNotExistException("Movie not found");
        }
        return movieRepository.save(movie);
    }

    @Override
    public Optional<Movie> findById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> sortMoviesByLikes() {
        List<Movie> movieList =  movieRepository.findAll();
        movieList.sort(Comparator.comparing(Movie::getNumberOfLikes));
        return movieList;
    }

    @Override
    public List<Movie> sortMoviesByHates() {
        List<Movie> movieList =  movieRepository.findAll();
        movieList.sort(Comparator.comparing(Movie::getNumberOfHates));
        return movieList;
    }

    @Override
    public List<Movie> sortMoviesByDate() {
        List<Movie> movieList =  movieRepository.findAll();
        movieList.sort(Comparator.comparing(Movie::getPublicationDate));
        return movieList;
    }

    @Override
    public List<Movie> findAllMoviesByUser(User user) {
        return movieRepository.findAllByUser(user);
    }
}
