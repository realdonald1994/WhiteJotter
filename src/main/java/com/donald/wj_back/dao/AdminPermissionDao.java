package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.AdminPermission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Donald
 * @data 19/05/2020 14:07
 */
public interface AdminPermissionDao extends JpaRepository<AdminPermission,Integer> {
    AdminPermission findById(int id);
}
