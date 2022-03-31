package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Concert;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.service.ConcertService;
import org.apache.coyote.Response;
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

        return ResponseEntity.ok().body(concertService.getAllConcerts());
    }
    @GetMapping("/getbyconcertid/{concertId}")
    public ResponseEntity<?> getConcertByConcertId(@PathVariable ("concertId") Long concertId){
        return ResponseEntity.ok().body(concertService.getConcertByConcertId(concertId));
    }

    @GetMapping("/getbyid/{artistId}")
    public ResponseEntity<?> getAllConcertsByArtistId(@PathVariable("artistId") Long id){

        return ResponseEntity.ok().body(concertService.getAllConcertsByArtistId(id));
    }

    @GetMapping("/getbyname/{artistName}")
    public ResponseEntity<?> getAllConcertsByArtistBandName(@PathVariable("artistName") String bandName){


        return ResponseEntity.ok().body(concertService.getAllConcertsByArtistBandName(bandName));
    }
    @PostMapping("/addconcert/{artistId}/{concerthallId}")
    public ResponseEntity<?> addConcertHallToArtist(@PathVariable("artistId") Long artistId,
                                                    @PathVariable("concerthallId") Long concerthallId,
                                                    @Valid @RequestBody Concert concert) {

        concertService.addConcertHallToArtist(artistId, concerthallId, concert);
        return ResponseEntity.ok("The Concert is added to the artist.");
    }

    @PutMapping("/updateConcert/{concertId}")
    public ResponseEntity<?> updateConcert(@PathVariable("concertId") Long concertId,
                                           @Valid @RequestBody Concert concert) {
        concertService.updateConcertById(concertId, concert);
        return ResponseEntity.ok("The concert is successfully updated.");
    }

    @DeleteMapping("/delete/{artistId}/{concerthallId}")
    public ResponseEntity<?> deleteConcertById(@PathVariable("artistId") Long artistId,
                                                   @PathVariable("concerthallId") Long concerthallId){
        concertService.deleteConcertById(artistId, concerthallId);
        return ResponseEntity.ok().body("The concert is successfully deleted.");
    }









}
