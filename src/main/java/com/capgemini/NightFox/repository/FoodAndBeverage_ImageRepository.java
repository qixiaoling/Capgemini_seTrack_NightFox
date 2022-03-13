package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.Artist_Images;
import com.capgemini.NightFox.model.FoodAndBeverage_Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodAndBeverage_ImageRepository extends JpaRepository<FoodAndBeverage_Images, Long> {
}
