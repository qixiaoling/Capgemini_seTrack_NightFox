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

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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

    public Concert getConcertByConcertId(Long id){
        Optional <Concert> possibleConcert = concertRepository.findById(id);
        if(possibleConcert.isPresent()){
            return possibleConcert.get();
        }
        throw new NotFoundException(
                "Concert does not exist.");
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

    public void addConcert(Concert concert) throws IOException {
//        Boolean concertHallExist = concertHallRepository.existsById(concert.getConcertHall().getId());
//        Boolean artistExist = artistRepository.existsById(concert.getArtist().getId());
//
//        if (concertHallExist && artistExist) {
//            concertRepository.saveAndFlush(concert);
//            return;
//        } else if(!concertHallExist){
//            throw new NotFoundException(
//                    "Concert hall id: " + concert.getConcertHall().getId() + "is not found.");
//        } else {
//            throw new NotFoundException(
//                    "Artist id: " + concert.getArtist().getId() + "is not found.");
//        }

        // temporary fix

        URL validationUrlArtist = new URL(String.format("http://localhost:8082/artist/checkExists/"+ concert.getArtist().getId().toString()));
        HttpURLConnection connectionArtist = (HttpURLConnection)validationUrlArtist.openConnection();
        URL validationUrlConcertHall = new URL(String.format("http://localhost:8082/concerthall/checkExists/"+ concert.getConcertHall().getId().toString()));
        HttpURLConnection connectionConcertHall = (HttpURLConnection)validationUrlConcertHall.openConnection();

        if(connectionArtist.getResponseCode() == HttpURLConnection.HTTP_OK && connectionConcertHall.getResponseCode() == HttpURLConnection.HTTP_OK){
            concert.setArtist(artistRepository.saveAndFlush(concert.getArtist()));
            concert.setConcertHall(concertHallRepository.saveAndFlush(concert.getConcertHall()));
            concertRepository.saveAndFlush(concert);
            return;
        }
        else{
            if(connectionArtist.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new NotFoundException("This artist id: " + concert.getArtist().getId() + "does not exist.");
            }
            if(connectionConcertHall.getResponseCode() != HttpURLConnection.HTTP_OK){
                throw new NotFoundException("This concert hall id: " + concert.getConcertHall().getId() + "does not exist.");
            }
        }
    }
    public void updateConcertById(Long concertId, Concert dataConcert){
        Concert concert = concertRepository.findById(concertId).orElseThrow(() -> new NotFoundException("Concert does not exist."));

        concert.setPrice(dataConcert.getPrice());
        concert.setDescription(dataConcert.getDescription());
        concert.setTime(dataConcert.getTime());

        return;

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
