package com.lcm.food.adapters.out.persistence.repositories;

import com.lcm.food.adapters.out.persistence.entities.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {
}
