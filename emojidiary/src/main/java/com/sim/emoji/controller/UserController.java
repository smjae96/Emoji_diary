package com.sim.emoji.controller;

import com.sim.emoji.model.Diary;
import com.sim.emoji.model.User;
import com.sim.emoji.service.DiaryService;
import com.sim.emoji.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {
    private final UserService userService;
    private final DiaryService diaryService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, DiaryService diaryService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.diaryService = diaryService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userId") String userId, @RequestParam("userPw") String userPw, Model model, HttpSession session) {
        User user = userService.findByUserId(userId);
        if (user != null && passwordEncoder.matches(userPw, user.getUserPw())) {
            session.setAttribute("user", user);
            return "redirect:/profile";
        } else {
            model.addAttribute("error", "아이디 혹은 비밀번호가 일치하지 않습니다.");
            return "login";
        }
    }

    @GetMapping("/signup")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute User user, Model model) {
        if (userService.isUserIdDuplicate(user.getUserId())) {
            model.addAttribute("error", "이미 존재하는 아이디입니다!");
            return "signup";
        }
        if (userService.isUserNicknameDuplicate(user.getUserNickname())) {
            model.addAttribute("error", "이미 존재하는 닉네임입니다!");
            return "signup";
        }
        userService.createUser(user);
        return "redirect:/login"; // 회원가입 후 로그인 페이지로 리다이렉트
    }

    @GetMapping("/profile")
    public String showProfile(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        List<Diary> diaries = diaryService.getDiariesByDiaryWriter(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("diaries", diaries);
        return "profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화
        return "redirect:/login"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
