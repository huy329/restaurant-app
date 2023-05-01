package com.example.RegistrationAndLoginSystem.process.decorator;

public class ToppingCheeseDecorator extends ToppingChooseDecorator{
    public ToppingCheeseDecorator(FoodDecorator foodDecorator) {
        super(foodDecorator);
    }

    @Override
    public String printFoodAndToppingDecorator() {
        return foodDecorator.printFoodAndToppingDecorator() + " with Cheese. "; 
    }
}
