package com.example.RegistrationAndLoginSystem.process.strategy;

public class CreditCardDepositStrategy implements DepositStrategy {
    @Override
    public String typePaymentDepositStrategy() {
        return "Payment by Credit Card (+2%)";
    }

    @Override
    public double calculateDepositStategy(double amount) {
        return amount + (amount * 0.02);
    } 
}