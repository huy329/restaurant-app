package com.example.RegistrationAndLoginSystem.process.strategy;

public class BankTransferDepositStrategy implements DepositStrategy {
    @Override
    public String typePaymentDepositStrategy() {
        return "Payment by Bank Transfer (+1%)";
    } 

    @Override
    public double calculateDepositStategy(double amount) {
        return amount + (amount * 0.01);
    } 
}