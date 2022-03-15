package com.capgemini.NightFox.service;

import com.capgemini.NightFox.Exception.NotFoundException;
import com.capgemini.NightFox.model.Artist;
import com.capgemini.NightFox.model.Review;
import com.capgemini.NightFox.repository.ArtistRepository;
import com.capgemini.NightFox.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private ArtistRepository artistRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository, ArtistRepository artistRepository) {
        this.reviewRepository = reviewRepository;
        this.artistRepository = artistRepository;
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

    public ResponseEntity<?> getReviewsByArtistId (Long artistId){
        Optional<Artist> possibleArtist = artistRepository.findById(artistId);
        if(possibleArtist.isPresent()){
            List<Review> reviews = new ArrayList<>();
            reviews.addAll(reviewRepository.findByArtist(possibleArtist.get()));
            return ResponseEntity.ok().body(reviews);
        }
        throw new NotFoundException(
                "Artist id: " + artistId + "does not exist.");
    }

    public ResponseEntity<?> addReviewToArtist (Long artistId, Review review){
        Optional<Artist> artistDB = artistRepository.findById(artistId);
        if(artistDB.isPresent()){
            review.setArtist(artistDB.get());
            reviewRepository.save(review);
            return ResponseEntity.ok().body("Review is successfully added.");
        }
        throw new NotFoundException(
                "Artist id: " + artistId + "does not exist.");
    }

    public ResponseEntity<?> updateReviewById(Long reviewId, Review review){
        Optional<Review> possibleReview = reviewRepository.findById(reviewId);
        if(possibleReview.isPresent()){
            possibleReview.get().setNameReviewer(review.getNameReviewer());
            possibleReview.get().setDescription(review.getDescription());
            possibleReview.get().setLike(review.getLike());
            reviewRepository.save(possibleReview.get());
            return ResponseEntity.ok().body("The review is updated.");
        }
        throw new NotFoundException(
                "Review id: " + reviewId + "does not exist.");
    }

    public ResponseEntity<?> deleteReviewById(Long reviewId){
        Optional<Review> possibleReview = reviewRepository.findById(reviewId);
        if(possibleReview.isPresent()){
            reviewRepository.deleteById(reviewId);
            return ResponseEntity.ok().body("The review is deleted.");
        }
        throw new NotFoundException(
                "Review id: " + reviewId + "does not exist.");
    }







}
