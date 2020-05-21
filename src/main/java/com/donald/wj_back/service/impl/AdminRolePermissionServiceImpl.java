package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminRolePermissionDao;
import com.donald.wj_back.pojo.AdminPermission;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.pojo.AdminRolePermission;
import com.donald.wj_back.service.AdminRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public void savePermChanges(int rid, List<AdminPermission> perms) {
        adminRolePermissionDao.deleteAllByRid(rid);

        List<AdminRolePermission> arp = new ArrayList<>();
        perms.forEach(adminPermission -> {
            AdminRolePermission adminRolePermission = new AdminRolePermission();
            adminRolePermission.setRid(rid);
            adminRolePermission.setPid(adminPermission.getId());
            arp.add(adminRolePermission);
        });
        adminRolePermissionDao.saveAll(arp);
    }
}
