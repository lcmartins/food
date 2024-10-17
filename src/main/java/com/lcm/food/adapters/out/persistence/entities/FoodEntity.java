package com.lcm.food.adapters.out.persistence.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "tbl_food")
public class FoodEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodId;

    private String name;

    private BigDecimal price;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "tbl_food_ingredient",
    joinColumns = @JoinColumn(name = "food_id"),
    inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
    private List<IngredientEntity> ingredients;

    public FoodEntity() {
    }

    public FoodEntity(Integer foodId, String name, BigDecimal price) {
        this.foodId = foodId;
        this.name = name;
        this.price = price;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FoodEntity foodEntity = (FoodEntity) o;
        return Objects.equals(foodId, foodEntity.foodId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(foodId);
    }
}
