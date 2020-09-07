package com.marios.gavriil.movierama2.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@EqualsAndHashCode
public class MovieRatingKey implements Serializable {

    @Column
    private Long userId;

    @Column
    private Long movieId;
}
