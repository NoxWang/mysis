package com.mysis.project.service.impl;

import com.mysis.project.service.IMenuService;
import com.mysis.project.service.IPermissionService;
import com.mysis.project.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionService implements IPermissionService {

    @Autowired
    private IMenuService menuService;

    @Override
    public Set<String> getMenuPermission(UserVO userVO) {
        Set<String> menuPerm = new HashSet<>();
        if (userVO.isAdmin()) {
            menuPerm.add("*:*:*");
        } else {
            menuPerm.addAll(menuService.selectMenuPermBuUserId(userVO.getUserId()));
        }
        return menuPerm;
    }
}
