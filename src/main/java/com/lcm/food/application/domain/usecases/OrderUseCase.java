package com.lcm.food.application.domain.usecases;

import com.lcm.food.application.domain.dtos.OrderItemDTO;
import com.lcm.food.application.domain.entities.Food;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.domain.entities.OrderItem;
import com.lcm.food.application.domain.vos.CustomerVO;
import com.lcm.food.application.ports.in.OrderUseCasePort;
import com.lcm.food.application.ports.out.IFoodRepositoryPort;
import com.lcm.food.application.ports.out.IOrderRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderUseCase implements OrderUseCasePort {

    private final IFoodRepositoryPort foodRepositoryPort;
    private final IOrderRepositoryPort orderRepository;


    public OrderUseCase(IFoodRepositoryPort foodRepositoryPort, IOrderRepositoryPort orderRepository) {
        this.foodRepositoryPort = foodRepositoryPort;
        this.orderRepository = orderRepository;
    }

    public Order order(CustomerVO customer, List<OrderItemDTO> orderItemDTOS) throws Exception {
        Set<Integer> foodIDs = orderItemDTOS.stream().map(OrderItemDTO::foodId).collect(Collectors.toSet());
        List<Food> foods =  this.foodRepositoryPort.getByIDs(foodIDs).stream().toList();
        List<OrderItem> orderItems = new ArrayList<>();

        for (Food food : foods) {
            if(food.canBeSold()) {
                Integer quantity = orderItemDTOS
                        .stream()
                        .filter(orderItemDTO -> orderItemDTO.foodId().equals(food.getId()))
                        .map(OrderItemDTO::quantity)
                        .reduce(0, Integer::sum);

                OrderItem orderItem = new OrderItem(food, quantity);
                orderItems.add(orderItem);
                continue;
            }
            throw new Exception("Food cannot be sold");
        }

        Order order = new Order(orderItems);
        return this.save(order);
    }

    public Order make(OrderItem item) {
        return new Order(List.of(item));
    }


    public Order save(Order order) throws Exception {
        this.orderRepository.save(order);
        return order;
    }
}
