package com.lcm.food.application.domain.entities;

import com.lcm.food.application.domain.vos.CustomerVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID ID;
    private final List<OrderItem> orderItems;
    private BigDecimal total;
    private final Long customerID;

    public Order(CustomerVO customerVO, List<OrderItem> orderItems, UUID ID) {
        this.orderItems = orderItems;
        this.total = BigDecimal.ZERO;
        this.customerID = customerVO.ID();
        this.ID = ID;
        this.calculatePrice();
    }

    public void calculatePrice() {
        this.orderItems.forEach((item)-> {
            BigDecimal price = item.getPrice();
            BigDecimal quantity  = BigDecimal.valueOf(item.getQuantity());
            this.total = this.total.add(price.multiply(quantity));
        });
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
    public BigDecimal getTotal() {
        return total;
    }

    public UUID getID() {
        return ID;
    }


    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Long getCustomerID() {
        return customerID;
    }

    public void add(OrderItem item) {
        this.orderItems.add(item);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("foods=").append(orderItems);
        sb.append(", total=").append(total);
        sb.append('}');
        return sb.toString();
    }

    public void setID(UUID id) {
        this.ID = id;
    }
}
