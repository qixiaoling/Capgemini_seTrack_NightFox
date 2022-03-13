package com.capgemini.NightFox.repository;

import com.capgemini.NightFox.model.AppUser;
import com.capgemini.NightFox.model.FoodAndBeverage;
import com.capgemini.NightFox.model.FoodAndBeverage_Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodAndBeverage_OrderRepository extends JpaRepository <FoodAndBeverage_Order, Long> {

        List<FoodAndBeverage_Order> findByAppUser(AppUser appUser);
        FoodAndBeverage_Order findByAppUserAndFoodAndBeverage(AppUser appUser, FoodAndBeverage foodAndBeverage);

        void deleteByAppUserAndFoodAndBeverage(AppUser appUser, FoodAndBeverage foodAndBeverage);


}
