package com.example.RegistrationAndLoginSystem.process.builder;

import com.example.RegistrationAndLoginSystem.entity.Booking;
import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.entity.User;

public interface BookingBuilder {
    public BookingBuilder setId(Long id);

    public BookingBuilder setUser(User user);

    public BookingBuilder setTable(TableR table);

    public BookingBuilder setDate(String date);

    public BookingBuilder setBalance(Double balance);

    public BookingBuilder setDescriber(String describer);

    public Booking build();
}

