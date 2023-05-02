package com.example.RegistrationAndLoginSystem.process.builder;

import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.entity.Review;
import com.example.RegistrationAndLoginSystem.entity.User;

public interface ReviewBuilder {
    public ReviewBuilder setId(Long id);

    public ReviewBuilder setUser(User user);

    public ReviewBuilder setFood(Food food);

    public ReviewBuilder setDate(String date);

    public ReviewBuilder setStar(int star);

    public ReviewBuilder setComment(String comment);

    public Review build();
}
