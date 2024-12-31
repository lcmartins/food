package com.lcm.food.application.ports.driven;

import com.lcm.food.application.domain.entities.Food;

import java.util.List;
import java.util.Set;

public interface IFoodRepositoryPort {
    List<Food> getByIDs(Set<Integer> foodIDs);
}
