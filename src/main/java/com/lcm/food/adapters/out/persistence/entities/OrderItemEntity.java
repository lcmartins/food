package com.lcm.food.adapters.out.persistence.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tbl_order_item")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID ID;

    private String foodName;

    private BigDecimal foodPrice;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public BigDecimal getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(BigDecimal foodPrice) {
        this.foodPrice = foodPrice;
    }

    public OrderEntity getOrderEntity() {
        return orderEntity;
    }

    public void setOrderEntity(OrderEntity orderEntityID) {
        this.orderEntity = orderEntityID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemEntity orderItemEntity = (OrderItemEntity) o;
        return Objects.equals(ID, orderItemEntity.ID) && Objects.equals(foodName, orderItemEntity.foodName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, foodName);
    }
}
