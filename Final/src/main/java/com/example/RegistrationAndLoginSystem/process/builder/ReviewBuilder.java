package com.example.RegistrationAndLoginSystem.process.builder;

import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.entity.Review;
import com.example.RegistrationAndLoginSystem.entity.User;

public class ReviewBuilder {
    private Long id;
    private User user;
    private Food food;
    private String date;
    private int star;
    private String comment;

    public ReviewBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public ReviewBuilder setUser(User user) {
        this.user = user;
        return this;
    }

    public ReviewBuilder setFood(Food food) {
        this.food = food;
        return this;
    }

    public ReviewBuilder setDate(String date) {
        this.date = date;
        return this;
    }

    public ReviewBuilder setStar(int star) {
        this.star = star;
        return this;
    }

    public ReviewBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public Review build() {
        return new Review(id, user, food, date, star, comment);
    }
}
