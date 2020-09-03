package com.mysis.framework.security.handler;

import com.alibaba.fastjson.JSON;
import com.mysis.common.base.vo.ResultVO;
import com.mysis.common.contants.HttpStatus;
import com.mysis.common.utils.ServletUtils;
import com.mysis.common.utils.StringUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        int code = HttpStatus.UNAUTHORIZED;
        String msg = StringUtils.format("请求{}认证失败", request.getRequestURI());
        ServletUtils.renderString(response, JSON.toJSONString(ResultVO.error(code, msg)));
    }
}
