package com.mysis.project.controller;

import com.mysis.common.base.vo.ResultVO;
import com.mysis.project.service.ICaptchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CaptchaController {

    @Autowired
    private ICaptchaService captchaService;

    /**
     * 生成验证码
     */
    @GetMapping("/captcha")
    @ResponseBody
    public ResultVO getCaptcha() {
        return captchaService.getCaptcha();
    }
}
