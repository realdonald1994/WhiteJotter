package com.donald.wj_back.service;

import com.donald.wj_back.pojo.AdminMenu;
import com.donald.wj_back.pojo.AdminRoleMenu;

import java.util.List;
import java.util.Map;

/**
 * @author Donald
 * @data 17/05/2020 17:21
 */
public interface AdminRoleMenuService {
    List<AdminRoleMenu> findAllByRid(List<Integer> rids);
    List<AdminRoleMenu> findAllByRid(int rid);

    void updateRoleMenu(int rid, Map<String, List<Integer>> menusIds);
}
