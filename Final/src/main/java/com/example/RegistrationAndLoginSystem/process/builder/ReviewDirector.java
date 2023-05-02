package com.example.RegistrationAndLoginSystem.process.builder;

import java.util.Date;

import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.entity.Review;
import com.example.RegistrationAndLoginSystem.entity.User;

public class ReviewDirector {
    public Review build(User user, Food food, Integer reviewStar, String reviewComment) {
        return new ReviewBuilderImpl()
                                    .setUser(user)
                                    .setFood(food)
                                    .setDate(new Date().toString())
                                    .setStar(reviewStar)
                                    .setComment(reviewComment)
                                    .build();
    }
}
