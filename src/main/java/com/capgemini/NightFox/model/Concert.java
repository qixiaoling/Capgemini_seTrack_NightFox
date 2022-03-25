package com.capgemini.NightFox.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@EqualsAndHashCode
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "individual_concert_id")
    private Long id;
    @Column
    @NotNull
    private Double price;
    @Column
    @NotBlank  (message = "Description is mandatory.")
    private String description;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate time;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Artist artist;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ConcertHall concertHall;


    public Concert() {
    }

    public Concert(Artist artist, ConcertHall concertHall) {
        this.artist = artist;
        this.concertHall = concertHall;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ConcertHall getConcertHall() {
        return concertHall;
    }

    public void setConcertHall(ConcertHall concert_hall) {
        this.concertHall = concertHall;
    }


}
