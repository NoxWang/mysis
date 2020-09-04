package com.mysis.project.controller;

import com.mysis.common.base.vo.ResultVO;
import com.mysis.common.contants.Constants;
import com.mysis.framework.security.service.LoginService;
import com.mysis.framework.security.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ResultVO login(@RequestBody UserLoginVO userLoginVO) {
        ResultVO resultVO = ResultVO.success();
        String token = loginService.login(userLoginVO.getUsername(), userLoginVO.getPassword(),
                userLoginVO.getCaptcha(), userLoginVO.getUuid());
        resultVO.put(Constants.KEY_TOKEN, token);
        return resultVO;
    }
}
