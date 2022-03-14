package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.service.ConcertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/concert")
public class ConcertController {

    private ConcertService concertService;

    @Autowired
    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getAllConcerts(){

        return concertService.getAllConcerts();
    }

    @GetMapping("/getbyid/{artistId}")
    public ResponseEntity<?> getAllConcertsByArtistId(@PathVariable("artistId") Long id){

        return concertService.getAllConcertsByArtistId(id);
    }

    @GetMapping("/getbyname/{artistName}")
    public ResponseEntity<?> getAllConcertsByArtistBandName(@PathVariable("artistName") String bandName){

        return concertService.getAllConcertsByArtistBandName(bandName);
    }
    @PostMapping("/addconcert/{artistId}/{concerthallId}")
    public ResponseEntity<?> addConcertHallToArtist(@PathVariable("artistId") Long artistId,
                                                    @PathVariable("concerthallId") Long concerthallId) {

        return concertService.addConcertHallToArtist(artistId, concerthallId);
    }
    @PutMapping("/updateconcertinfo/{artistId}/{concerthallId}")
    public ResponseEntity<?> updateConcertHallById (@PathVariable("artistId") Long artistId,
                                                    @PathVariable("concerthallId") Long concerthallId,
                                                    @Valid @RequestBody Concert concert){
        return concertService.addConcertDetailedInfo(artistId, concerthallId, concert);
    }

    @DeleteMapping("/delete/{artistId}/{concerthallId}")
    public ResponseEntity<?> deleteConcertHallById(@PathVariable("artistId") Long artistId,
                                                   @PathVariable("concerthallId") Long concerthallId){
        return concertService.deleteConcertById(artistId, concerthallId);
    }









}