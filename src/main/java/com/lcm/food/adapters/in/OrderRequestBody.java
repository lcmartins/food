package com.lcm.food.adapters.in;

import com.lcm.food.application.domain.dtos.OrderItemDTO;

import java.util.List;

public class OrderRequestBody {

    private Integer customerId;


    private List<OrderItemDTO> orderItems;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemDTO> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDTO> orderItems) {
        this.orderItems = orderItems;
    }
}
