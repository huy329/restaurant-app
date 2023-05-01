package com.example.RegistrationAndLoginSystem.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.Bill;
import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.process.command.BillCommand;
import com.example.RegistrationAndLoginSystem.process.command.Command;
import com.example.RegistrationAndLoginSystem.process.command.Invoker;
import com.example.RegistrationAndLoginSystem.process.decorator.FoodChooseDecorator;
import com.example.RegistrationAndLoginSystem.process.decorator.FoodDecorator;
import com.example.RegistrationAndLoginSystem.process.decorator.ToppingCheeseDecorator;
import com.example.RegistrationAndLoginSystem.process.decorator.ToppingMushroomDecorator;
import com.example.RegistrationAndLoginSystem.process.decorator.ToppingSpicyDecorator;
import com.example.RegistrationAndLoginSystem.process.singleton.StatusUpdateSingleton;
import com.example.RegistrationAndLoginSystem.repository.BillRepository;

@Service
public class BillService {
    @Autowired
    BillRepository billRepository;

    public void createBill(User user, Food food, List<String> toppingList) {
        //NoteHuy: Decorator
        String describer = new String();
        FoodDecorator foodChooseDecorator = new FoodChooseDecorator(food);
        for(String topping : toppingList) {
            if(topping.equals("cheese")) {
                FoodDecorator toppingCheeseDecorator = new ToppingCheeseDecorator(foodChooseDecorator);
                describer += toppingCheeseDecorator.printFoodAndToppingDecorator();
            } else if(topping.equals("spicy")) {
                FoodDecorator toppingSpicyDecorator = new ToppingSpicyDecorator(foodChooseDecorator);
                describer += toppingSpicyDecorator.printFoodAndToppingDecorator();
            } else if(topping.equals("mushroom")) {
                FoodDecorator toppingMushroomDecorator = new ToppingMushroomDecorator(foodChooseDecorator);
                describer += toppingMushroomDecorator.printFoodAndToppingDecorator();
            }
        }
        
        //NoteHuy: Command
        Bill bill = new Bill(null, user, food, new Date().toString(), describer, 0);
        Command command = new BillCommand(bill, billRepository);
        Invoker invoker = new Invoker();
        invoker.setCommand(command);
        invoker.executeCommand();
    }

    public List<Bill> findAllBill() {
        return billRepository.findAll();
    }

    public Optional<Bill> findById(Long billId) {
        return billRepository.findById(billId);
    }

    public void setBillStatus(Bill bill, Integer billStatus) {
        //NoteHuy: Singleton
        StatusUpdateSingleton statusUpdateSingleton = StatusUpdateSingleton.getInstance();

        statusUpdateSingleton.setStatus(bill, billRepository, billStatus);
    }
}
