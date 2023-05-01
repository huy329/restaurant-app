package com.example.RegistrationAndLoginSystem.process.command;

import com.example.RegistrationAndLoginSystem.entity.Bill;
import com.example.RegistrationAndLoginSystem.repository.BillRepository;

public class BillCommand implements Command {
    private Bill bill;
    private BillRepository billRepository;

    public BillCommand(Bill bill, BillRepository billRepository) {
        this.bill = bill;
        this.billRepository = billRepository;
    }

    @Override
    public void execute() {
        billRepository.save(bill);
    }
}