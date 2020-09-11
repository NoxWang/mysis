package com.mysis.project.service.impl;

import com.mysis.common.utils.StringUtils;
import com.mysis.project.dao.IMenuDao;
import com.mysis.project.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class MenuService implements IMenuService {

    @Autowired
    private IMenuDao menuDao;

    @Override
    public Set<String> selectMenuPermBuUserId(Long userId) {
        Set<String> perms = menuDao.selectMenuPermByUserId(userId);
        Set<String> menuPerm = new HashSet<>();
        for (String perm : perms) {
            if (StringUtils.isNormal(perm)) {
                menuPerm.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return menuPerm;
    }
}
