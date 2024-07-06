package com.sim.emoji.service;

import com.sim.emoji.mapper.UserMapper;
import com.sim.emoji.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isPasswordValid(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    public User findByUserId(String userId) {
        return userMapper.findByUserId(userId);
    }

    public void createUser(User user) {
        // 비밀번호 암호화
        user.setUserPw(passwordEncoder.encode(user.getUserPw()));
        userMapper.insert(user);
    }

    public boolean isUserIdDuplicate(String userId) {
        return userMapper.countByUserId(userId) > 0;
    }

    public boolean isUserNicknameDuplicate(String userNickname) {
        return userMapper.countByUserNickname(userNickname) > 0;
    }
}
