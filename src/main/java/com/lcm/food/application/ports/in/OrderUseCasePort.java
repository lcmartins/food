package com.lcm.food.application.ports.in;

import com.lcm.food.application.domain.dtos.OrderItemDTO;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.domain.vos.CustomerVO;

import java.util.List;

public interface OrderUseCasePort {
    Order order(CustomerVO customer, List<OrderItemDTO> orderItemDTOS) throws Exception;
}
