package com.capgemini.NightFox.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class Artist_Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy =  "artist_category")
    private List<Artist> artistList;

    public Artist_Category() {

    }
    public Artist_Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artist> getArtistList() {
        return artistList;
    }

    public void setArtistList(List<Artist> artistList) {
        this.artistList = artistList;
    }
}
