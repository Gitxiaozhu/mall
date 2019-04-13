package com.custom.mall.service.impl;

import com.custom.mall.dao.UserMapper;
import com.custom.mall.dao.UserRepository;
import com.custom.mall.pojo.User;
import com.custom.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity select() {
        User user = userMapper.selectByPrimaryKey(2);
        return ResponseEntity.status(200).body(user);
    }

    @Override
    public String getUsername(String id) {
        String username = userMapper.getUsername(id);
        return username;
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
//        return userMapper.selectByUsername(username);
    }


}
