package com.marios.gavriil.movierama2.dto;

import com.marios.gavriil.movierama2.model.User;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class MovieDto {

    @NotNull
    @NotEmpty
    private String title;

    @NotNull
    @NotEmpty
    private String description;

    private User user;
    private Date publicationDate;
    private Integer numberOfLikes;
    private Integer numberOfHates;
}
