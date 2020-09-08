package com.marios.gavriil.movierama2.services;

import com.marios.gavriil.movierama2.dto.MovieDto;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.services.interfaces.MovieService;
import com.marios.gavriil.movierama2.services.interfaces.UserService;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.modelmapper.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class MovieServiceImplTest {

    @TestConfiguration
    static class MovieServiceImplTestContextConfiguration {
        @Bean
        public ModelMapper modelMapper() { return new ModelMapper();}
    }

    @MockBean
    private MovieService movieService;

    @Autowired
    private ModelMapper modelMapper;


    @Test
    public void addMovieSuccessful() {
        Movie givenMovie = createGivenMovie();
        MovieDto movieDto = modelMapper.map(givenMovie, MovieDto.class);

        Mockito.when(movieService.addMovie(movieDto))
                .thenReturn(givenMovie);

        Movie expectedMovie = movieService.addMovie(movieDto);
        assertEquals(expectedMovie, givenMovie);
    }

    @Test
    public void addMovieNullTitle() {
        Movie givenMovie = createGivenMovie();
        MovieDto movieDto = modelMapper.map(givenMovie, MovieDto.class);
        movieDto.setTitle(null);

        Mockito.when(movieService.addMovie(movieDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, ()->{
            movieService.addMovie(movieDto);
        });
    }

    @Test
    public void addMovieEmptyTitle() {
        Movie givenMovie = createGivenMovie();
        MovieDto movieDto = modelMapper.map(givenMovie, MovieDto.class);
        movieDto.setTitle("");

        Mockito.when(movieService.addMovie(movieDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, ()->{
            movieService.addMovie(movieDto);
        });
    }

    @Test
    public void addMovieNullDescription() {
        Movie givenMovie = createGivenMovie();
        MovieDto movieDto = modelMapper.map(givenMovie, MovieDto.class);
        movieDto.setDescription(null);

        Mockito.when(movieService.addMovie(movieDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, ()->{
            movieService.addMovie(movieDto);
        });
    }

    @Test
    public void addMovieEmptyDescription() {
        Movie givenMovie = createGivenMovie();
        MovieDto movieDto = modelMapper.map(givenMovie, MovieDto.class);
        movieDto.setDescription("");

        Mockito.when(movieService.addMovie(movieDto))
                .thenThrow(ValidationException.class);

        assertThrows(ValidationException.class, ()->{
            movieService.addMovie(movieDto);
        });
    }

    @Test
    public void updateMovieSuccessful() throws Exception {
        Movie givenMovie = createGivenMovie();

        Mockito.when(movieService.findById(1L))
                .thenReturn(java.util.Optional.of(givenMovie));

        Optional<Movie> movieOptional = movieService.findById(1L);

        assertNotNull(movieOptional);

        MovieDto movieDto = modelMapper.map(movieOptional.get(), MovieDto.class);
        movieDto.setTitle("changedTitle");
        givenMovie.setTitle("changedTitle");

        Mockito.when(movieService.updateMovie(movieOptional.get().getId(), movieDto))
                .thenReturn(givenMovie);

        assertEquals(movieService.updateMovie(movieOptional.get().getId(), movieDto), givenMovie);
    }


    @Test
    public void findByIdSuccessful() {
        Movie givenMovie = createGivenMovie();

        Mockito.when(movieService.findById(1L))
                .thenReturn(java.util.Optional.of(givenMovie));

        Optional<Movie> movieOptional = movieService.findById(1L);

        assertNotNull(movieOptional);
        assertEquals(movieOptional.get(), givenMovie);
    }

    @Test
    public void findByIdNoExistingId() {
        Mockito.when(movieService.findById(1L))
                .thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, ()-> {
           movieService.findById(1L);
        });
    }

    private User createGivenUser(){
        User givenUser = new User();
        givenUser.setId(1L);
        givenUser.setUsername("user");
        givenUser.setPassword("1234");
        givenUser.setRole("ROLE_USER");
        givenUser.setEnabled(true);
        givenUser.setFirstName("firstname");
        givenUser.setLastName("lastname");
        return givenUser;
    }

    private Movie createGivenMovie(){

        Movie givenMovie = new Movie();
        givenMovie.setId(1L);
        givenMovie.setTitle("title");
        givenMovie.setDescription("description");
        givenMovie.setPublicationDate(new LocalDate());
        givenMovie.setNumberOfLikes(0);
        givenMovie.setNumberOfHates(0);
        givenMovie.setUser(createGivenUser());
        return givenMovie;
    }
}