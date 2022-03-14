package com.capgemini.NightFox.service;

import com.capgemini.NightFox.repository.ConcertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ConcertService {

    private ConcertRepository concertRepository;

    @Autowired
    public ConcertService(ConcertRepository concertRepository) {
        this.concertRepository = concertRepository;
    }

    public ResponseEntity<?> getAllConcert() {
        return null;
    }







}
