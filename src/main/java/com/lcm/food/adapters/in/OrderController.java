package com.lcm.food.adapters.in;

import com.lcm.food.application.domain.entities.*;
import com.lcm.food.application.domain.usecases.OrderUseCase;
import com.lcm.food.application.domain.vos.CustomerVO;
import com.lcm.food.application.ports.in.OrderUseCasePort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("orders")
public class OrderController {

    private final OrderUseCasePort orderUseCase;

    public OrderController(OrderUseCase orderUseCase) {
        this.orderUseCase = orderUseCase;
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<Order> create(@RequestBody OrderRequestBody requestBody) throws Exception {
        Order createdOrder = this.orderUseCase
                .order(new CustomerVO(requestBody.getCustomerId()), requestBody.getOrderItems());
        return ResponseEntity.ok(createdOrder);
    }
}
