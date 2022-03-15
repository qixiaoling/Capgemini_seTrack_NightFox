package com.capgemini.NightFox.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
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
    private Double price;
    @Column
    private String description;
    @Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate time;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Artist artist;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ConcertHall concertHall;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "concert")
    private List<Concert_Ticket> concert_ticketList = new ArrayList<>();

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

    public List<Concert_Ticket> getConcert_ticketList() {
        return concert_ticketList;
    }

    public void setConcert_ticketList(List<Concert_Ticket> concert_ticketList) {
        this.concert_ticketList = concert_ticketList;
    }
}
