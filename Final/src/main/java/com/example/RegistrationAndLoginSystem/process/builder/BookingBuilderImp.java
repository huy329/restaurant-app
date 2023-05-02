package com.example.RegistrationAndLoginSystem.process.builder;

import com.example.RegistrationAndLoginSystem.entity.Booking;
import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.entity.User;

public class BookingBuilderImp implements BookingBuilder {
    private Long id;
    private User user;
    private TableR table;
    private String date;
    private double balance;
    private String describer;
    
    public BookingBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public BookingBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public BookingBuilder setTable(TableR table) {
        this.table = table;
        return this;
    }

    public BookingBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public BookingBuilder setBalance(Double balance) {
        this.balance = balance;
        return this;
    }

    public BookingBuilder setDescriber(String describer) {
        this.describer = describer;
        return this;
    }

    public Booking build() {
        return new Booking(id, user, table, date, balance, describer);
    }
}