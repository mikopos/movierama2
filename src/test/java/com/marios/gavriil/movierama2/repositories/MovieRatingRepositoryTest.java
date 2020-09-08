package com.marios.gavriil.movierama2.repositories;

import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.MovieRating;
import com.marios.gavriil.movierama2.model.User;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MovieRatingRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MovieRatingRepository movieRatingRepository;

    @Test
    public void findByUserAndMovieSuccessful() {

        User givenUser = createGivenUser();
        Movie givenMovie = createGivenMovie();

        MovieRating givenMovieRating = new MovieRating();
        givenMovieRating.setUser(givenUser);
        givenMovieRating.setMovie(givenMovie);
        testEntityManager.persistAndFlush(givenMovieRating);

        MovieRating movieRating = movieRatingRepository.findByUserAndMovie(givenUser, givenMovie);

        assertEquals(movieRating.getUser().getId(),givenUser.getId());
        assertEquals(movieRating.getMovie().getId(), givenMovie.getId());
    }

    @Test
    public void findByUserAndMovieNoExistingMovie() {

        User givenUser = createGivenUser();
        Movie givenMovie = createGivenMovie();
        Movie notExistingMovie = new Movie();
        notExistingMovie.setId(2L);

        MovieRating givenMovieRating = new MovieRating();
        givenMovieRating.setUser(givenUser);
        givenMovieRating.setMovie(givenMovie);
        testEntityManager.persistAndFlush(givenMovieRating);

        MovieRating movieRating = movieRatingRepository.findByUserAndMovie(givenUser, notExistingMovie);

        assertNull(movieRating);
    }

    @Test
    public void findByUserAndMovieSuccessfulNoExistingUser() {

        User givenUser = createGivenUser();
        Movie givenMovie = createGivenMovie();
        User notExistingUser = new User();
        notExistingUser.setId(2L);

        MovieRating givenMovieRating = new MovieRating();
        givenMovieRating.setUser(givenUser);
        givenMovieRating.setMovie(givenMovie);
        testEntityManager.persistAndFlush(givenMovieRating);

        MovieRating movieRating = movieRatingRepository.findByUserAndMovie(notExistingUser, givenMovie);

        assertNull(movieRating);
    }

    private User createGivenUser(){
        User givenUser = new User();
        givenUser.setUsername("user");
        givenUser.setPassword("1234");
        givenUser.setRole("ROLE_USER");
        givenUser.setEnabled(true);
        givenUser.setFirstName("firstname");
        givenUser.setLastName("lastname");
        testEntityManager.persistAndFlush(givenUser);
        return givenUser;
    }

    private Movie createGivenMovie(){

        Movie givenMovie = new Movie();
        givenMovie.setTitle("title");
        givenMovie.setDescription("description");
        givenMovie.setPublicationDate(new LocalDate());
        givenMovie.setNumberOfLikes(0);
        givenMovie.setNumberOfHates(0);
        givenMovie.setUser(createGivenUser());
        testEntityManager.persistAndFlush(givenMovie);
        return givenMovie;
    }
}