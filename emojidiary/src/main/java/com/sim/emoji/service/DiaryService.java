package com.sim.emoji.service;

import com.sim.emoji.mapper.DiaryMapper;
import com.sim.emoji.model.Diary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiaryService {
    @Autowired
    private final DiaryMapper diaryMapper;

    public DiaryService(DiaryMapper diaryMapper) {
        this.diaryMapper = diaryMapper;
    }

//    public List<Diary> getDiariesByDiaryWriterAndKeyword(Long diaryWriter, String keyword, String order) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("diaryWriter", diaryWriter);
//        params.put("keyword", keyword == null ? "" : keyword);
//        params.put("order", order);
//        return diaryMapper.findByDiaryWriterAndKeyword(params);
//    }
//
//    public List<Diary> getDiariesByDiaryWriterWithPagination(Long diaryWriter, String order, int limit, int offset) {
//        Map<String, Object> params = new HashMap<>();
//        params.put("diaryWriter", diaryWriter);
//        params.put("order", order);
//        params.put("limit", limit);
//        params.put("offset", offset);
//        return diaryMapper.findByDiaryWriterWithPagination(params);
//    }
public List<Diary> getDiariesByDiaryWriterAndKeyword(Long diaryWriter, String keyword, String order, int limit, int offset) {
    Map<String, Object> params = new HashMap<>();
    params.put("diaryWriter", diaryWriter);
    params.put("keyword", keyword == null ? "" : keyword);
    params.put("order", order);
    params.put("limit", limit);
    params.put("offset", offset);
    return diaryMapper.findByDiaryWriterAndKeyword(params);
}

    public int countDiariesByDiaryWriter(Long diaryWriter, String keyword) {
        Map<String, Object> params = new HashMap<>();
        params.put("diaryWriter", diaryWriter);
        params.put("keyword", keyword == null ? "" : keyword);
        return diaryMapper.countDiariesByDiaryWriter(params);
    }

    public List<Diary> getDiariesByDiaryWriter(Long diaryWriter) {
        return diaryMapper.findByDiaryWriter(diaryWriter);
    }

    public void saveDiary(Diary diary) {
        diaryMapper.saveDiary(diary);
    }
}
