package com.lcm.food.application.domain.entities;

public class Ingredient {
    private Integer ID;
    private final String name;
    private final int quantityInStock;

    public Ingredient(String name, int quantityInStock) {
        this.name = name;
        this.quantityInStock = quantityInStock;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }
}
