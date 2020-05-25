package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminRoleDao;
import com.donald.wj_back.pojo.AdminMenu;
import com.donald.wj_back.pojo.AdminPermission;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.pojo.AdminUserRole;
import com.donald.wj_back.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminMenuService adminMenuService;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;
    @Override
    public List<AdminRole> listRolesByUser(String username) {
        Integer uid = userService.getByName(username).getId();
        List<Integer> rids = adminUserRoleService.listAllByUid(uid).stream().map(AdminUserRole::getRid).collect(Collectors.toList());
        return adminRoleDao.findAllById(rids);

    }

    @Override
    public List<AdminRole> list() {
        List<AdminRole> roles = adminRoleDao.findAll();
        List<AdminPermission> perms;
        List<AdminMenu> menus;
        for (AdminRole role : roles) {
            perms = adminPermissionService.listPermsByRoleId(role.getId());
            menus = adminMenuService.getMenusByRoleId(role.getId());
            role.setPerms(perms);
            role.setMenus(menus);
        }
        return roles;
    }

    @Override
    public void addOrUpdate(AdminRole adminRole) {
        adminRole.setEnabled(true);
        adminRoleDao.save(adminRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void editRole(AdminRole adminRole) {
        adminRoleDao.save(adminRole);
        adminRolePermissionService.savePermChanges(adminRole.getId(),adminRole.getPerms());
    }

    @Override
    public AdminRole updateStatus(AdminRole role) {
        AdminRole roleInDb = adminRoleDao.findById(role.getId().intValue());
        roleInDb.setEnabled(role.isEnabled());
        return adminRoleDao.save(role);
    }

    @Override
    public void deleteRole(Integer id) {
        adminRoleDao.deleteById(id);
    }
}
