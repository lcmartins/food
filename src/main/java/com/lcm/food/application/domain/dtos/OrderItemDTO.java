package com.lcm.food.application.domain.dtos;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record OrderItemDTO(Integer foodId, Integer quantity, BigDecimal foodPrice, BigDecimal total, String foodName){
    public BigDecimal foodPrice() {
        return foodPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal total() {
        return total.setScale(2, RoundingMode.HALF_EVEN);
    }
}