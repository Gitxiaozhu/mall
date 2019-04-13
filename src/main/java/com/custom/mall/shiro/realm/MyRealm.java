package com.custom.mall.shiro.realm;

import com.custom.mall.pojo.User;
import com.custom.mall.service.IUserService;
import com.custom.mall.shiro.token.JWTToken;
import com.custom.mall.util.JWTUtil;
import com.google.common.collect.Sets;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class MyRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);
    @Autowired
    private IUserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * @Description: 权限认证
     * @Author: xiaozhu
     * @CreateDate: 2019/4/10 19:18
     * @UpdateUser: xiaozhu
     * @URL: url
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("权限认证   " + principalCollection.toString());
        String username = JWTUtil.getId(principalCollection.toString());
        User user = userService.getUser(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        Set<String> set = Sets.newHashSet();
        logger.info("权限认证  "+user.getRole());
        set.add(user.getRole());
        simpleAuthorizationInfo.addRoles(set);
        return simpleAuthorizationInfo;
    }

    /**
     * @Description: 登录认证
     * @Author: xiaozhu
     * @CreateDate: 2019/4/10 19:18
     * @UpdateUser: xiaozhu
     * @URL: url
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        logger.info("shiro的token :" + token);
        String username = JWTUtil.getId(token);
        logger.info("shiro的username  :" + username);
        return new SimpleAuthenticationInfo(token, token, "'realm");
    }
}
