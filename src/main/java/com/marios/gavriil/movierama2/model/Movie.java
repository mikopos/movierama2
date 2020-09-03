package com.marios.gavriil.movierama2.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "movie")
public class Movie implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @ManyToOne
    private User user;

    @Column(name="publicationDate")
    private Date publicationDate;

    @Column(name="numberOfLikes")
    private Integer numberOfLikes;

    @Column(name="numberOfHates")
    private Integer numberOfHates;
}
