package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminRoleMenuDao;
import com.donald.wj_back.pojo.AdminMenu;
import com.donald.wj_back.pojo.AdminRoleMenu;
import com.donald.wj_back.service.AdminRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
