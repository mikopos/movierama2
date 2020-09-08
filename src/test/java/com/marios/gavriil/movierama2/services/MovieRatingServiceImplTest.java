package com.marios.gavriil.movierama2.services;

import com.marios.gavriil.movierama2.dto.MovieRatingDto;
import com.marios.gavriil.movierama2.exceptions.SameVoteException;
import com.marios.gavriil.movierama2.exceptions.SameVoterAndCreatorException;
import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.MovieRating;
import com.marios.gavriil.movierama2.model.MovieRatingKey;
import com.marios.gavriil.movierama2.model.User;
import com.marios.gavriil.movierama2.services.interfaces.MovieRatingService;
import org.joda.time.LocalDate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;


@RunWith(SpringRunner.class)
public class MovieRatingServiceImplTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public ModelMapper modelMapper() { return new ModelMapper();}
    }

    @MockBean
    private MovieRatingService movieRatingService;

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void voteForMovieSuccessfulforLike() throws Exception {
        MovieRating givenMovieRating = createGivenMovieRating(true);
        MovieRatingDto movieRatingdto = modelMapper.map(givenMovieRating, MovieRatingDto.class);

        Mockito.when(movieRatingService.voteForMovie(movieRatingdto))
                .thenReturn(givenMovieRating);

        MovieRating expectedMovieRating = movieRatingService.voteForMovie(movieRatingdto);

        assertEquals(expectedMovieRating, givenMovieRating);
    }

    @Test
    public void voteForMovieSuccessfulforHate() throws Exception {
        MovieRating givenMovieRating = createGivenMovieRating(false);
        MovieRatingDto movieRatingdto = modelMapper.map(givenMovieRating, MovieRatingDto.class);

        Mockito.when(movieRatingService.voteForMovie(movieRatingdto))
                .thenReturn(givenMovieRating);

        MovieRating expectedMovieRating = movieRatingService.voteForMovie(movieRatingdto);

        assertEquals(expectedMovieRating, givenMovieRating);
    }

    @Test
    public void voteForMovieUserIsTheCreator() throws Exception {
        MovieRating givenMovieRating = createGivenMovieRating(false);
        MovieRatingDto movieRatingdto = modelMapper.map(givenMovieRating, MovieRatingDto.class);

        Mockito.when(movieRatingService.voteForMovie(movieRatingdto))
                .thenThrow(SameVoterAndCreatorException.class);

        assertThrows(SameVoterAndCreatorException.class, ()-> {
            movieRatingService.voteForMovie(movieRatingdto);
        });
    }

    @Test
    public void voteForMovieAlreadyLiked() throws Exception {
        MovieRating givenMovieRating = createGivenMovieRating(false);
        MovieRatingDto movieRatingdto = modelMapper.map(givenMovieRating, MovieRatingDto.class);

        Mockito.when(movieRatingService.voteForMovie(movieRatingdto))
                .thenThrow(SameVoteException.class);

        assertThrows(SameVoteException.class, ()-> {
            movieRatingService.voteForMovie(movieRatingdto);
        });
    }

    @Test
    public void voteForMovieAlreadyHate() throws Exception {
        MovieRating givenMovieRating = createGivenMovieRating(false);
        MovieRatingDto movieRatingdto = modelMapper.map(givenMovieRating, MovieRatingDto.class);

        Mockito.when(movieRatingService.voteForMovie(movieRatingdto))
                .thenThrow(SameVoteException.class);

        assertThrows(SameVoteException.class, ()-> {
            movieRatingService.voteForMovie(movieRatingdto);
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

    private User createSecondGivenUser(){
        User givenUser = new User();
        givenUser.setId(2L);
        givenUser.setUsername("user2");
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

    private MovieRating createGivenMovieRating(boolean vote){

        MovieRating movieRating = new MovieRating();
        movieRating.setId(new MovieRatingKey());
        movieRating.setMovie(createGivenMovie());
        movieRating.setUser(createGivenUser());
        movieRating.setVote(vote);
        return movieRating;
    }
}