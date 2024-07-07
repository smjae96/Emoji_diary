package com.sim.emoji.service;

import com.sim.emoji.mapper.DiaryMapper;
import com.sim.emoji.model.Diary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiaryService {
    private final DiaryMapper diaryMapper;

    public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

    public List<Diary> getDiariesByDiaryWriter(Long diaryWriter) {
        return diaryMapper.findByDiaryWriter(diaryWriter);
    }
}
