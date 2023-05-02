package com.example.RegistrationAndLoginSystem.process.factory;

public class GoldCustomer extends Customer {
   public GoldCustomer(double discountRate) {
      this.discountRate = discountRate;
   }

   @Override
   public double getDiscountRate() {
      return discountRate;
   }
}
