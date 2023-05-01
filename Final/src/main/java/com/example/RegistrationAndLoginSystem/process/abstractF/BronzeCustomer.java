package com.example.RegistrationAndLoginSystem.process.abstractF;

public class BronzeCustomer extends Customer {
   public BronzeCustomer(double discountRate) {
      this.discountRate = discountRate;
   }

   @Override
   public double getDiscountRate() {
      return discountRate;
   }
}
