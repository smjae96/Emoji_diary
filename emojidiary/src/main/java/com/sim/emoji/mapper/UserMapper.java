package com.sim.emoji.mapper;

import com.sim.emoji.model.Users;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface UserMapper {
    List<Users> findAll();
    Users findById(Long id);
    void insert(Users users);
    void update(Users user);
    void delete(Long id);
    Users findByUserId(String userId);
}
