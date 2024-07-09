package com.sim.emoji.mapper;

import com.sim.emoji.model.Diary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryMapper {
    List<Diary> findByDiaryWriter(@Param("diaryWriter") Long diaryWriter);
    void saveDiary(Diary diary);
}

