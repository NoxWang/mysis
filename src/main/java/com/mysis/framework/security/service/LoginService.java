package com.mysis.framework.security.service;

import com.mysis.common.contants.Constants;
import com.mysis.framework.exception.UserException;
import com.mysis.framework.redis.RedisCache;
import com.mysis.framework.security.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginService {

    @Autowired
    private RedisCache redisCache;

    @Resource
    private AuthenticationManager authenticationManager;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @param captcha  验证码
     * @param uuid     uuid
     * @return
     */
    public String login(String username, String password, String captcha, String uuid) {
        String verifyKey = Constants.KEY_CAPTCHA + uuid;
        String correctCaptcha = redisCache.getCacheObject(verifyKey);
        redisCache.deleteObject(verifyKey);

        if (correctCaptcha == null) {
            throw new UserException("user.captcha.expire");
        }

        if (!correctCaptcha.equalsIgnoreCase(captcha)) {
            throw new UserException("user.captcha.error");
        }

        Authentication authentication;
        try {
            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new UserException("user.password.notMatch");
            } else {
                throw new RuntimeException(e.getMessage());
            }
        }
        LoginVO loginVO = (LoginVO) authentication.getPrincipal();
        return "okk";
    }
}
