package com.capgemini.NightFox.model;

import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@EqualsAndHashCode
public class FoodAndBeverage_Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    @Column
    private Integer quantity;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private FoodAndBeverage foodAndBeverage;
    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppUser appUser;

    public FoodAndBeverage_Order() {
    }

    public FoodAndBeverage_Order(FoodAndBeverage foodAndBeverage, AppUser appUser) {
        this.foodAndBeverage = foodAndBeverage;
        this.appUser = appUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public FoodAndBeverage getFoodAndBeverage() {
        return foodAndBeverage;
    }

    public void setFoodAndBeverage(FoodAndBeverage foodAndBeverage) {
        this.foodAndBeverage = foodAndBeverage;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}
