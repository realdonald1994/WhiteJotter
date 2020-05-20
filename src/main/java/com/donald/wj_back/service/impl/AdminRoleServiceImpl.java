package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminRoleDao;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.pojo.AdminUserRole;
import com.donald.wj_back.service.AdminRoleService;
import com.donald.wj_back.service.AdminUserRoleService;
import com.donald.wj_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Donald
 * @data 19/05/2020 15:01
 */
@Service
public class AdminRoleServiceImpl implements AdminRoleService {
    @Autowired
    private UserService userService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;
    @Autowired
    private AdminRoleDao adminRoleDao;
    @Override
    public List<AdminRole> listRolesByUser(String username) {
        Integer uid = userService.getByName(username).getId();
        List<Integer> rids = adminUserRoleService.listAllByUid(uid).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDao.findAllById(rids);

    }

    @Override
    public List<AdminRole> list() {
        return adminRoleDao.findAll();
    }
}
