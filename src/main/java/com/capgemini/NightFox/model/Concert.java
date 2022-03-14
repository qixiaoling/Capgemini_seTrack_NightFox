package com.capgemini.NightFox.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@EqualsAndHashCode
public class Concert {
    @EmbeddedId
    private ArtistConcertHallId id;
    @Column
    private Double price;
    @Column
    private String description;
    @Column
    private LocalDate time;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Artist artist;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Concert_Hall concert_hall;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "concert")
    private List<Concert_Ticket> concert_ticketList = new ArrayList<>();

    public Concert() {
    }

    public Concert(Artist artist, Concert_Hall concert_hall) {
        this.artist = artist;
        this.concert_hall = concert_hall;
        this.id = new ArtistConcertHallId(artist.getId(), concert_hall.getId());
    }

    public ArtistConcertHallId getId() {
        return id;
    }

    public void setId(ArtistConcertHallId id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Concert_Hall getConcert_hall() {
        return concert_hall;
    }

    public void setConcert_hall(Concert_Hall concert_hall) {
        this.concert_hall = concert_hall;
    }

    public List<Concert_Ticket> getConcert_ticketList() {
        return concert_ticketList;
    }

    public void setConcert_ticketList(List<Concert_Ticket> concert_ticketList) {
        this.concert_ticketList = concert_ticketList;
    }
}
