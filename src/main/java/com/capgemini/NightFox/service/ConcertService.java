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

    public List<Concert> getAllConcerts() {
        List<Concert> concerts = new ArrayList<>();
        concertRepository.findAll().forEach(concerts::add);
        return concerts;
    }

    public List<Concert>  getAllConcertsByArtistId(Long id) {
        Optional<Artist> possibleArtist = artistRepository.findById(id);
        if (possibleArtist.isPresent()) {
            List<Concert> concertList = new ArrayList<>();
            List<Concert> generatedList = concertRepository.findByArtist(possibleArtist.get());
            for(Concert c : generatedList){
                concertList.add(c);
            }
            return concertList;
        }else{
            throw new NotFoundException(
                    "Artist id: " + id + "does not exist.");
        }

    }

    public List<Concert> getAllConcertsByArtistBandName(String bandName) {
        Optional<Artist> possibleArtist = artistRepository.findByBandName(bandName);
        if (possibleArtist.isPresent()) {
            List<Concert> concertList = new ArrayList<>();
            concertList.addAll(concertRepository.findByArtist(possibleArtist.get()));
            return concertList;
        }
        throw new NotFoundException(
                "Artist name: " + bandName + "does not exist.");
    }

    public void addConcertHallToArtist(Long artistId, Long concertHallId, Concert concert) {
        ConcertHall concertHall = concertHallRepository.findById(concertHallId).orElseThrow(() -> new NotFoundException("Concert hall does not exist."));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new NotFoundException("Artist does not exist."));

        Optional<Concert> oncertDB = concertRepository.findByArtistAndConcertHall(artist, concertHall);
        if (oncertDB.isPresent()) {
            throw new BadRequestException("This concert hall is already added to the artist.");
        }else{
            Concert concert_added = new Concert(artist, concertHall);
            concert_added.setArtist(artist);
            concert_added.setConcertHall(concertHall);
            concert_added.setTime(concert.getTime());
            concert_added.setPrice(concert.getPrice());
            concert_added.setDescription(concert.getDescription());
            concert_added.setTime(concert.getTime());
            concertRepository.save(concert_added);
            return;
        }
    }


    public void deleteConcertById(Long artistId, Long concertHallId){
        ConcertHall concertHall = concertHallRepository.findById(concertHallId).orElseThrow(() -> new NotFoundException("Concert hall does not exist."));
        Artist artist = artistRepository.findById(artistId).orElseThrow(() -> new NotFoundException("Artist does not exist."));

        Optional<Concert> concert = concertRepository.findByArtistAndConcertHall(artist, concertHall);
        if(concert.isEmpty()){
            throw new NotFoundException(
                    "The concert does not exist.");
        }
        concertRepository.deleteByArtistAndConcertHall(artist, concertHall);
        return;


    }



}
