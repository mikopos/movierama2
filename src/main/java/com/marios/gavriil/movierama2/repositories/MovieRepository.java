package com.marios.gavriil.movierama2.repositories;

import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    List<Movie> findAllByUser(User user);
}
