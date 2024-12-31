package com.lcm.food.adapters.driven.persistence.repositories;

import com.lcm.food.adapters.driven.persistence.entities.IngredientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<IngredientEntity, Integer> {
}
