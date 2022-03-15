package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.BadRequestException;
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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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

    public ResponseEntity<?> getAllConcerts() {
        List<Concert> concerts = new ArrayList<>();
        concertRepository.findAll().forEach(concerts::add);
        return ResponseEntity.ok().body(concerts);
    }

    public ResponseEntity<?> getAllConcertsByArtistId(Long id) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if (possibleArtist.isPresent()) {
            List<Concert> concertList = new ArrayList<>();
            concertList.addAll(concertRepository.findByArtist(possibleArtist.get()));
            return ResponseEntity.ok().body(concertList);
        }
        throw new NotFoundException(
                "Artist id: " + id + "does not exist.");
    }

    public ResponseEntity<?> getAllConcertsByArtistBandName(String bandName) {
        Optional<Artist> possibleArtist = artistRepository.findByBandName(bandName);
        if (possibleArtist.isPresent()) {
            List<Concert> concertList = new ArrayList<>();
            concertList.addAll(concertRepository.findByArtist(possibleArtist.get()));
            return ResponseEntity.ok().body(concertList);
        }
        throw new NotFoundException(
                "Artist name: " + bandName + "does not exist.");
    }

    public void addConcertHallToArtist(Long artistId, Long concertHallId) {
        ConcertHall concertHall = concertHallRepository.findById(concertHallId).orElseThrow(() -> new NotFoundException("Concert hall does not exist."));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new NotFoundException("Artist does not exist."));

        Optional<Concert> concert = concertRepository.findByArtistAndConcertHall(artist, concertHall);
        if (concert.isPresent()) {
            throw new BadRequestException("This concert hall is already added to the artist.");
        }
        Concert concert_added = new Concert(artist, concertHall);
        concert_added.setArtist(artist);
        concert_added.setConcertHall(concertHall);
        concertRepository.save(concert_added);



    }

    public void addConcertDetailedInfo(Long artistId, Long concertHallId, Concert dataConcert){
        ConcertHall concertHall = concertHallRepository.findById(concertHallId).orElseThrow(() -> new NotFoundException("Concert hall does not exist."));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new NotFoundException("Artist does not exist."));

        Optional<Concert> concert = concertRepository.findByArtistAndConcertHall(artist, concertHall);
        if(concert.isPresent()){
            concert.get().setPrice(dataConcert.getPrice());
            concert.get().setDescription(dataConcert.getDescription());
            concert.get().setTime(dataConcert.getTime());
        }
        throw new NotFoundException(
                "The artist id: " + artistId + "does not exist.");

    }

//    public ResponseEntity<?> addConcertDetailedInfo(Long artistId, Long concertHallId, Concert dataConcert) {
//        ConcertHall concertHall = concertHallRepository.findConcert_HallById(concertHallId);
//        Optional<Artist> possibleArtist = artistRepository.findById(artistId);
//        if (possibleArtist.isPresent()) {
//            Concert concert = concertRepository.findByArtistAndConcertHall(possibleArtist.get(), concertHall);
//            concert.setPrice(dataConcert.getPrice());
//            concert.setTime(dataConcert.getTime());
//            concert.setDescription(dataConcert.getDescription());
//            concertRepository.save(concert);
//            return ResponseEntity.ok().body("Concert information is updated.");
//        }
//        throw new NotFoundException(
//                "The artist id: " + artistId + "does not exist.");
//    }


    public void deleteConcertById(Long artistId, Long concertHallId){
        ConcertHall concertHall = concertHallRepository.findById(concertHallId).orElseThrow(() -> new NotFoundException("Concert hall does not exist."));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new NotFoundException("Artist does not exist."));

        Optional<Concert> concert = concertRepository.findByArtistAndConcertHall(artist, concertHall);
        if(concert.isEmpty()){
            throw new NotFoundException(
                    "The concert does not exist.");
        }
        concertRepository.deleteByArtistAndConcertHall(artist, concertHall);


    }
//
//    public ResponseEntity<?> deleteConcertById(Long artistId, Long concertHallId) {
//        ConcertHall concertHall = concertHallRepository.findConcert_HallById(concertHallId);
//        Optional<Artist> possibleArtist = artistRepository.findById(artistId);
//        if (possibleArtist.isPresent()) {
//            concertRepository.deleteByArtistAndConcertHall(possibleArtist.get(), concertHall);
//            return ResponseEntity.ok().body("Concert successfully deleted.");
//        }
//        throw new NotFoundException(
//                "The artist id: " + artistId + "does not exist.");
//    }


}
