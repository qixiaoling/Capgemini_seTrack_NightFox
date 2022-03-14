package com.capgemini.NightFox.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable
@EqualsAndHashCode
public class ArtistConcertHallId implements Serializable {

    @Column
    private Long artistId;
    @Column
    private Long concertHallId;

    public ArtistConcertHallId() {
    }

    public ArtistConcertHallId(Long artistId, Long concertHallId) {
        this.artistId = artistId;
        this.concertHallId = concertHallId;
    }

    public Long getArtistId() {
        return artistId;
    }


    public Long getConcertHallId() {
        return concertHallId;
    }


}
