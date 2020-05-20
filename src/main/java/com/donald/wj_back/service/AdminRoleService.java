package com.donald.wj_back.service;

import com.donald.wj_back.pojo.AdminRole;

import java.util.List;

/**
 * @author Donald
 * @data 17/05/2020 17:21
 */
public interface AdminRoleService {
    List<AdminRole> listRolesByUser(String username);
    List<AdminRole> list();
}
