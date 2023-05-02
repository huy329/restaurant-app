package com.example.RegistrationAndLoginSystem.process.factory;

public class SilverCustomer extends Customer {
   public SilverCustomer(double discountRate) {
      this.discountRate = discountRate;
   }

   @Override
   public double getDiscountRate() {
      return discountRate;
   }
}
