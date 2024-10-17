//package com.lcm.food.adapters.out.persistence.entities.mappers;
//
//import com.lcm.food.adapters.out.persistence.entities.FoodEntity;
//import com.lcm.food.application.domain.entities.Food;
//import org.junit.Test;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.math.BigDecimal;
//import java.util.HashSet;
//import java.util.Set;
//
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//@ExtendWith(MockitoExtension.class)
//public class FoodMapperTest {
//
//    @Test
//    public void test_number_one() {
//        FoodEntity foodEntity = new FoodEntity();
//        foodEntity.setID(1);
//        foodEntity.setName("hamburger");
//        foodEntity.setPrice(BigDecimal.TEN);
//
//        Set<IngredientEntity> ingredientEntities = new HashSet<>();
//        IngredientEntity pao = new IngredientEntity();
//        pao.setID(1);
//        pao.setName("pao de brioche");
//        pao.setQuantityInStock(10);
//
//        IngredientEntity hamburger = new IngredientEntity();
//        hamburger.setID(2);
//        hamburger.setName("hamburger de picanha");
//        hamburger.setQuantityInStock(10);
//
//        ingredientEntities.add(pao);
//        ingredientEntities.add(hamburger);
//        foodEntity.setIngredients(ingredientEntities);
//
//        Food domain = FoodMapper.mapToDomain(foodEntity);
//
//        Assertions.assertEquals(domain.getName(), foodEntity.getName());
//        Assertions.assertEquals(domain.getIngredients().size(), foodEntity.getIngredients().size());
//    }
//
//}