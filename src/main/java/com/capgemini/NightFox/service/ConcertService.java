package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.Concert_Hall;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.repository.ConcertRepository;
import com.capgemini.NightFox.repository.Concert_HallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {

    private ArtistRepository artistRepository;
    private ConcertRepository concertRepository;
    private Concert_HallRepository concert_hallRepository;

    @Autowired
    public ConcertService(ArtistRepository artistRepository, ConcertRepository concertRepository, Concert_HallRepository concert_hallRepository) {
        this.artistRepository = artistRepository;
        this.concertRepository = concertRepository;
        this.concert_hallRepository = concert_hallRepository;
    }


    public ResponseEntity<?> getAllConcertByArtistId(Long id) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
            List<Concert> concertList = new ArrayList<>();
            concertList.addAll(concertRepository.findByArtist(possibleArtist.get()));
            return ResponseEntity.ok().body(concertList);
        }
        throw new NotFoundException(
                "Artist id: " + id + "does not exist.");
    }

    public ResponseEntity<?> addConcertHallToArtist(Long artistId, Long concertHallId){
        Concert_Hall concert_hall = concert_hallRepository.findConcert_HallById(concertHallId);
        Optional<Artist> possibleArtist = artistRepository.findById(artistId);
        if(possibleArtist.isPresent()){
            Concert concert = concertRepository.findByArtistAndConcert_Hall(possibleArtist.get(), concert_hall);
            if(concert != null){
                return ResponseEntity.badRequest().body("This concert hall is already added to the artist. ");
            }else{
                Concert concert_added = new Concert(possibleArtist.get(), concert_hall);
                concert_added.setArtist(possibleArtist.get());
                concert_added.setConcert_hall(concert_hall);
                concertRepository.save(concert_added);
                return ResponseEntity.ok().body("The concert hall is successfully added.");
            }
        }
        throw new NotFoundException(
                "The artist id: " + artistId + "does not exist.");
    }







}
