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
    public ConcertHallService(ConcertHallRepository concert_hallRepository) {
        this.concertHallRepository = concert_hallRepository;
    }

    public ResponseEntity<?> getAllConcert_Halls(){
        List<ConcertHall> concert_halls = new ArrayList<>();
        concertHallRepository.findAll().forEach(concert_halls::add);
        return ResponseEntity.ok().body(concert_halls);
    }

    public ResponseEntity<?> addConcert_Hall(ConcertHall concert_hall) {
        boolean existName = concertHallRepository.existsByHallName(concert_hall.getHallName());
        if(existName){
            throw new BadRequestException(
                    "Concert hall: " + concert_hall.getHallName() +  "exist already.");
        }
        concertHallRepository.save(concert_hall);
        return ResponseEntity.ok().body("This concert hall is added.");
    }

    public ResponseEntity<?> getConcert_HallById(Long id) {
        Optional <ConcertHall> possibleConcert_Hall = concertHallRepository.findById(id);
        if(possibleConcert_Hall.isPresent()){
            return ResponseEntity.ok().body(possibleConcert_Hall.get());
        }
        throw new NotFoundException(
                "The Concert hall id: " + id + "does not exist.");
    }

    public ResponseEntity<?> updateConcert_HallById(Long id, ConcertHall concert_hall){
        Optional<ConcertHall> possibleConcert_Hall = concertHallRepository.findById(id);
        if(possibleConcert_Hall.isPresent()){
            possibleConcert_Hall.get().setHallName(concert_hall.getHallName());
            possibleConcert_Hall.get().setStreet(concert_hall.getStreet());
            possibleConcert_Hall.get().setNumber(concert_hall.getNumber());
            possibleConcert_Hall.get().setCity(concert_hall.getCity());
            possibleConcert_Hall.get().setPhone(concert_hall.getPhone());
            possibleConcert_Hall.get().setCapacity(concert_hall.getCapacity());
            possibleConcert_Hall.get().setOpenAir(concert_hall.getOpenAir());
            concertHallRepository.save(possibleConcert_Hall.get());
            return ResponseEntity.ok().body("The concert hall is successfully updated.");
        }
        throw new NotFoundException(
                "The concert hall id: "+ id + "does not exist.");
    }

    public ResponseEntity<?> deleteConcert_HallById(Long id) {
        Optional<ConcertHall> possibleConcert_Hall = concertHallRepository.findById(id);
        if(possibleConcert_Hall.isPresent()){
            concertHallRepository.deleteById(id);
            return ResponseEntity.ok().body("The concert hall is successfully deleted.");
        }
        throw new NotFoundException(
                "The concert hall id: " + id + "does not exist.");
    }






}
