package com.lcm.food.application.ports.driven;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.lcm.food.application.domain.entities.Order;

import java.util.concurrent.ExecutionException;

public interface IResiliencePort {
    void save(Order order) throws ExecutionException, InterruptedException, JsonProcessingException;
}
