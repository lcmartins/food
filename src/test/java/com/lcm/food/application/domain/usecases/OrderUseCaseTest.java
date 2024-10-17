package com.lcm.food.application.domain.usecases;

import com.lcm.food.application.domain.dtos.OrderItemDTO;
import com.lcm.food.application.domain.entities.Food;
import com.lcm.food.application.domain.entities.Ingredient;
import com.lcm.food.application.domain.entities.Order;
import com.lcm.food.application.domain.vos.CustomerVO;
import com.lcm.food.application.ports.out.IFoodRepositoryPort;
import com.lcm.food.application.ports.out.IOrderRepositoryPort;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
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
    public void order() throws Exception {
        List<OrderItemDTO> orderItemDTOS = new ArrayList<>();
        orderItemDTOS.add(new OrderItemDTO(1, 2));
        orderItemDTOS.add(new OrderItemDTO(1, 3));
        Ingredient pao = new Ingredient("pao de brioche", 10);
        Ingredient hamburger = new Ingredient("hamburger de picanha", 11);

        Food food = new Food.Builder()
                .withId(1)
                .withName("Hamburger picanhoso")
                .withIngredients(new HashSet<>(List.of(pao, hamburger)))
                .withPrice(BigDecimal.valueOf(36.50))
                .build();

        when(this.foodRepositoryPort.getByIDs(anySet())).thenReturn(List.of(food));

        doAnswer(invocationOnMock -> null).when(orderRepository).save(any());

        Order order = orderUseCase.order(new CustomerVO(10), orderItemDTOS);

        assertEquals(BigDecimal.valueOf(36.50).multiply(BigDecimal.valueOf(5)), order.getTotal());
    }
}