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
    @Column(name = "individual_order_id")
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
}
