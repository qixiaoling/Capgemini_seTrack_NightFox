package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.BadRequestException;
import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.ConcertHall;
import com.capgemini.NightFox.repository.ConcertHallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ConcertHallService {
    private ConcertHallRepository concertHallRepository;
    @Autowired
    public ConcertHallService(ConcertHallRepository concertHallRepository) {
        this.concertHallRepository = concertHallRepository;
    }

    public ResponseEntity<?> getAllConcertHalls(){
        List<ConcertHall> concertHalls = new ArrayList<>();
        concertHallRepository.findAll().forEach(concertHalls::add);
        return ResponseEntity.ok().body(concertHalls);
    }

    public ResponseEntity<?> addConcertHall(ConcertHall concertHall) {
        boolean existName = concertHallRepository.existsByHallName(concertHall.getHallName());
        if(existName){
            throw new BadRequestException(
                    "Concert hall: " + concertHall.getHallName() +  "exist already.");
        }
        concertHallRepository.save(concertHall);
        return ResponseEntity.ok().body("This concert hall is added.");
    }

    public ResponseEntity<?> getConcertHallById(Long id) {
        Optional <ConcertHall> possibleConcertHall = concertHallRepository.findById(id);
        if(possibleConcertHall.isPresent()){
            return ResponseEntity.ok().body(possibleConcertHall.get());
        }
        throw new NotFoundException(
                "The Concert hall id: " + id + "does not exist.");
    }

    public ResponseEntity<?> updateConcertHallById(Long id, ConcertHall concertHall){
        Optional<ConcertHall> possibleConcertHall = concertHallRepository.findById(id);
        if(possibleConcertHall.isPresent()){
            possibleConcertHall.get().setHallName(concertHall.getHallName());
            possibleConcertHall.get().setStreet(concertHall.getStreet());
            possibleConcertHall.get().setNumber(concertHall.getNumber());
            possibleConcertHall.get().setCity(concertHall.getCity());
            possibleConcertHall.get().setPhone(concertHall.getPhone());
            possibleConcertHall.get().setCapacity(concertHall.getCapacity());
            possibleConcertHall.get().setOpenAir(concertHall.getOpenAir());
            concertHallRepository.save(possibleConcertHall.get());
            return ResponseEntity.ok().body("The concert hall is successfully updated.");
        }
        throw new NotFoundException(
                "The concert hall id: "+ id + "does not exist.");
    }

    public ResponseEntity<?> deleteConcertHallById(Long id) {
        Optional<ConcertHall> possibleConcertHall = concertHallRepository.findById(id);
        if(possibleConcertHall.isPresent()){
            concertHallRepository.deleteById(id);
            return ResponseEntity.ok().body("The concert hall is successfully deleted.");
        }
        throw new NotFoundException(
                "The concert hall id: " + id + "does not exist.");
    }






}
