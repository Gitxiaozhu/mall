package com.custom.mall.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExcetionController {

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity handle401(AuthorizationException a){
        return ResponseEntity.status(401).body("错误"+a.getMessage());
    }
}
