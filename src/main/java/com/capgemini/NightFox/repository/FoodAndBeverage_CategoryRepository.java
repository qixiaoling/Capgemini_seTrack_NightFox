package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.FoodAndBeverage_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodAndBeverage_CategoryRepository extends JpaRepository <FoodAndBeverage_Category, Long> {

    FoodAndBeverage_Category findByName(String name);
    boolean existsByName(String name);

}
