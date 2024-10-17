package com.lcm.food.application.domain.entities;

import java.math.BigDecimal;

public class OrderItem {
    private final String name;
    private final BigDecimal price;
    private final Integer quantity;

    public OrderItem(Food food, Integer quantity) {
        this.name = food.getName();
        this.price = food.getPrice();
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
