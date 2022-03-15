package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.repository.ConcertRepository;
import com.capgemini.NightFox.repository.ConcertHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConcertService {

    private ArtistRepository artistRepository;
    private ConcertRepository concertRepository;
    private ConcertHallRepository concertHallRepository;

    @Autowired
    public ConcertService(ArtistRepository artistRepository, ConcertRepository concertRepository, ConcertHallRepository concertHallRepository) {
        this.artistRepository = artistRepository;
        this.concertRepository = concertRepository;
        this.concertHallRepository = concertHallRepository;
    }

    public ResponseEntity<?> getAllConcerts(){
        List<Concert> concerts = new ArrayList<>();
        concertRepository.findAll().forEach(concerts::add);
        return ResponseEntity.ok().body(concerts);
    }

    public ResponseEntity<?> getAllConcertsByArtistId(Long id) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if(possibleArtist.isPresent()){
            List<Concert> concertList = new ArrayList<>();
            concertList.addAll(concertRepository.findByArtist(possibleArtist.get()));
            return ResponseEntity.ok().body(concertList);
        }
        throw new NotFoundException(
                "Artist id: " + id + "does not exist.");
    }
    public ResponseEntity<?> getAllConcertsByArtistBandName(String bandName) {
        Optional<Artist> possibleArtist = artistRepository.findByBandName(bandName);
        if(possibleArtist.isPresent()){
            List<Concert> concertList = new ArrayList<>();
            concertList.addAll(concertRepository.findByArtist(possibleArtist.get()));
            return ResponseEntity.ok().body(concertList);
        }
        throw new NotFoundException(
                "Artist name: " + bandName + "does not exist.");
    }

    public ResponseEntity<?> addConcertHallToArtist(Long artistId, Long concertHallId){
        ConcertHall concert_hall = concertHallRepository.findConcert_HallById(concertHallId);
        Optional<Artist> possibleArtist = artistRepository.findById(artistId);
        if(possibleArtist.isPresent()){
            Concert concert = concertRepository.findByArtistAndConcertHall(possibleArtist.get(), concert_hall);
            if(concert != null){
                return ResponseEntity.badRequest().body("This concert hall is already added to the artist. ");
            }else{
                Concert concert_added = new Concert(possibleArtist.get(), concert_hall);
                concert_added.setArtist(possibleArtist.get());
                concert_added.setConcertHall(concert_hall);
                concertRepository.save(concert_added);
                return ResponseEntity.ok().body("The concert hall is successfully added.");
            }
        }
        throw new NotFoundException(
                "The artist id: " + artistId + "does not exist.");
    }

    public ResponseEntity<?> addConcertDetailedInfo(Long artistId, Long concertHallId, Concert dataConcert){
        ConcertHall concertHall = concertHallRepository.findConcert_HallById(concertHallId);
        Optional<Artist> possibleArtist = artistRepository.findById(artistId);
        if(possibleArtist.isPresent()){
            Concert concert = concertRepository.findByArtistAndConcertHall(possibleArtist.get(), concertHall);
            concert.setPrice(dataConcert.getPrice());
            concert.setTime(dataConcert.getTime());
            concert.setDescription(dataConcert.getDescription());
            concertRepository.save(concert);
            return ResponseEntity.ok().body("Concert information is updated.");
        }
        throw new NotFoundException(
                "The artist id: " + artistId + "does not exist.");
    }

    public ResponseEntity<?> deleteConcertById(Long artistId, Long concertHallId){
        ConcertHall concertHall = concertHallRepository.findConcert_HallById(concertHallId);
        Optional<Artist> possibleArtist = artistRepository.findById(artistId);
        if(possibleArtist.isPresent()){
            concertRepository.deleteByArtistAndConcertHall(possibleArtist.get(), concertHall);
            return ResponseEntity.ok().body("Concert successfully deleted.");
        }
        throw new NotFoundException(
                "The artist id: " + artistId + "does not exist.");
    }







}
