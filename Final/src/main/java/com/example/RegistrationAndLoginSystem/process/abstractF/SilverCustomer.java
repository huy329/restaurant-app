package com.example.RegistrationAndLoginSystem.process.abstractF;

public class SilverCustomer extends Customer {
   public SilverCustomer(double discountRate) {
      this.discountRate = discountRate;
   }

   @Override
   public double getDiscountRate() {
      return discountRate;
   }
}
