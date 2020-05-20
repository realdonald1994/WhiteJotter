package com.donald.wj_back.service;

import com.donald.wj_back.pojo.AdminRolePermission;

import java.util.List;

/**
 * @author Donald
 * @data 19/05/2020 14:56
 */
public interface AdminRolePermissionService {
    List<AdminRolePermission> findAllByRid(int rid);
}
