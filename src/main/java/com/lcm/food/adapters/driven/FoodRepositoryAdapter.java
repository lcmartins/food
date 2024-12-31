package com.lcm.food.adapters.driven;

import com.lcm.food.adapters.driven.persistence.entities.FoodEntity;
import com.lcm.food.adapters.driven.persistence.entities.mappers.FoodMapper;
import com.lcm.food.adapters.driven.persistence.repositories.FoodRepository;

import com.lcm.food.application.domain.entities.Food;
import com.lcm.food.application.ports.driven.IFoodRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class FoodRepositoryAdapter implements IFoodRepositoryPort {

    private final FoodRepository foodRepository;

    public FoodRepositoryAdapter(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public List<Food> getByIDs(Set<Integer> foodIDs) {
        List<FoodEntity> foods = this.foodRepository.findAllById(foodIDs);

        List<Food> domainFoods = new ArrayList<>();
        foods.forEach(foodEntity -> domainFoods.add(FoodMapper.mapToDomain(foodEntity)));

        return domainFoods;
    }

}
