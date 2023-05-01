package com.example.RegistrationAndLoginSystem.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.RegistrationAndLoginSystem.entity.Bill;
import com.example.RegistrationAndLoginSystem.entity.Booking;
import com.example.RegistrationAndLoginSystem.entity.Deposit;
import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.entity.Review;
import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.service.BillService;
import com.example.RegistrationAndLoginSystem.service.BookingService;
import com.example.RegistrationAndLoginSystem.service.DepositService;
import com.example.RegistrationAndLoginSystem.service.FoodService;
import com.example.RegistrationAndLoginSystem.service.ReviewService;
import com.example.RegistrationAndLoginSystem.service.TableService;
import com.example.RegistrationAndLoginSystem.service.UserService;

@Controller
public class AdminController {
    private UserService userService;
    private TableService tableService;
    private FoodService foodService;
    private BookingService bookingService;
    private BillService billService;
    private DepositService depositService;
    private ReviewService reviewService;

    public AdminController(
                    UserService userService, 
                    TableService tableService,
                    FoodService foodService, 
                    BookingService bookingService,
                    BillService billService,
                    DepositService depositService,
                    ReviewService reviewService) {
        this.userService = userService;
        this.tableService = tableService;
        this.foodService = foodService;
        this.bookingService = bookingService;
        this.billService = billService;
        this.depositService = depositService;
        this.reviewService = reviewService;
    }

    //NoteHuy: xem danh sách user
    @GetMapping("/user")
    public String userList(Model model){
        List<User> userList = userService.findAllUser();

        model.addAttribute("userList", userList);
        return "/user";
    }

    //NoteHuy: thêm trong user form
    @GetMapping("/userCreateForm")
    public String usercreateForm(Model model){
        model.addAttribute("user", new User());
        return "/userCreateForm";
    }

    //NoteHuy: thêm user
    @PostMapping("/userCreate")
    public String userCreate(@ModelAttribute("user") User user) {
        userService.createUser(user);
        return "redirect:/user";
    }

    //NoteHuy: sửa trong user form
    @PostMapping("/userUpdateForm")
    public String userUpdateForm(
                            @ModelAttribute("userId") Long userId, 
                            Model model) {
        User user = userService.findById(userId).orElse(null);

        model.addAttribute("user", user);
        return "/userUpdateForm";
    }

    //NoteHuy: xóa user
    @PostMapping("/userDelete")
    public String userDelete(@ModelAttribute("userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/user";
    }
    
    //NoteHuy: xem danh sách bàn
    @GetMapping("/table")
    public String tableList(Model model){
        List<TableR> tableList = tableService.findAllTable();

        model.addAttribute("tableList", tableList);
        return "/table";
    }

    //NoteHuy: thêm trong table form
    @GetMapping("/tableCreateForm")
    public String tableCreateForm(Model model){
        return "/tableCreateForm";
    }

    //NoteHuy: thêm table
    @PostMapping("/tableCreate")
    public String tableCreate(
                            @ModelAttribute("tableName") String tableName,
                            @ModelAttribute("userId") Long userId,
                            @ModelAttribute("tablePrice") Double tablePrice) {
        User user = userService.findById(userId).orElse(null);

        TableR table = new TableR(null, tableName, user, tablePrice);
        
        tableService.createTable(table);
        return "redirect:/table";
    }

    //NoteHuy: sửa trong table form
    @PostMapping("/tableUpdateForm")
    public String tableUpdateForm(
                            @ModelAttribute("tableId") Long tableId,
                            Model model) {
        TableR table = tableService.findById(tableId).orElse(null);

        model.addAttribute("table", table);
        return "/tableUpdateForm";
    }

    //NoteHuy: sửa table
    @PostMapping("/tableUpdate")
    public String tableUpdate(
                            @ModelAttribute("tableId") Long tableId,
                            @ModelAttribute("tableName") String tableName,
                            @ModelAttribute("userId") Long userId,
                            @ModelAttribute("tablePrice") Double tablePrice) {
        User user = userService.findById(userId).orElse(null);
        TableR table = new TableR(tableId, tableName, user, tablePrice);
        
        tableService.updateTable(table);
        return "redirect:/table";
    }

    //NoteHuy: xóa table
    @PostMapping("/tableDelete")
    public String tableDelete(@ModelAttribute("tableId") Long tableId) {
        tableService.deleteTable(tableId);
        return "redirect:/table";
    }

    //NoteHuy: xem danh sách món
    @GetMapping("/food")
    public String foodList(Model model){
        List<Food> foodList = foodService.findAllFood();

        model.addAttribute("foodList", foodList);
        return "/food";
    }

    //NoteHuy: thêm trong food form
    @GetMapping("/foodCreateForm")
    public String foodCreateForm(Model model){
        model.addAttribute("food", new Food());
        return "/foodCreateForm";
    }

    //NoteHuy: thêm food
    @PostMapping("/foodCreate")
    public String foodCreate(@ModelAttribute("food") Food food) {
        foodService.createFood(food);
        return "redirect:/food";
    }

    //NoteHuy: sửa trong food form
    @PostMapping("/foodUpdateForm")
    public String foodUpdateForm(
                            @ModelAttribute("foodId") Long foodId, 
                            Model model) {
        Food food = foodService.findById(foodId).orElse(null);

        model.addAttribute("food", food);
        return "/foodUpdateForm";
    }

    //NoteHuy: sửa food
    @PostMapping("/foodUpdate")
    public String foodUpdate(
                            @ModelAttribute("foodId") Long foodId,
                            @ModelAttribute("foodName") String foodName,
                            @ModelAttribute("foodNation") String foodNation,
                            @ModelAttribute("foodIngredient") String foodIngredient,
                            @ModelAttribute("foodImage") String foodImage,
                            @ModelAttribute("foodStage") String foodStage,
                            @ModelAttribute("foodType") Integer foodType) {
        Food food = new Food(foodId, foodName, foodNation, foodIngredient, foodImage, foodStage, foodType);
        foodService.updateFood(food);
        return "redirect:/food";
    }

    //NoteHuy: xóa food
    @PostMapping("/foodDelete")
    public String foodDelete(@ModelAttribute("foodId") Long foodId) {
        foodService.deleteFood(foodId);
        return "redirect:/food";
    }

    //NoteHuy: xem lịch sử đặt bàn
    @GetMapping("/booking")
    public String bookingList(Model model){
        List<Booking> bookingList = bookingService.findAllBooking();

        model.addAttribute("bookingList", bookingList);
        return "/booking";
    }

    //NoteHuy: xem lịch sử đặt món
    @GetMapping("/bill")
    public String billList(Model model){
        List<Bill> billList = billService.findAllBill();

        model.addAttribute("billList", billList);
        return "/bill";
    }

    //NoteHuy: xem lịch sử nạp tiền
    @GetMapping("/deposit")
    public String depositList(Model model){
        List<Deposit> depositList = depositService.findAllDeposit();

        model.addAttribute("depositList", depositList);
        return "/deposit";
    }

    //NoteHuy: xem lịch sử đánh giá
    @GetMapping("/review")
    public String reviewList(Model model){
        List<Review> reviewList = reviewService.findAllReview();

        model.addAttribute("reviewList", reviewList);
        return "/review";
    }

    @GetMapping("/my-page")
    public String myPage(Model model) {
        String myString = "Hello World!";
        model.addAttribute("myString", myString);
        return "my-page";
    }
}
