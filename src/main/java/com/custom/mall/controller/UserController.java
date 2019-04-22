package com.custom.mall.controller;


import com.custom.mall.pojo.User;
import com.custom.mall.service.IUserService;
import com.custom.mall.util.JWTUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user/")
@Api(description = "用户")
public class UserController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @ApiOperation(value = "登陆", notes = "用户不存在就注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", dataType = "String", required = true),
            @ApiImplicitParam(name = "password", value = "密码", dataType = "String", required = true)
    })
    @GetMapping(value = "login")
    public ResponseEntity login(String name, String password) {

        User user = iUserService.getUser(name);
        System.out.println(user.getUsername());
        return ResponseEntity.status(200).body(JWTUtil.createJWT(name));
    }

    @GetMapping("getId")
    public String getId() {
        return "测试";
    }

}
