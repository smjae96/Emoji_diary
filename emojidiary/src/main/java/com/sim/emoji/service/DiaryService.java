package com.sim.emoji.service;

import com.sim.emoji.mapper.DiaryMapper;
import com.sim.emoji.model.Diary;
import com.sim.emoji.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class DiaryService {
    @Autowired
    private final DiaryMapper diaryMapper;

    public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    public List<Diary> getDiariesByDiaryWriter(Long diaryWriter) {
        return diaryMapper.findByDiaryWriter(diaryWriter);
    }

    public void saveDiary(Diary diary) {
        diaryMapper.saveDiary(diary);
    }
}
