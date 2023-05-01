package com.example.RegistrationAndLoginSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.RegistrationAndLoginSystem.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card,Long> {
    public Card findByNumberAndPassword(String number, String password);
}