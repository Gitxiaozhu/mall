package com.custom.mall.filter;

import com.custom.mall.shiro.token.JWTToken;
import com.custom.mall.util.JWTUtil;
import com.custom.mall.util.StringUtil;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
public class JWTFilter extends BasicHttpAuthenticationFilter {
    private Logger logger = LoggerFactory.getLogger(JWTFilter.class);

    /**
     * @Description: 判断用户是否想要登入.
     * 检测header里面是否包含token字段
     * @Author: xiaozhu
     * @CreateDate: 2019/4/10 14:08
     * @UpdateUser: xiaozhu
     * @URL: url
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest req = (HttpServletRequest) request;
        String authorization = req.getHeader("token");
        logger.info("isLoginAttempt: " + authorization);
        return StringUtil.isNotBlank(authorization) && JWTUtil.verify(authorization);
    }

    /**
     * @Description: 登入，之后跳转到shiro
     * @Author: xiaozhu
     * @CreateDate: 2019/4/10 23:00
     * @UpdateUser: xiaozhu
     * @URL: url
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("token");
        logger.info("登入: " + token);
        JWTToken jwtToken = new JWTToken(token);
        getSubject(request, response).login(jwtToken);
        return true;
    }

    /**
     * @Description: 判断请求是否带有token,
     * 如果有token则执行登入,
     * 如果没有,则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true
     * @Author: xiaozhu
     * @CreateDate: 2019/4/10 14:16
     * @UpdateUser: xiaozhu
     * @URL: url
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        logger.info("JWTFilter +我进来了");
        if (isLoginAttempt(request, response)) {
            try {
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                logger.error("JWTFilter token异常  "+e);
            }
        }
        logger.info("未登录");
        return true;
    }
}
