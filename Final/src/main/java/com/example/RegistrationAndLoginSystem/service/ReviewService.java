package com.example.RegistrationAndLoginSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.Bill;
import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.entity.Review;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.process.builder.ReviewDirector;
import com.example.RegistrationAndLoginSystem.repository.BillRepository;
import com.example.RegistrationAndLoginSystem.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    BillRepository billRepository;

    public void createReview(User user, Food food, Bill bill, Integer reviewStar, String reviewComment) {
        //NoteHuy: Builder
        Review review = new ReviewDirector().build(user, food, reviewStar, reviewComment);

        //NoteHuy: set status bill sang 6
        bill.setStatus(6);

        reviewRepository.save(review);
        billRepository.save(bill);
    }

    public List<Review> findAllReviewFood(Food food) {
        return reviewRepository.findByFood(food);
    }

    public List<Review> findAllReview() {
        return reviewRepository.findAll();
    }
}