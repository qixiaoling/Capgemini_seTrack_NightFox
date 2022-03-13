package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.FoodAndBeverage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodAndBeverageRepository extends JpaRepository <FoodAndBeverage,Long> {

    FoodAndBeverage findFoodAndBeverageById(Long id);


}
