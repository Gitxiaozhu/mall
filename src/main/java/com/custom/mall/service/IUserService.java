package com.custom.mall.service;

import com.custom.mall.pojo.User;
import org.springframework.http.ResponseEntity;

public interface IUserService {
    ResponseEntity select();

    String getUsername(String id);

    User getUser(String username);
}
