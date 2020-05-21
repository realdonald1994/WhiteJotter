package com.donald.wj_back.service;

import com.donald.wj_back.pojo.AdminMenu;

import java.util.List;

/**
 * @author Donald
 * @data 17/05/2020 17:21
 */
public interface AdminMenuService {
    List<AdminMenu> getAllByParentId(int parentId);

    List<AdminMenu> getMenusByCurrentUser();

    List<AdminMenu> getMenusByRoleId(int rid);
}
