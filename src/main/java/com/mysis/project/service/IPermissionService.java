package com.mysis.project.service;

import com.mysis.project.vo.UserVO;

import java.util.Set;

public interface IPermissionService {
    /**
     * 获取菜单权限
     * @param userVO 用户信息
     * @return 拥有的菜单权限
     */
    Set<String> getMenuPermission(UserVO userVO);
}
