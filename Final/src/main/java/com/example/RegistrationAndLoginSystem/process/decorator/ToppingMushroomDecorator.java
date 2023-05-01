package com.example.RegistrationAndLoginSystem.process.decorator;

public class ToppingMushroomDecorator extends ToppingChooseDecorator{
    public ToppingMushroomDecorator(FoodDecorator foodDecorator) {
        super(foodDecorator);
    }
    
    @Override
    public String printFoodAndToppingDecorator() {
        return foodDecorator.printFoodAndToppingDecorator() + " with Mushroom. "; 
    }
}
