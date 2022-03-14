package com.capgemini.NightFox.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table
public class FoodAndBeverage_Category {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @NotBlank(message = "Category name is mandatory")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foodAndBeverage_category" )
    private List<FoodAndBeverage> foodAndBeverageList;

    public FoodAndBeverage_Category() {
    }

    public FoodAndBeverage_Category(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodAndBeverage> getFoodAndBeverageList() {
        return foodAndBeverageList;
    }

    public void setFoodAndBeverageList(List<FoodAndBeverage> foodAndBeverageList) {
        this.foodAndBeverageList = foodAndBeverageList;
    }
}
