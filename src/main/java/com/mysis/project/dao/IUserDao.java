package com.mysis.project.dao;

import com.mysis.project.vo.UserVO;
import org.springframework.stereotype.Repository;

/**
 * user_t DAO层
 */
@Repository
public interface IUserDao {
    /**
     * 通过username查询用户
     * @param username 用户名
     * @return 用户VO
     */
    UserVO selectUserByUsername(String username);
}
