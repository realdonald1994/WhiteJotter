package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Donald
 * @data 20/05/2020 11:20
 */
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private AdminRoleService adminRoleService;

    @GetMapping("/admin/role")
    public ResponseEntity<List<AdminRole>> listRoles() throws Exception{
        return ResponseEntity.ok(adminRoleService.list());
    }
}
