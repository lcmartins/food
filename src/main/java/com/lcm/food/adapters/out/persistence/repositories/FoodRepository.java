package com.lcm.food.adapters.out.persistence.repositories;

import com.lcm.food.adapters.out.persistence.entities.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Integer> {
}
