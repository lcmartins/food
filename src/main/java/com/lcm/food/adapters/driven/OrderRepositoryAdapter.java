package com.lcm.food.adapters.driven;

import com.lcm.food.adapters.driven.persistence.entities.OrderEntity;
import com.lcm.food.adapters.driven.persistence.entities.mappers.FoodMapper;
import com.lcm.food.adapters.driven.persistence.repositories.OrderRepository;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.ports.driven.IOrderRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


@Repository
public class OrderRepositoryAdapter implements IOrderRepositoryPort {

    private final OrderRepository orderRepository;

    public OrderRepositoryAdapter(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Override
    @Transactional
    public Order save(Order order) {
        System.out.println("Order: ".concat(order.toString()).concat(" saved to mysql"));
        OrderEntity orderEntity = FoodMapper.mapToEntity(order);
        orderEntity = this.orderRepository.saveAndFlush(orderEntity);
        order.setID(orderEntity.getID());
        return order;
    }
}
