package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminRolePermissionDao;
import com.donald.wj_back.pojo.AdminRolePermission;
import com.donald.wj_back.service.AdminRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Donald
 * @data 19/05/2020 14:57
 */
@Service
public class AdminRolePermissionServiceImpl implements AdminRolePermissionService {
    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;
    @Override
    public List<AdminRolePermission> findAllByRid(int rid) {
        return adminRolePermissionDao.findAllByRid(rid);
    }
}
