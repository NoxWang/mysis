package com.mysis.framework.security.service;

import com.mysis.common.base.exception.BaseException;
import com.mysis.common.enums.UserStatusEnum;
import com.mysis.common.utils.ObjectUtils;
import com.mysis.framework.security.vo.LoginVO;
import com.mysis.project.service.IUserService;
import com.mysis.project.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 自定义用户认证服务
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO = userService.selectUserByUsername(username);
        if (ObjectUtils.isNull(userVO)) {
            log.info("登录用户{}不存在", username);
            throw new UsernameNotFoundException("登录用户" + username + "不存在");
        } else if (UserStatusEnum.DISABLE.getCode().equals(userVO.getDelFlag())) {
            log.info("登录用户{}已被删除", username);
            throw new BaseException("对不起，您的帐号" + username + "已被删除");
        } else if (UserStatusEnum.DISABLE.getCode().equals(userVO.getStatus())) {
            log.info("登录用户{}已被停用", username);
            throw new BaseException("对不起，您的帐号" + username + "已停用");
        }

        return new LoginVO(userVO);
    }
}
