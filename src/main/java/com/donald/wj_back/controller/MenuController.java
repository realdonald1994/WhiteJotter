package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.AdminMenu;
import com.donald.wj_back.service.AdminMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Donald
 * @data 17/05/2020 20:26
 */
@RestController
@RequestMapping("/api")
public class MenuController {

    @Autowired
    private AdminMenuService adminMenuService;


    @GetMapping("menu")
    public List<AdminMenu> menu(){
        return adminMenuService.getMenusByCurrentUser();
    }

    @GetMapping("/admin/role/menu")
    public ResponseEntity<List<AdminMenu>> listMenus(){
        return ResponseEntity.ok(adminMenuService.getMenusByRoleId(1));
    }
}
