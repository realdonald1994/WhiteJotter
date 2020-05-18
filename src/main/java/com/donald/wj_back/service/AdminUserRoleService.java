package com.donald.wj_back.service;

import com.donald.wj_back.pojo.AdminUserRole;

import java.util.List;

/**
 * @author Donald
 * @data 17/05/2020 17:21
 */
public interface AdminUserRoleService {
    List<AdminUserRole> listAllByUid(int uid);
}
