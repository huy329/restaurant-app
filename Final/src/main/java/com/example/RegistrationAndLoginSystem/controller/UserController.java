package com.example.RegistrationAndLoginSystem.controller;

import com.example.RegistrationAndLoginSystem.entity.Bill;
import com.example.RegistrationAndLoginSystem.entity.Booking;
import com.example.RegistrationAndLoginSystem.entity.Card;
import com.example.RegistrationAndLoginSystem.entity.Deposit;
import com.example.RegistrationAndLoginSystem.entity.Food;
import com.example.RegistrationAndLoginSystem.entity.Review;
import com.example.RegistrationAndLoginSystem.entity.TableR;
import com.example.RegistrationAndLoginSystem.entity.User;
import com.example.RegistrationAndLoginSystem.service.BillService;
import com.example.RegistrationAndLoginSystem.service.BookingService;
import com.example.RegistrationAndLoginSystem.service.CardService;
import com.example.RegistrationAndLoginSystem.service.DepositService;
import com.example.RegistrationAndLoginSystem.service.FoodService;
import com.example.RegistrationAndLoginSystem.service.ReviewService;
import com.example.RegistrationAndLoginSystem.service.TableService;
import com.example.RegistrationAndLoginSystem.service.UserService;

import jakarta.validation.Valid;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private UserService userService;
    private CardService cardService;
    private DepositService depositService;
    private TableService tableService;
    private BookingService bookingService;
    private FoodService foodService;
    private ReviewService reviewService;
    private BillService billService;

    public UserController(  UserService userService, 
                            CardService cardService, 
                            DepositService depositService, 
                            TableService tableService, 
                            BookingService bookingService, 
                            FoodService foodService, 
                            ReviewService reviewService,
                            BillService billService) {
        this.userService = userService;
        this.cardService = cardService;
        this.depositService = depositService;
        this.tableService = tableService;
        this.bookingService = bookingService;
        this.foodService = foodService;
        this.reviewService = reviewService;
        this.billService = billService;
    }

    @GetMapping("/")
    public String home2(){
        return "/index";
    }

    @GetMapping("/index")
    public String home(){
        return "/index";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "/register";
    }

    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("user") User user,
                               BindingResult result,
                               Model model){
        User existing = userService.findByEmail(user.getEmail());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "/register";
        }
        userService.createUser(user);
        return "/redirect:/register?success";
    }

    //NoteHuy: điền form nạp tiền vào tài khoản
    @GetMapping("/depositForm")
    public String depositForm() {
        return "/depositForm";
    }
    @PostMapping("/depositProcess")
    public String depositProcess(
                            @RequestParam("userEmail") String userEmail,
                            @RequestParam("depositPayment") String depositPayment,
                            @RequestParam("depositBalance") Double depositBalance,
                            @RequestParam("cardNumber") String cardNumber,
                            @RequestParam("cardPassword") String cardPassword,
                            RedirectAttributes redirectAttributes) {
        User user = userService.findByEmail(userEmail);
        Card card = cardService.findByNumberAndPassword(cardNumber, cardPassword);

        if(card==null) {
            redirectAttributes.addFlashAttribute("error", "Invaild");
            return "redirect:/depositForm";
        } else {
            redirectAttributes.addFlashAttribute("message", "Vaild");
            depositService.createDeposit(depositPayment, user, card, depositBalance);
        }

        return "redirect:/depositForm";
    }

    //NoteHuy: điền form booking
    @PostMapping("/bookingForm")
    public String bookingForm(@RequestParam("userEmail") String userEmail, Model model){
        User user = userService.findByEmail(userEmail);

        List<TableR> tableList = tableService.findAllTable();

        model.addAttribute("user", user);
        model.addAttribute("tableList", tableList);
        return "/bookingForm";
    }
    @PostMapping("/bookingProcess")
    public String bookingProcess(
                            @RequestParam("userEmail") String userEmail,
                            @RequestParam("tableId") Long tableId,
                            RedirectAttributes redirectAttributes,
                            Model model){
        User user = userService.findByEmail(userEmail);
        TableR table = tableService.findById(tableId).orElse(null);

        if(user.getBalance() < table.getPrice()) {
            redirectAttributes.addFlashAttribute("error", "balance not enough!");
            return "redirect:/bookingForm";
        }

        bookingService.createBooking(user, table);
        return "redirect:/foodMenu";
    }

    //NoteHuy: điền form review
    @PostMapping("/reviewForm")
    public String reviewForm(
                            @RequestParam("billId") String billId, 
                            @RequestParam("foodId") String foodId, 
                            Model model) {
        model.addAttribute("billId", billId);
        model.addAttribute("foodId", foodId);
        return "/reviewForm";
    }
    @PostMapping("/reviewProcess")
    public String reviewProcess(
                            @RequestParam("userEmail") String userEmail,
                            @RequestParam("foodId") Long foodId,
                            @RequestParam("billId") Long billId,
                            @RequestParam("reviewStar") Integer reviewStar,
                            @RequestParam("reviewComment") String reviewComment,
                            Model model){
        User user = userService.findByEmail(userEmail);
        Food food = foodService.findById(foodId).orElse(null);
        Bill bill = billService.findById(billId).orElse(null);
        Date date = new Date();

        reviewService.createReview(user, food, bill, date.toString(), reviewStar, reviewComment);

        return "redirect:/review";
    }

    //NoteHuy: xem review của món ăn cụ thể
    @PostMapping("/reviewFood")
    public String reviewFood(
                            @RequestParam("foodId") Long foodId,
                            Model model) {
        Food food = foodService.findById(foodId).orElse(null);

        List<Review> reviewFoodList = reviewService.findAllReviewFood(food);

        model.addAttribute("reviewFoodList", reviewFoodList);
        return "/reviewFood";
    }

    //NoteHuy: order món ăn
    @PostMapping("/billProcess")
    public String billProcess(
                            @RequestParam("userEmail") String userEmail,
                            @RequestParam("foodId") Long foodId,
                            @RequestParam("toppingList") List<String> toppingList,
                            RedirectAttributes redirectAttributes){
        User user = userService.findByEmail(userEmail);
        Food food = foodService.findById(foodId).orElse(null);

        if(user.getStatus() == 0) {
            redirectAttributes.addFlashAttribute("error", "you should booking table!");
            return "redirect:/bookingForm";
        }
        billService.createBill(user, food, toppingList);

        redirectAttributes.addFlashAttribute("message", "order success!");
        return "redirect:/foodMenu";
    }

    //NoteHuy: order trạng thái món ăn
    @PostMapping("/billStatusProcess")
    public String billStatusProcess(
                            @RequestParam("billId") Long billId,
                            @RequestParam("billStatus") Integer billStatus){
        Bill bill = billService.findById(billId).orElse(null);

        billService.setBillStatus(bill, billStatus);
        return "redirect:/bill";
    }

    //NoteHuy: customer update form
    @PostMapping("/customerUpdateForm")
    public String customerUpdateForm(
                            @RequestParam("userEmail") String userEmail, 
                            Model model) {
        User user = userService.findByEmail(userEmail);

        model.addAttribute("user", user);
        return "/customerUpdateForm";
    }
    @PostMapping("/customerUpdate")
    public String userUpdate(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/foodMenu";
    }

    //NoteHuy: hiển thị menu và chi tiết món ăn
    @GetMapping("/foodMenu")
    public String foodMenu(Model model) {
        List<Food> foodList = foodService.findAllFood();

        model.addAttribute("foodList", foodList);
        return "/foodMenu";
    }
    @PostMapping("/foodMenuDetail")
    public String foodMenuDetail(
                            @ModelAttribute("foodId") Long foodId,
                            Model model) {
        Food food = foodService.findById(foodId).orElse(null);
        List<Review> reviewList = reviewService.findAllReviewFood(food);

        model.addAttribute("food", food);
        model.addAttribute("reviewList", reviewList);
        return "/foodMenuDetail";
    }

    @GetMapping("/home")
    public String home3(Model model){
        List<Food> foodList = foodService.findAllFood();

        model.addAttribute("foodList", foodList);
        return "/home";
    }

    @GetMapping("/history")
    public String history(){
        return "/history";
    }

    @PostMapping("/history")
    public String historyPost(@RequestParam("number") Integer number, Model model){
        List<Deposit> depositList = depositService.findAllDeposit();
        List<Booking> bookingList = bookingService.findAllBooking();
        List<Bill> billList = billService.findAllBill();

        model.addAttribute("number", number);
        model.addAttribute("depositList", depositList);
        model.addAttribute("bookingList", bookingList);
        model.addAttribute("billList", billList);
        return "/history";
    }

    @PostMapping("/userStatusStop")
    public String userStatusStop(@RequestParam("userEmail") String userEmail){
        User user = userService.findByEmail(userEmail);

        userService.userStatusStop(user);
        return "redirect:/home";
    }
}