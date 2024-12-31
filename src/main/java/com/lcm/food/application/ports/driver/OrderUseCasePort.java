package com.lcm.food.application.ports.driver;

import com.lcm.food.application.domain.dtos.OrderDTO;
import com.lcm.food.application.domain.dtos.OrderItemDTO;
import com.lcm.food.application.domain.entities.Food;
import com.lcm.food.application.domain.entities.OrderItem;
import com.lcm.food.application.domain.vos.CustomerVO;

import java.util.List;

public interface OrderUseCasePort {
    OrderDTO order(CustomerVO customer, List<OrderItemDTO> orderItemDTOS) throws Exception;
    List<OrderItemDTO> consolidateOrderItens(List<OrderItemDTO> incomingFoodDTOs, List<Food> foods, List<OrderItem> newOrderDomainItems) throws Exception;
}
