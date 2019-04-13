package com.custom.mall.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

@ConfigurationProperties(prefix = "jwt")
@Component
public class JWTUtil {
    private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);

    private static String secret;

    private static long erpire = 60 * 60 * 24 * 3;

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        JWTUtil.secret = secret;
    }


    /**
     * @param name 用户的姓名 看前端的需求
     * @return 返回加密后的token
     * @Description: 生成签名
     * @Author: xiaozhu
     * @CreateDate: 2019/4/8 22:30
     */
    public static String createJWT(String name) {
        Date date = new Date(System.currentTimeMillis() + erpire);
        Map<String, Object> map = Maps.newHashMap();
        map.put("alg", "HS256");
        map.put("typ", "JWT");
        return JWT.create()
                .withHeader(map) //jwt头部
                .withSubject(name) //主题
                .withIssuer("xiaozhu") //签名是有谁生成
                .withExpiresAt(date)//签名过期的时间
                .withClaim("username", name)//自定义
                .sign(Algorithm.HMAC256(secret));// 签名算法以及密匙
    }

    /**
     * @param token 前端传过来的token
     * @return 是否正确
     * @Description: 校验token是否正确
     * @Author: xiaozhu
     * @CreateDate: 2019/4/8 22:36
     */
    public static boolean verify(String token) {
        try {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                .withIssuer("xiaozhu").build();
        verifier.verify(token);
        return true;
        } catch (JWTVerificationException exception) {
            logger.error("token过期了");
            return false;
        }
    }

    /**
     * @param token 前端传过来的token
     * @return 返回从token获取的字段
     * @Description: 获得token中的信息无需secret解密也能获得
     * @Author: xiaozhu
     * @CreateDate: 2019/4/8 22:44
     */
    public static String getId(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim("username").asString();
    }

}
