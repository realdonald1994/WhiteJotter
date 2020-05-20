package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminPermissionDao;
import com.donald.wj_back.dao.AdminRolePermissionDao;
import com.donald.wj_back.pojo.AdminPermission;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.pojo.AdminRolePermission;
import com.donald.wj_back.service.AdminPermissionService;
import com.donald.wj_back.service.AdminRolePermissionService;
import com.donald.wj_back.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Donald
 * @data 19/05/2020 14:57
 */
@Service
public class AdminPermissionServiceImpl implements AdminPermissionService {

    @Autowired
    private AdminPermissionDao adminPermissionDao;

    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminRolePermissionDao adminRolePermissionDao;
    @Autowired
    private AdminRolePermissionService adminRolePermissionService;




    @Override
    public List<AdminPermission> list() {
        return adminPermissionDao.findAll();
    }

    @Override
    public Set<String> listPermissionURLByUser(String username){
        List<Integer> rids = adminRoleService.listRolesByUser(username).stream().map(AdminRole::getId).collect(Collectors.toList());

        List<Integer> pids = adminRolePermissionDao.findAllByRidIn(rids).stream().map(AdminRolePermission::getPid).collect(Collectors.toList());

        List<AdminPermission> perms = adminPermissionDao.findAllById(pids);
        Set<String> URLs = perms.stream().map(AdminPermission::getUrl).collect(Collectors.toSet());
        return URLs;
    }

    @Override
    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<Integer> pids = adminRolePermissionService.findAllByRid(rid).stream().map(AdminRolePermission::getPid).collect(Collectors.toList());
        return adminPermissionDao.findAllById(pids);
    }

    @Override
    public boolean needFilter(String requestApi) {
        List<AdminPermission> ps = adminPermissionDao.findAll();
        for (AdminPermission p : ps) {
            if(p.getUrl().equals(requestApi)){
                return true;
            }
        }
        return false;
    }
}
