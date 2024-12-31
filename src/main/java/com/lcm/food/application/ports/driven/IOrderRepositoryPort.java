package com.lcm.food.application.ports.driven;

import com.lcm.food.application.domain.entities.Order;

public interface IOrderRepositoryPort {
    Order save(Order order);
}
