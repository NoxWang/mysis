package com.mysis.project.service.impl;

import com.mysis.common.base.vo.ResultVO;
import com.mysis.common.contants.Constants;
import com.mysis.framework.redis.RedisCache;
import com.mysis.project.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class CaptchaService implements ICaptchaService {

    // 验证码字符集
    public static final String VERIFY_SOURCE = "23456789ABCDEFGHJKMNPQRSTUVWXYZ";

    // 验证码长度
    public static final int VERIFY_SIZE = 4;

    // 验证码有效时间（单位：分钟）
    public static final int CAPTCHA_EXPIRATION = 1;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResultVO getCaptcha() {

        String verifyCode = generateCode(VERIFY_SIZE);
        String uuid = UUID.randomUUID().toString();
        String verifyKey = Constants.REDIS_KEY_CAPTCHA + uuid;
        redisCache.setCacheObject(verifyKey, verifyCode, CAPTCHA_EXPIRATION, TimeUnit.MINUTES);

        ResultVO resultVO = ResultVO.success();
        resultVO.put("uuid", uuid);
        resultVO.put("code", verifyCode);
        return resultVO;
    }

    private String generateCode(int size) {
        int sourceLen = VERIFY_SOURCE.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder code = new StringBuilder(size);
        for (int i = 0; i < size; i++) {
            code.append(VERIFY_SOURCE.charAt(rand.nextInt(sourceLen - 1)));
        }
        return code.toString();
    }
}
