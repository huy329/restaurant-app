package com.example.RegistrationAndLoginSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.process.template.DrinkTemplate;
import com.example.RegistrationAndLoginSystem.process.template.FoodAndDrinkTemplate;
import com.example.RegistrationAndLoginSystem.process.template.FoodTemplate;
import com.example.RegistrationAndLoginSystem.repository.FoodRepository;

@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;

    public void createFood(Food food) {
        foodRepository.save(food);
    }
    
    public void updateFood(Food food) {
        foodRepository.save(food);
    }
    
    public void deleteFood(Long id) {
        foodRepository.deleteById(id);
    }

    public List<Food> findAllFood() {
        List<Food> food = foodRepository.findAll();
        return food;
    }

    public Optional<Food> findById(Long foodId) {
        return foodRepository.findById(foodId);
    }

    public String prepareFoodAndDrink(Food food) {
        //NoteHuy: Template
        FoodAndDrinkTemplate foodTemplate = new FoodTemplate(food);

        if(food.getType() == 1) {
            foodTemplate = new DrinkTemplate(food);
        }

        return foodTemplate.prepareFoodAndDrink();
    }
}
