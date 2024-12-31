package com.lcm.food.adapters.driven.persistence.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tbl_ingredient")
public class IngredientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ingredientId;

    private String name;

    private  int quantityInStock;

    @ManyToMany(mappedBy = "ingredients")
    private List<FoodEntity> food;


    public IngredientEntity() {
    }

    public IngredientEntity(Integer ingredientId, String name, int quantityInStock) {
        this.ingredientId = ingredientId;
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodEntity> getFood() {
        return food;
    }

    public void setFood(List<FoodEntity> food) {
        this.food = food;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientEntity that = (IngredientEntity) o;
        return quantityInStock == that.quantityInStock && Objects.equals(ingredientId, that.ingredientId) && Objects.equals(name, that.name) && Objects.equals(food, that.food);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, name, quantityInStock, food);
    }
}
