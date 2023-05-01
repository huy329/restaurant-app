package com.example.RegistrationAndLoginSystem.process.template;

public abstract class FoodAndDrinkTemplate {
    public final String prepareFoodAndDrink() {
        String a = prepareIngredients();
        String b = cook();
        String c = serve();

        return "Get " + a + ". Then, " + b + ". Finally, " + c;
    }

    public abstract String prepareIngredients();
    public abstract String cook();
    public abstract String serve();
}
 
