package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminUserRoleDao;
import com.donald.wj_back.pojo.AdminUserRole;
import com.donald.wj_back.service.AdminUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Donald
 * @data 17/05/2020 17:28
 */
@Service
public class AdminUserRoleServiceImpl implements AdminUserRoleService {
    @Autowired
    private AdminUserRoleDao adminUserRoleDao;
    @Override
    public List<AdminUserRole> listAllByUid(int uid) {
        return adminUserRoleDao.findAllByUid(uid);
    }
}
