package com.example.RegistrationAndLoginSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.Booking;
import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.process.builder.BookingDirector;
import com.example.RegistrationAndLoginSystem.process.factory.Customer;
import com.example.RegistrationAndLoginSystem.process.factory.CustomerDiscountFactory;
import com.example.RegistrationAndLoginSystem.process.factory.CustomerFactory;
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
        Booking booking = new BookingDirector().build(user, table);

        //NoteHuy: Abstract Factory
        CustomerFactory customerFactory = new CustomerDiscountFactory();
        List<Booking> bookingList = bookingRepository.findByUser(user);

        Customer customer = customerFactory.createCustomer(bookingList);
        double discountRate = customer.getDiscountRate();

        //NoteHuy: đổi trạng thái bàn và user
        user.setBalance(user.getBalance()-(table.getPrice()*discountRate));
        user.setStatus(1);
        user.setPromo(customer.getDiscountRate());
        
        userRepository.save(user);
        tableRepository.save(table);
        bookingRepository.save(booking);
    }

    public List<Booking> findAllBooking() {
        return bookingRepository.findAll();
    }
}