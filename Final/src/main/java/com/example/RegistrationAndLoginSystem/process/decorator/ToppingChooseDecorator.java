package com.example.RegistrationAndLoginSystem.process.decorator;

public abstract class ToppingChooseDecorator implements FoodDecorator{
    FoodDecorator foodDecorator;

    public ToppingChooseDecorator(FoodDecorator foodDecorator) {
        this.foodDecorator = foodDecorator;
    }

    @Override
    public String printFoodAndToppingDecorator() {
        return foodDecorator.printFoodAndToppingDecorator(); 
    }
}
