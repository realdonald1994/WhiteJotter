package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.AdminPermission;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.service.AdminPermissionService;
import com.donald.wj_back.service.AdminRoleMenuService;
import com.donald.wj_back.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Donald
 * @data 20/05/2020 11:20
 */
@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminPermissionService adminPermissionService;
    @Autowired
    private AdminRoleMenuService adminRoleMenuService;

    @GetMapping("/admin/role")
    public ResponseEntity<List<AdminRole>> listRoles() throws Exception{
        return ResponseEntity.ok(adminRoleService.list());
    }

    @GetMapping("/admin/role/perm")
    public ResponseEntity<List<AdminPermission>> listPerms() throws Exception{
        return ResponseEntity.ok(adminPermissionService.list());
    }

    @PutMapping("/admin/role")
    public ResponseEntity<String> editRole(@RequestBody AdminRole  updateRole){
        adminRoleService.editRole(updateRole);
        String message = "Modify Role Successfully";
        return ResponseEntity.ok(message);
    }

    @PutMapping("/admin/role/menu")
    public ResponseEntity<String> editRoleMenu(@RequestParam int rid, @RequestBody Map<String, List<Integer>> menusIds){
        adminRoleMenuService.updateRoleMenu(rid,menusIds);
        return ResponseEntity.ok("Modify Menu Successfully");
    }
}
