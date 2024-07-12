package com.sim.emoji.controller;

import com.sim.emoji.model.Diary;
import com.sim.emoji.model.User;
import com.sim.emoji.service.DiaryService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @GetMapping("/{id}")
    @ResponseBody
    public Diary getDiaryById(@PathVariable Long id) {
        System.out.println(diaryService.getDiaryById(id));
        // 일기 데이터를 데이터베이스에서 가져옵니다.
        return diaryService.getDiaryById(id);
    }

    @GetMapping("/list")
    public String showDiaries(Model model, HttpSession session,
                              @RequestParam(required = false, defaultValue = "DESC") String order,
                              @RequestParam(required = false) String keyword,
                              @RequestParam(required = false, defaultValue = "0") int page) {
        User user = (User) session.getAttribute("user");
        if (user != null) {
            int limit = 5;
            int offset = limit * page;
            List<Diary> diaries = diaryService.getDiariesByDiaryWriterAndKeyword(user.getId(), keyword, order, limit, offset);
            int totalDiaries = diaryService.countDiariesByDiaryWriter(user.getId(), keyword);
            int totalPages = (int) Math.ceil((double) totalDiaries / limit);
            model.addAttribute("user", user);
            model.addAttribute("order", order);
            model.addAttribute("keyword", keyword);
            model.addAttribute("diaries", diaries);
            model.addAttribute("totalPages", totalPages);
            model.addAttribute("currentPage", page);
            return "diary";
        } else {
            return "redirect:/login";
        }
    }
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
    @GetMapping("/edit/{id}")
    public String editDiary(@PathVariable Long id, Model model) {
        Diary diary = diaryService.getDiary(id);
        model.addAttribute("diary", diary);
        return "diary/edit";  // 일기 수정 페이지로 이동
    }

    @PostMapping("/delete/{id}")
    public String deleteDiary(@PathVariable Long id) {
        diaryService.deleteDiary(id);
        return "redirect:/diary";
    }

    @PostMapping("/save")
    public String saveDiary(@RequestParam("diaryTitle") String diaryTitle,
                            @RequestParam("diaryContent") String diaryContent,
                            @RequestParam("emojiMood") String emojiMood,
                            @RequestParam("emojiWeather") String emojiWeather,
                            @RequestParam("emojiPeople") String emojiPeople,
                            @RequestParam("diaryImage") MultipartFile diaryImage,
                            HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        Diary diary = new Diary();
        diary.setDiaryWriter(user.getId());
        diary.setDiaryTitle(diaryTitle);
        diary.setDiaryContent(diaryContent);
        diary.setEmojiMood(emojiMood);
        diary.setEmojiWeather(emojiWeather);
        diary.setEmojiPeople(emojiPeople);
        diary.setDiaryDate(LocalDateTime.now());

        // 이미지 파일 저장 로직 추가
        if (!diaryImage.isEmpty()) {
            String fileName = UUID.randomUUID().toString() + "_" + diaryImage.getOriginalFilename();
            Path path = Paths.get("src/main/resources/static/images/saved/" + fileName); // 저장 경로 설정
            try {
                Files.createDirectories(path.getParent()); // 디렉토리가 없을 경우 생성
                Files.write(path, diaryImage.getBytes());
                diary.setDiaryImage("/images/saved/" + fileName); // 저장된 파일 이름 설정
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        diaryService.saveDiary(diary);

        return "redirect:/diary";
    }




}
