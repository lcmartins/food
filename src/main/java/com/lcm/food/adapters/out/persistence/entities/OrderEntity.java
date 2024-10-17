package com.lcm.food.adapters.out.persistence.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tbl_order")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id")
    private UUID ID;

    private Long customerId;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.PERSIST)
    private List<OrderItemEntity> orderItemEntities;

    public UUID getID() {
        return ID;
    }

    public void setID(UUID ID) {
        this.ID = ID;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderItemEntity> getOrderItemEntities() {
        return orderItemEntities;
    }

    public void setOrderItemEntities(List<OrderItemEntity> orderItemEntities) {
        this.orderItemEntities = orderItemEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity orderEntity = (OrderEntity) o;
        return Objects.equals(ID, orderEntity.ID) && Objects.equals(customerId, orderEntity.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, customerId);
    }
}
