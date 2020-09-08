package com.marios.gavriil.movierama2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class MovieRatingKey implements Serializable {

    @Column(name = "user_id")
    @NotNull
    private Long userId;

    @Column(name = "movie_id")
    @NotNull
    private Long movieId;
}
