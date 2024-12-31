package com.lcm.food.adapters.driven.persistence.repositories;

import com.lcm.food.adapters.driven.persistence.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

}