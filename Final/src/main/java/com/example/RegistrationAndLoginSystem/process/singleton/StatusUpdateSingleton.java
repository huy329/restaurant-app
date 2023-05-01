package com.example.RegistrationAndLoginSystem.process.singleton;

import com.example.RegistrationAndLoginSystem.entity.Bill;
import com.example.RegistrationAndLoginSystem.repository.BillRepository;

public class StatusUpdateSingleton {
    BillRepository billRepository;
    Bill bill;

    private static StatusUpdateSingleton instance;

    private StatusUpdateSingleton() {
    }

    public static StatusUpdateSingleton getInstance() {
        if (instance == null) {
            instance = new StatusUpdateSingleton();
        }
        return instance;
    }

    public void setStatus(Bill bill, BillRepository billRepository, Integer billStatus) {
        bill.setStatus(billStatus);
        billRepository.save(bill);
    }
}
