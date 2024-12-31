package com.lcm.food.application.domain.dtos;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderDTO(UUID ID,
                       List<OrderItemDTO> orderItems,
                       BigDecimal total,
                       Long customerID
) {
}
