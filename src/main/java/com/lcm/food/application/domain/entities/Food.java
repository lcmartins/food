package com.lcm.food.application.domain.entities;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

public class Food {

    private Integer Id;

    private String name;

    private BigDecimal price;

    private Set<Ingredient> ingredients;

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public boolean canBeSold() {
        return Objects.nonNull(this.name) && !this.name.isEmpty()
                && Objects.nonNull(this.price) && this.price.compareTo(BigDecimal.ZERO) > 0
                && hasSecureIngredientAmmount();
    }

    private boolean hasSecureIngredientAmmount() {
        return this.ingredients.stream()
                .allMatch(ingredient -> ingredient.getQuantityInStock() >= 10);
    }

    public static class Builder {
        private final Food food;

        public Builder() {
            food = new Food();
        }

        public Builder withId(Integer Id) {
            this.food.setId(Id);
            return this;
        }

        public Builder withName(String name) {
            this.food.setName(name);
            return this;
        }

        public Builder withPrice(BigDecimal price) {
            this.food.setPrice(price);
            return this;
        }

        public Builder withIngredients(Set<Ingredient> ingredients) {
            this.food.setIngredients(ingredients);
            return this;
        }

        public Food build() {
            return food;
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hamburger{");
        sb.append("price=").append(this.getPrice());
        sb.append(",name=").append(this.getName());
        sb.append('}');
        return sb.toString();
    }
}
