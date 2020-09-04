package com.mysis.project.service.impl;

import com.mysis.project.dao.IUserDao;
import com.mysis.project.service.IUserService;
import com.mysis.project.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public UserVO selectUserByUsername(String username) {
        return userDao.selectUserByUsername(username);
    }
}
