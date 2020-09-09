package com.marios.gavriil.movierama2.dto;

import com.marios.gavriil.movierama2.model.Movie;
import com.marios.gavriil.movierama2.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class
MovieRatingDto {

    @NotNull
    @NotEmpty
    private User user;

    @NotNull
    @NotEmpty
    private Movie movie;

    @NotNull
    @NotEmpty
    private boolean vote;
}
