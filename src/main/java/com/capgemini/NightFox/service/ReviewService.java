package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Review;
import com.capgemini.NightFox.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ResponseEntity<?> getAllReviews() {
        List<Review> reviewList = new ArrayList<>();
        reviewRepository.findAll().forEach(reviewList::add);
        return ResponseEntity.ok().body(reviewList);
    }
    public ResponseEntity<?> getReviewById(Long id) {
        Optional<Review> possibleReview = reviewRepository.findById(id);
        if(possibleReview.isPresent()){
            return ResponseEntity.ok().body(possibleReview.get());
        }
        throw new NotFoundException(
                "Review id: " + id + "does not exist.");
    }

    public ResponseEntity<?> getReviewByArtistId (Long artistId){
        Optional<Review> possibleReview
    }





}
