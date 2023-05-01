package com.example.RegistrationAndLoginSystem.process.strategy;

public class CashDepositStrategy implements DepositStrategy {
    @Override
    public String typePaymentDepositStrategy() {
        return "Payment by Cash";
    } 

    @Override
    public double calculateDepositStategy(double amount) {
        return amount;
    } 
}
