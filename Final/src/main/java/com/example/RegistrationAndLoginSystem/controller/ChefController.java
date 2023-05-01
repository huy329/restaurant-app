package com.example.RegistrationAndLoginSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.service.FoodService;

@Controller
public class ChefController {
    private FoodService foodService;

    public ChefController(FoodService foodService) {
        this.foodService = foodService;
    }

    //NoteHuy: xem các bước làm món ăn
    @PostMapping("/foodStageProcess")
    public String foodStageProcess(
                            @RequestParam("foodId") Long foodId, 
                            Model model) {
        Food food = foodService.findById(foodId).orElse(null);
        String foodStage = foodService.prepareFoodAndDrink(food);
        
        model.addAttribute("foodStage", foodStage);
        return "/foodStage";
    }
}
