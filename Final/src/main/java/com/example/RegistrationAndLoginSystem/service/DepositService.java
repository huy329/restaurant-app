package com.example.RegistrationAndLoginSystem.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.Card;
import com.example.RegistrationAndLoginSystem.entity.Deposit;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.process.strategy.BankTransferDepositStrategy;
import com.example.RegistrationAndLoginSystem.process.strategy.CashDepositStrategy;
import com.example.RegistrationAndLoginSystem.process.strategy.CreditCardDepositStrategy;
import com.example.RegistrationAndLoginSystem.process.strategy.DepositContext;
import com.example.RegistrationAndLoginSystem.process.strategy.DepositStrategy;
import com.example.RegistrationAndLoginSystem.repository.CardRepository;
import com.example.RegistrationAndLoginSystem.repository.DepositRepository;
import com.example.RegistrationAndLoginSystem.repository.UserRepository;

@Service
public class DepositService {
    @Autowired
    DepositRepository depositRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    UserRepository userRepository;

    public void createDeposit(String depositPayment, User user, Card card, double balance) {
        //NoteHuy: Strategy
        DepositStrategy depositStrategy = new CashDepositStrategy();

        if(depositPayment.equals("depositBankPayment")) {
            depositStrategy = new BankTransferDepositStrategy();
        } else if(depositPayment.equals("depositCreditCardPayment")) {
            depositStrategy = new CreditCardDepositStrategy();
        } 

        DepositContext depositContext = new DepositContext(depositStrategy);
        double calculateDeposit = depositContext.excuteCalculateDepositStategy(balance);
        String typePaymentDeposit = depositContext.excuteTypePaymentDepositStrategy();

        //NoteHuy: cập nhật cho user và card và deposit
        user.setBalance(user.getBalance() + calculateDeposit);
        card.setBalance(card.getBalance() - balance);
        Deposit deposit = new Deposit(null, user, new Date().toString(), calculateDeposit, typePaymentDeposit);

        //NoteHuy: save cả 3
        userRepository.save(user);
        cardRepository.save(card);
        depositRepository.save(deposit);
    }

    public List<Deposit> findAllDeposit() {
        return depositRepository.findAll();
    }
}
