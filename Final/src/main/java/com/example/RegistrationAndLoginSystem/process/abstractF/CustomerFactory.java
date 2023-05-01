package com.example.RegistrationAndLoginSystem.process.abstractF;

import java.util.List;

import com.example.RegistrationAndLoginSystem.entity.Booking;

public abstract class CustomerFactory {
    public abstract Customer createCustomer(List<Booking> bookingList);
}