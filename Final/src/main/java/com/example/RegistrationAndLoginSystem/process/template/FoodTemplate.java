package com.example.RegistrationAndLoginSystem.process.template;

import com.example.RegistrationAndLoginSystem.entity.Food;

public class FoodTemplate extends FoodAndDrinkTemplate {
    Food food;

    public FoodTemplate(Food food) {
        this.food = food;
    }

    @Override
    public String prepareIngredients() {
        return food.getIngredient();
    }

    @Override
    public String cook() {
        return food.getStage();
    }

    @Override
    public String serve() {
        return "Put Food on Plate";
    }
}
