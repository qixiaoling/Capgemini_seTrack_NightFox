package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.service.ConcertHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/concerthall")
public class ConcertHallController {

    private ConcertHallService concertHallService;

    @Autowired
    public ConcertHallController(ConcertHallService concertHallService) {
        this.concertHallService = concertHallService;
    }
    @GetMapping("/getall")
    public ResponseEntity<?> getAllConcertHalls(){
        return concertHallService.getAllConcertHalls();
    }

    @GetMapping("/getbyid/{concerthallId}")
    public ResponseEntity<?> getConcertHallById(@PathVariable("concerthallId") Long id){

        return concertHallService.getConcertHallById(id);
    }
    @PostMapping("/add")
    public ResponseEntity<?> addConcertHall(@Valid @RequestBody ConcertHall concertHall) {

        return concertHallService.addConcertHall(concertHall);
    }
    @PutMapping("/update/{concerthallId}")
    public ResponseEntity<?> updateConcertHallById (@PathVariable("concerthallId") Long id,
                                              @Valid @RequestBody ConcertHall concertHall){
        return concertHallService.updateConcertHallById(id, concertHall);
    }
    @DeleteMapping("/delete/{concerthallId}")
    public ResponseEntity<?> deleteConcertHallById(@PathVariable("concerthallId") Long id){
        return concertHallService.deleteConcertHallById(id);
    }










}
