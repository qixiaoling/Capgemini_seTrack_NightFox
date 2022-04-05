package com.capgemini.NightFox.controller;

import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.service.ConcertHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@CrossOrigin(origins ="http://localhost:4200")
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
        return ResponseEntity.ok().body(concertHallService.getAllConcertHalls());
    }

    @GetMapping("/getbyid/{concerthallId}")
    public ResponseEntity<?> getConcertHallById(@PathVariable("concerthallId") Long id){

        return ResponseEntity.ok().body(concertHallService.getConcertHallById(id));
    }
    @PostMapping("/add")
    public ResponseEntity<?> addConcertHall(@Valid @RequestBody ConcertHall concertHall) {

        concertHallService.addConcertHall(concertHall);
        return ResponseEntity.ok().body("The concert hall is successfully added.");


    }
    @PutMapping("/update/{concerthallId}")
    public ResponseEntity<?> updateConcertHallById (@PathVariable("concerthallId") Long id,
                                              @Valid @RequestBody ConcertHall concertHall){
        concertHallService.updateConcertHallById(id, concertHall);
        return ResponseEntity.ok().body("The concert hall is successfully updated.");
    }
    @DeleteMapping("/delete/{concerthallId}")
    public ResponseEntity<?> deleteConcertHallById(@PathVariable("concerthallId") Long id){
        concertHallService.deleteConcertHallById(id);
        return ResponseEntity.ok().body("The concert hall is successfully deleted.");
    }










}
