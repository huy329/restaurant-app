package com.example.RegistrationAndLoginSystem.process.abstractF;

import java.util.List;

import com.example.RegistrationAndLoginSystem.entity.Booking;

public class CustomerDiscountFactory extends CustomerFactory {
    public Customer createCustomer(List<Booking> bookingList) {
        double bookingAllOfUser = 0;

        //NoteHuy: + user
        for(Booking booking : bookingList) {
            bookingAllOfUser += booking.getBalance();
        }

        //NoteHuy: tính tổng hóa đơn và tiến hành giảm giá
        if (bookingAllOfUser >= 3000) {
            return new GoldCustomer(0.2);
        } else if (bookingAllOfUser >= 2000) {
            return new SilverCustomer(0.1);
        } 
        return new BronzeCustomer(0);
    }
}