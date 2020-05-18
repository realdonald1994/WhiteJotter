package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.AdminMenuDao;
import com.donald.wj_back.pojo.AdminMenu;
import com.donald.wj_back.pojo.AdminRoleMenu;
import com.donald.wj_back.pojo.AdminUserRole;
import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.AdminMenuService;
import com.donald.wj_back.service.AdminRoleMenuService;
import com.donald.wj_back.service.AdminUserRoleService;
import com.donald.wj_back.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Donald
 * @data 17/05/2020 17:22
 */
@Service
public class AdminMenuServiceImpl implements AdminMenuService {
    @Autowired
    private AdminMenuDao adminMenuDao;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminUserRoleService adminUserRoleService;

    @Autowired
    private AdminRoleMenuService adminRoleMenuService;


    @Override
    public List<AdminMenu> getAllByParentId(int parentId) {
        return adminMenuDao.findAllByParentId(parentId);
    }

    @Override
    public List<AdminMenu> getMenusByCurrentUser(){
        System.out.println(SecurityUtils.getSubject());
        String username = SecurityUtils.getSubject().getPrincipal().toString();
        User user = userService.getByName(username);

        List<Integer> rids = adminUserRoleService.listAllByUid(user.getId()).stream().map(AdminUserRole::getRid).collect(Collectors.toList());

        List<AdminMenu> menus = adminRoleMenuService.findAllByRid(rids).stream().distinct().collect(Collectors.toList());
        return menus;
    }

    public void handleMenus(List<AdminMenu> menus){
        for (AdminMenu menu : menus) {
            List<AdminMenu> children = getAllByParentId(menu.getId());
            menu.setChildren(children);
        }
        Iterator<AdminMenu> iterator = menus.iterator();
        while (iterator.hasNext()){
            AdminMenu menu = iterator.next();
            if (menu.getParentId() !=0){
                iterator.remove();
            }
        }
    }
}
