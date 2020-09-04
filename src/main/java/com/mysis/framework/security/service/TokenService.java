package com.mysis.framework.security.service;

import com.mysis.common.contants.Constants;
import com.mysis.common.utils.StringUtils;
import com.mysis.framework.redis.RedisCache;
import com.mysis.framework.security.vo.LoginVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Token服务
 */
@Component
public class TokenService {

    /**
     * Token自定义标识
     */
    @Value("${token.header}")
    private String header;

    /**
     * Token密钥
     */
    @Value("${token.secret}")
    private String secret;

    /**
     * Token有效期（单位：分钟）
     */
    @Value("${token.expireTime}")
    private int expireTime;

    @Autowired
    private RedisCache redisCache;

    /**
     * 创建 Token
     * @param loginVO 登陆成功后的用户信息
     * @return token
     */
    public String createToken(LoginVO loginVO) {
        String token = UUID.randomUUID().toString();
        loginVO.setToken(token);
        refreshToken(loginVO);

        Map<String, Object> claims = new HashMap<>();
        claims.put(Constants.CLAIMS_KEY_TOKEN, token);
        return createToken(claims);

    }

    private String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 刷新 Token 有效期
     * @param loginVO
     */
    public void refreshToken(LoginVO loginVO) {
        loginVO.setLoginTime(System.currentTimeMillis());
        loginVO.setExpireTime(loginVO.getLoginTime() + TimeUnit.MINUTES.toMillis(expireTime));
        String userKey = getTokenKey(loginVO.getToken());
        redisCache.setCacheObject(userKey, loginVO, expireTime, TimeUnit.MINUTES);
    }

    public LoginVO getLoginVO(HttpServletRequest request) {
        String token = getToken(request);
        if (StringUtils.isNormal(token)) {
            Claims claims = getClaims(token);
            String uuid = (String) claims.get(Constants.CLAIMS_KEY_TOKEN);
            String userKey = getTokenKey(uuid);
            LoginVO user = redisCache.getCacheObject(userKey);
            return user;
        }
        return null;
    }

    private String getToken(HttpServletRequest request) {
        String token = request.getHeader(header);
        if (StringUtils.isNormal(token) && token.startsWith(Constants.TOKEN_PREFIX)) {
            token = token.replace(Constants.TOKEN_PREFIX, "");
        }
        return token;
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public void verifyTokenExpire(LoginVO loginVO) {
        long expireTime = loginVO.getExpireTime();
        long currentTime = System.currentTimeMillis();
        if (expireTime - currentTime <= TimeUnit.MINUTES.toMillis(20)) {
            refreshToken(loginVO);
        }
    }

    public String getTokenKey(String uuid) {
        return Constants.REDIS_KEY_TOKEN + uuid;
    }
}
