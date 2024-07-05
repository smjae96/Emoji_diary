package com.sim.emoji.service;

import com.sim.emoji.mapper.UserMapper;
import com.sim.emoji.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public Users findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    public void createUser(Users user) {
        userMapper.insert(user);
    }
}
