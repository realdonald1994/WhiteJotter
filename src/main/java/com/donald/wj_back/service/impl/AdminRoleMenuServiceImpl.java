package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminRoleMenuDao;
import com.donald.wj_back.pojo.AdminMenu;
import com.donald.wj_back.pojo.AdminRoleMenu;
import com.donald.wj_back.service.AdminRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Donald
 * @data 17/05/2020 17:52
 */
@Service
public class AdminRoleMenuServiceImpl implements AdminRoleMenuService {

    @Autowired
    private AdminRoleMenuDao adminRoleMenuDao;
    @Override
    public List<AdminRoleMenu> findAllByRid(List<Integer> rids) {
        return adminRoleMenuDao.findAllByRidIn(rids);
    }

    @Override
    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDao.findAllByRid(rid);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds) {
        adminRoleMenuDao.deleteAllByRid(rid);
        List<AdminRoleMenu> rms = new ArrayList<>();
        for (Integer mid:menusIds.get("menusIds")){
            AdminRoleMenu arm = new AdminRoleMenu();
            arm.setMid(mid);
            arm.setRid(rid);
            rms.add(arm);
        }
        adminRoleMenuDao.saveAll(rms);
    }
}
