package com.lcm.food.adapters.driver;

import com.lcm.food.application.domain.dtos.OrderItemDTO;

import java.util.List;

public class OrderRequestBody {

    private Long customerId;

    private List<OrderItemDTO> orderItems;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
