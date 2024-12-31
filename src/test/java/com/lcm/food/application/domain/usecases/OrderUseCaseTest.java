package com.lcm.food.application.domain.usecases;

import com.lcm.food.application.domain.dtos.OrderDTO;
import com.lcm.food.application.domain.dtos.OrderItemDTO;
import com.lcm.food.application.domain.entities.Food;
import com.lcm.food.application.domain.entities.Ingredient;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.domain.entities.OrderItem;
import com.lcm.food.application.domain.vos.CustomerVO;
import com.lcm.food.application.ports.driven.IFoodRepositoryPort;
import com.lcm.food.application.ports.driven.IOrderRepositoryPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderUseCaseTest {

    @Mock
    public IFoodRepositoryPort foodRepositoryPort;

    @Mock
    public IOrderRepositoryPort orderRepository;

    @InjectMocks
    public OrderUseCase orderUseCase;


    @Before()
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_consolidate_orderItems() throws Exception {
        //arrange
        List<OrderItemDTO> intommingOrderItemDTOs = new ArrayList<>();
        intommingOrderItemDTOs.add(new OrderItemDTO(1, 3, null, null, null));
        intommingOrderItemDTOs.add(new OrderItemDTO(1, 3, null, null, null));
        intommingOrderItemDTOs.add(new OrderItemDTO(2, 3, null, null, null));

        Ingredient pao = new Ingredient("pao de brioche", 10);
        Ingredient hamburger = new Ingredient("hamburger de picanha", 11);
        Ingredient picles = new Ingredient("picles", 12);

        List<Food> databaseFoods = List.of(new Food.Builder()
                        .withId(1)
                        .withName("Hamburger picanhoso")
                        .withIngredients(new HashSet<>(List.of(pao, hamburger)))
                        .withPrice(BigDecimal.valueOf(30.55))
                        .build(),
                new Food.Builder()
                        .withId(2)
                        .withName("premiumn Hamburger")
                        .withIngredients(new HashSet<>(List.of(pao, hamburger, picles)))
                        .withPrice(BigDecimal.valueOf(40.90))
                        .build());

        List<OrderItem> orderItems = new ArrayList<>();

        //act
        List<OrderItemDTO> outCommingOrderItemDTOs = orderUseCase.consolidateOrderItens(intommingOrderItemDTOs, databaseFoods, orderItems);
        Order order = new Order(new CustomerVO(1L), orderItems, null);

        //assert
        assertEquals(new BigDecimal("306.00"), order.getTotal());
        assertEquals(1L, order.getCustomerID());
        assertEquals(2, order.getOrderItems().size());
        assertEquals(2, outCommingOrderItemDTOs.size());

        assertEquals(1, outCommingOrderItemDTOs.get(0).foodId());
        assertEquals(6, outCommingOrderItemDTOs.get(0).quantity());
        assertEquals(new BigDecimal("30.55"), outCommingOrderItemDTOs.get(0).foodPrice());
        assertEquals(new BigDecimal("183.30"), outCommingOrderItemDTOs.get(0).total());
        assertEquals("Hamburger picanhoso", outCommingOrderItemDTOs.get(0).foodName());

        assertEquals(2, outCommingOrderItemDTOs.get(1).foodId());
        assertEquals(3, outCommingOrderItemDTOs.get(1).quantity());
        assertEquals(new BigDecimal("40.90"), outCommingOrderItemDTOs.get(1).foodPrice());
        assertEquals(new BigDecimal("122.70"), outCommingOrderItemDTOs.get(1).total());
        assertEquals("premiumn Hamburger", outCommingOrderItemDTOs.get(1).foodName());
    }
}