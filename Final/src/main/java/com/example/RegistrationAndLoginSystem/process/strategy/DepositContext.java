package com.example.RegistrationAndLoginSystem.process.strategy;

public class DepositContext {
    private DepositStrategy depositStrategy;

    public DepositContext(DepositStrategy depositStrategy) {
        this.depositStrategy = depositStrategy;
    }

    public double excuteCalculateDepositStategy(double amount) {
        return depositStrategy.calculateDepositStategy(amount);
    }

    public String excuteTypePaymentDepositStrategy() {
        return depositStrategy.typePaymentDepositStrategy();
    }
}
