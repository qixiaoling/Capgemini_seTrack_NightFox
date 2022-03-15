package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist;

import com.capgemini.NightFox.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ReviewRepository extends JpaRepository <Review, Long> {

//    Optional<Review> findById(Long id);
    List<Review> findByArtist(Artist artist);



}
