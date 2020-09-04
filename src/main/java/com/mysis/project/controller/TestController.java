package com.mysis.project.controller;

import com.mysis.common.base.vo.ResultVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @GetMapping("/test")
    @ResponseBody
    public ResultVO test() {
        return ResultVO.success("okk");
    }
}
