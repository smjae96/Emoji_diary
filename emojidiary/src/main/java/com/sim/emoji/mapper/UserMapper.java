package com.sim.emoji.mapper;

import com.sim.emoji.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user);
    void update(User user);
    void delete(Long id);
    User findByUserId(String userId);
    // id 및 닉네임 중복체크
    int countByUserId(@Param("userId") String userId);
    int countByUserNickname(@Param("userNickname") String userNickname);
}
