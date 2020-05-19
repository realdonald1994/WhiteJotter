package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.AdminRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Donald
 * @data 19/05/2020 14:09
 */
public interface AdminRolePermissionDao extends JpaRepository<AdminRolePermission,Integer> {
    List<AdminRolePermission> findAllByRid(List<Integer> rids);
}
