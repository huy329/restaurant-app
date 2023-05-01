package com.example.RegistrationAndLoginSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.Card;
import com.example.RegistrationAndLoginSystem.repository.CardRepository;

@Service
public class CardService {
    @Autowired
    CardRepository cardRepository;

    public Card findByNumberAndPassword(String number, String password) {
        return cardRepository.findByNumberAndPassword(number, password);
    }
}
