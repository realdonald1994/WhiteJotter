package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.AdminMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Donald
 * @data 17/05/2020 17:17
 */
public interface AdminMenuDao extends JpaRepository<AdminMenu,Integer> {
    List<AdminMenu> findAllByParentId(int parentId);
}
