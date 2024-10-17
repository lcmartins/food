package com.lcm.food.adapters.out;

import com.lcm.food.adapters.out.persistence.entities.OrderEntity;
import com.lcm.food.adapters.out.persistence.entities.mappers.FoodMapper;
import com.lcm.food.adapters.out.persistence.repositories.OrderRepository;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.ports.out.IOrderRepositoryPort;
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
        this.orderRepository.saveAndFlush(orderEntity);
        return order;
    }
}
