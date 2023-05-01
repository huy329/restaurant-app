package com.example.RegistrationAndLoginSystem.process.decorator;

import com.example.RegistrationAndLoginSystem.entity.Food;

public class FoodChooseDecorator implements FoodDecorator{
    Food food;
    
    public FoodChooseDecorator(Food food) {
        this.food = food;
    }

    @Override
    public String printFoodAndToppingDecorator() {
        return this.food.getName();
    }
}
