package com.donald.wj_back.service;

import com.donald.wj_back.pojo.AdminPermission;

import java.util.List;
import java.util.Set;

/**
 * @author Donald
 * @data 19/05/2020 14:56
 */
public interface AdminPermissionService {
    List<AdminPermission> list();
    boolean needFilter(String requestApi);
    Set<String> listPermissionURLByUser(String username);
}
