package com.lcm.food.application.domain.entities;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FoodTest {

    @Test
    void canBeSold() {

        Ingredient pao = new Ingredient("pao de brioche", 10);
        Ingredient hamburger = new Ingredient("hamburger de picanha", 11);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(pao);
        ingredients.add(hamburger);

        Food food = new Food.Builder()
                .withId(1)
                .withName("Hamburger picanhoso")
                .withIngredients(ingredients)
                .withPrice(BigDecimal.valueOf(100L))
                .build();


        assertTrue(food.canBeSold());
    }

    @Test
    void cannotBeSold() {

        Ingredient pao = new Ingredient("pao de brioche", 1);
        Ingredient hamburger = new Ingredient("hamburger de picanha", 11);
        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(pao);
        ingredients.add(hamburger);

        Food food = new Food.Builder()
                .withName("Hamburger picanhoso")
                .withIngredients(ingredients)
                .withPrice(BigDecimal.valueOf(100L))
                .build();


        assertFalse(food.canBeSold());
    }
}