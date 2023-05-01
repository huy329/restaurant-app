package com.example.RegistrationAndLoginSystem.process.abstractF;

public class GoldCustomer extends Customer {
   public GoldCustomer(double discountRate) {
      this.discountRate = discountRate;
   }

   @Override
   public double getDiscountRate() {
      return discountRate;
   }
}
