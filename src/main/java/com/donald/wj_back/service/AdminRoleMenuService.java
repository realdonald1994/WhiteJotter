package com.donald.wj_back.service;

import com.donald.wj_back.pojo.AdminMenu;
import com.donald.wj_back.pojo.AdminRoleMenu;

import java.util.List;

/**
 * @author Donald
 * @data 17/05/2020 17:21
 */
public interface AdminRoleMenuService {
    List<AdminRoleMenu> findAllByRid(List<Integer> rids);
}
