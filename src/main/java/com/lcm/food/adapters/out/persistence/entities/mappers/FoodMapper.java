package com.lcm.food.adapters.out.persistence.entities.mappers;

import com.lcm.food.adapters.out.persistence.entities.FoodEntity;
import com.lcm.food.adapters.out.persistence.entities.IngredientEntity;
import com.lcm.food.adapters.out.persistence.entities.OrderEntity;
import com.lcm.food.adapters.out.persistence.entities.OrderItemEntity;
import com.lcm.food.application.domain.entities.Food;
import com.lcm.food.application.domain.entities.Ingredient;
import com.lcm.food.application.domain.entities.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FoodMapper {
    public static Food mapToDomain(FoodEntity foodEntity) {
        Food.Builder food = new Food.Builder();
        Set<Ingredient> ingredients = new HashSet<>();
        foodEntity.getIngredients().forEach(ingredientEntity -> {
            ingredients.add(mapToDomain(ingredientEntity));
        });
        food.withId(foodEntity.getFoodId())
                .withName(foodEntity.getName())
                .withPrice(foodEntity.getPrice())
                .withIngredients(ingredients)
                .build();

        return food.build();
    }

    public static Ingredient mapToDomain(IngredientEntity ingredientEntity) {
        return new Ingredient(ingredientEntity.getName(), ingredientEntity.getQuantityInStock());
    }

    public static OrderEntity mapToEntity(Order order) {
        OrderEntity orderEntity = new OrderEntity();
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        order.getFoods().forEach(orderItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setFoodName(orderItem.getName());
            orderItemEntity.setFoodPrice(orderItem.getPrice());
            orderItemEntity.setOrderEntity(orderEntity);
            orderItemEntities.add(orderItemEntity);
        });

        orderEntity.setOrderItemEntities(orderItemEntities);
        orderEntity.setCustomerId(1L);

        return orderEntity;
    }
}
