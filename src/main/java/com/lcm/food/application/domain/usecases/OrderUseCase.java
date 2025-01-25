package com.lcm.food.application.domain.usecases;

import com.lcm.food.application.domain.dtos.OrderDTO;
import com.lcm.food.application.domain.dtos.OrderItemDTO;
import com.lcm.food.application.domain.entities.Food;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.domain.entities.OrderItem;
import com.lcm.food.application.domain.vos.CustomerVO;
import com.lcm.food.application.ports.driven.IResiliencePort;
import com.lcm.food.application.ports.driver.OrderUseCasePort;
import com.lcm.food.application.ports.driven.IFoodRepositoryPort;
import com.lcm.food.application.ports.driven.IOrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderUseCase implements OrderUseCasePort {

    private final IFoodRepositoryPort foodRepositoryPort;
    private final IOrderRepositoryPort orderRepository;
    private final IResiliencePort resiliencePort;

    public OrderUseCase(IFoodRepositoryPort foodRepositoryPort, IOrderRepositoryPort orderRepository, IResiliencePort resiliencePort) {
        this.foodRepositoryPort = foodRepositoryPort;
        this.orderRepository = orderRepository;
        this.resiliencePort = resiliencePort;
    }

    public OrderDTO order(CustomerVO customer, List<OrderItemDTO> incomingFoodDTOs) throws Exception {
        Set<Integer> foodIDs = incomingFoodDTOs.stream().map(OrderItemDTO::foodId).collect(Collectors.toSet());
        List<Food> foods = this.foodRepositoryPort.getByIDs(foodIDs).stream().toList();
        List<OrderItem> newOrderDomainItems = new ArrayList<>();
        List<OrderItemDTO> outcommingFoodDTOs = consolidateOrderItens(incomingFoodDTOs, foods, newOrderDomainItems);

        Order order = new Order(customer, newOrderDomainItems, null);
        order = this.save(order);
        this.resiliencePort.save(order);
        return new OrderDTO(order.getID(), outcommingFoodDTOs, order.getTotal(), order.getCustomerID());
    }

    public List<OrderItemDTO> consolidateOrderItens(List<OrderItemDTO> incomingFoodDTOs, List<Food> foods, List<OrderItem> newOrderDomainItems) throws Exception {
        List<OrderItemDTO> outcommingFoodDTOs = new ArrayList<>();
        for (Food food : foods) {
            if (food.canBeSold()) {
                Integer quantity = incomingFoodDTOs
                        .stream()
                        .filter(orderItemDTO -> orderItemDTO.foodId().equals(food.getId()))
                        .map(OrderItemDTO::quantity)
                        .reduce(0, Integer::sum);

                OrderItem orderItem = new OrderItem(food, quantity);
                OrderItemDTO orderItemDTO = new OrderItemDTO(food.getId(), quantity, food.getPrice(), food.getPrice().multiply(BigDecimal.valueOf(quantity)), food.getName());
                newOrderDomainItems.add(orderItem);
                outcommingFoodDTOs.add(orderItemDTO);
                continue;
            }
            throw new Exception("Food cannot be sold");
        }
        return outcommingFoodDTOs;
    }


    public Order save(Order order) throws Exception {
        return this.orderRepository.save(order);
    }
}
