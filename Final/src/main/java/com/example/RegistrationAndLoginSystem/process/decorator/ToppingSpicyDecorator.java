package com.example.RegistrationAndLoginSystem.process.decorator;

public class ToppingSpicyDecorator extends ToppingChooseDecorator{
    public ToppingSpicyDecorator(FoodDecorator foodDecorator) {
        super(foodDecorator);
    }
    
    @Override
    public String printFoodAndToppingDecorator() {
        return foodDecorator.printFoodAndToppingDecorator() + " with Spicy. "; 
    }
}
