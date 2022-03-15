package com.capgemini.NightFox.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table
public class Review {
    @Id
    @GeneratedValue (strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank (message =  "please fill in all the field.")
    private String nameReviewer;
    @NotBlank (message =  "please give us  your opinion.")
    private String description;
    @NotNull
    private Boolean like;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Artist artist;

    public Review() {
    }

    public Review(String nameReviewer, String description, Boolean like) {
        this.nameReviewer = nameReviewer;
        this.description = description;
        this.like = like;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }
    @JsonBackReference
    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getNameReviewer() {
        return nameReviewer;
    }

    public void setNameReviewer(String nameReviewer) {
        this.nameReviewer = nameReviewer;
    }
}
