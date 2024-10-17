package com.lcm.food.application.domain.entities;

import java.math.BigDecimal;
import java.util.List;

public class Order {
    private final List<OrderItem> foods;
    private boolean isDone;
    private boolean isShipped;
    private BigDecimal total;

    public Order(List<OrderItem> foods) {
        this.foods = foods;
        this.total = BigDecimal.ZERO;
        this.calculatePrice();
    }

    public void calculatePrice() {
        this.foods.forEach((item)-> {
            BigDecimal price = item.getPrice();
            BigDecimal quantity  = BigDecimal.valueOf(item.getQuantity());
            this.total = this.total.add(price.multiply(quantity));
        });
    }

    public List<OrderItem> getFoods() {
        return foods;
    }

    public boolean isDone() {
        return isDone;
    }

    public boolean isShipped() {
        return isShipped;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void setShipped(boolean shipped) {
        isShipped = shipped;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void add(OrderItem item) {
        this.foods.add(item);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("foods=").append(foods);
        sb.append(", isDone=").append(isDone);
        sb.append(", isShipped=").append(isShipped);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }
}
