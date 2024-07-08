package com.sim.emoji.controller;

import com.sim.emoji.model.Diary;
import com.sim.emoji.model.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/diary")
public class DiaryController {
    @GetMapping("/write")
    public String showDiaryWriteForm(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);
            return "diary_write"; // 일기 작성 페이지 템플릿
        } else {
            return "redirect:/login";
        }
    }
//    @PostMapping("/diary/write")
//    public String writeDiary(@RequestParam("diaryTitle") String diaryTitle,
//                             @RequestParam("diaryContent") String diaryContent,
//                             // 추가 필드들
//                             HttpSession session) {
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return "redirect:/login";
//        }
//        Diary diary = new Diary();
//        diary.setDiaryWriter(user.getId());
//        diary.setDiaryTitle(diaryTitle);
//        diary.setDiaryContent(diaryContent);
//        // 추가 필드 설정
//        diaryService.saveDiary(diary);
//        return "redirect:/diary";
//    }
}
