package com.sim.emoji.mapper;

import com.sim.emoji.model.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DiaryMapper {
    List<Diary> findByDiaryWriter(@Param("diaryWriter") Long diaryWriter);
    List<Diary> findByDiaryWriterAndKeyword(Map<String, Object> params);
    List<Diary> findByDiaryWriterWithPagination(Map<String, Object> params);
    void saveDiary(Diary diary);
    int countDiariesByDiaryWriter(Map<String, Object> params);
}

