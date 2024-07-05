package com.sim.emoji.controller;

import com.sim.emoji.model.Users;
import com.sim.emoji.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "index"; // index.html 템플릿 이름
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String userPw, Model model) {
        Users user = userService.findByUserId(userId);
        if (user != null && user.getUserPw().equals(userPw)) {
            model.addAttribute("user", user);
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "Invalid credentials. Please try again.");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new Users());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute Users user, Model model) {
        userService.createUser(user);
        model.addAttribute("user", user);
        return "redirect:/profile";
    }

    @GetMapping("/profile")
    public String showProfile() {
        return "profile";
    }
}
