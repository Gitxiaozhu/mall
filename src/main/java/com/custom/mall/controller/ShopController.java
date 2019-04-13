package com.custom.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/shop/")
public class ShopController {

    @PostMapping("getList")
    public String shop(){
        return "测试";
    }
}
