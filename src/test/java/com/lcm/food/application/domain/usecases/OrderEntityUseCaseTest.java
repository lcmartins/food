//package com.lcm.food.application.domain.usecases;
//
//import com.lcm.food.adapters.out.OrderRepositoryAdapter;
//import com.lcm.food.application.domain.entities.*;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//class OrderEntityUseCaseTest {
//
//    @Mock(name="dynamoDbOrderRepository")
//    OrderRepositoryAdapter orderRepository;
//
//    @InjectMocks
//    OrderUseCase orderUseCase;
//
//    @Before()
//    public void setup(){
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void save() throws Exception {
//        Ingredient hanburger = new Ingredient("amburger de picanha", 10);
//        Ingredient paoBrioche = new Ingredient("pao de brioche", 70);
//
//        Food food = new Food.Builder().withName("hamburge picanhoso")
//                .withPrice(BigDecimal.TEN)
//                .withProduct(hanburger)
//                .withProduct(paoBrioche)
//                .build();
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        Order order = orderUseCase.make(new OrderItem(food, 2));
//
//        orderUseCase.save(order);
//
//        verify(orderRepository, times(1)).save(any());
//    }
//}