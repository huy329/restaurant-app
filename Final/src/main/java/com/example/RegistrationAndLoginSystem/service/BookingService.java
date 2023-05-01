package com.example.RegistrationAndLoginSystem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.Booking;
import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.process.builder.BookingBuilder;
import com.example.RegistrationAndLoginSystem.process.abstractF.Customer;
import com.example.RegistrationAndLoginSystem.process.abstractF.CustomerDiscountFactory;
import com.example.RegistrationAndLoginSystem.process.abstractF.CustomerFactory;
import com.example.RegistrationAndLoginSystem.repository.BookingRepository;
import com.example.RegistrationAndLoginSystem.repository.TableRepository;
import com.example.RegistrationAndLoginSystem.repository.UserRepository;

@Service
public class BookingService {
    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TableRepository tableRepository;

    @Autowired
    UserRepository userRepository;

    public void createBooking(User user, TableR table) {
        //NoteHuy: Builder
        Booking booking = new BookingBuilder()
                                .setTable(table)
                                .setUser(user)
                                .setDate(new Date().toString())
                                .setBalance(table.getPrice())
                                .build();

        //NoteHuy: Abstract Factory
        CustomerFactory customerFactory = new CustomerDiscountFactory();
        List<Booking> bookingList = bookingRepository.findByUser(user);

        Customer customer = customerFactory.createCustomer(bookingList);
        double discountRate = customer.getDiscountRate();

        //NoteHuy: đổi trạng thái bàn và user
        user.setBalance(user.getBalance()-(table.getPrice()*discountRate));
        user.setStatus(1);
        user.setPromo(customer.getDiscountRate());

        table.setUser(user);
        
        userRepository.save(user);
        tableRepository.save(table);
        bookingRepository.save(booking);
    }

    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }
}