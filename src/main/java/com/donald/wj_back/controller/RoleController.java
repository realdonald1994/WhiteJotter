package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.AdminPermission;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.AdminPermissionService;
import com.donald.wj_back.service.AdminRoleMenuService;
import com.donald.wj_back.service.AdminRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Donald
 * @data 20/05/2020 11:20
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
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
    @PutMapping("/admin/role/status")
    public ResponseEntity<String> updateStatus(@RequestBody AdminRole role){
        adminRoleService.updateStatus(role);
        return ResponseEntity.ok("Role's status is updated");
    }
    @PostMapping("/admin/role")
    public ResponseEntity<String> addRole(@RequestBody AdminRole role){
        adminRoleService.addOrUpdate(role);
        return ResponseEntity.ok("Add Role Successfully");
    }
    @DeleteMapping("/admin/role/delete")
    public ResponseEntity<String> deleteRole(@RequestBody AdminRole role){
        adminRoleService.deleteRole(role.getId());
        return ResponseEntity.ok("Deleted Role Successfully");
    }
}
