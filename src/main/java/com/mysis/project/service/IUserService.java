package com.mysis.project.service;

import com.mysis.project.vo.UserVO;

public interface IUserService {
    /**
     * 通过username查询用户
     * @param username 用户名
     * @return 用户VO
     */
    UserVO selectUserByUsername(String username);

}
