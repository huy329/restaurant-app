package com.example.RegistrationAndLoginSystem.process.builder;

import java.util.Date;

import com.example.RegistrationAndLoginSystem.entity.Booking;
import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.entity.User;

public class BookingDirector {
    public Booking build(User user, TableR table) {
        return new BookingBuilderImp()
                        .setTable(table)
                        .setUser(user)
                        .setDate(new Date().toString())
                        .setBalance(table.getPrice())
                        .build();
    }
}