package com.capgemini.NightFox.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
public class FoodAndBeverage_Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodAndBeverage_category" )
    private List<FoodAndBeverage> foodAndBeverageList;

}
