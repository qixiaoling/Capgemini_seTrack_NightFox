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

    public List<ConcertHall> getAllConcertHalls(){
        List<ConcertHall> concertHalls = new ArrayList<>();
        concertHallRepository.findAll().forEach(concertHalls::add);
        return concertHalls;
    }
    public void checkConcertHallIsExistsByConcertHallId(Long id) {

        if(concertHallRepository.existsById(id)){
            return;
        }else{
            throw new NotFoundException(
                    "Concert hall id: " + id + "does not exist.");
        }
    }

    public void addConcertHall(ConcertHall concertHall) {
        boolean existName = concertHallRepository.existsByHallName(concertHall.getHallName());
        if(existName){
            throw new BadRequestException(
                    "Concert hall: " + concertHall.getHallName() +  "exist already.");
        }
        concertHallRepository.save(concertHall);

    }

    public ConcertHall getConcertHallById(Long id) {
        Optional <ConcertHall> possibleConcertHall = concertHallRepository.findById(id);
        if(possibleConcertHall.isPresent()){
            return possibleConcertHall.get();
        }
        throw new NotFoundException(
                "The Concert hall id: " + id + "does not exist.");
    }

    public void updateConcertHallById(Long id, ConcertHall concertHall){
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
            return;
        }
        throw new NotFoundException(
                "The concert hall id: "+ id + "does not exist.");
    }

    public void deleteConcertHallById(Long id) {
        Optional<ConcertHall> possibleConcertHall = concertHallRepository.findById(id);
        if(possibleConcertHall.isPresent()){
            concertHallRepository.deleteById(id);
            return;
        }
        throw new NotFoundException(
                "The concert hall id: " + id + "does not exist.");
    }






}
