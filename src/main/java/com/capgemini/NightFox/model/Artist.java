package com.capgemini.NightFox.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotBlank  (message = "Band name is mandatory.")
    @Size(min = 3, max = 20)
    private String bandName;
    private String description;

    @OneToMany(mappedBy = "artist")
    private List<Review> reviews = new ArrayList<>();

    @ManyToOne
    private Artist_Category artist_category;

    @OneToMany(mappedBy = "artist")
    private List<Artist_Images> artist_imagesList = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "artist")
    private List<Concert> concerts = new ArrayList<>();

    public Artist() {
    }

    public Artist(String bandName, String description) {
        this.bandName = bandName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBandName() {
        return bandName;
    }

    public void setBandName(String bandName) {
        this.bandName = bandName;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Artist_Category getArtist_category() {
        return artist_category;
    }

    public void setArtist_category(Artist_Category artist_category) {
        this.artist_category = artist_category;
    }

    public List<Artist_Images> getArtist_imagesList() {
        return artist_imagesList;
    }

    public void setArtist_imagesList(List<Artist_Images> artist_imagesList) {
        this.artist_imagesList = artist_imagesList;
    }

    public List<Concert> getConcerts() {
        return concerts;
    }

    public void setConcerts(List<Concert> concerts) {
        this.concerts = concerts;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
