package com.donald.wj_back.controller;

import com.donald.wj_back.dto.UserDTO;
import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Donald
 * @data 19/05/2020 14:12
 */
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @RequiresPermissions("/api/admin/user")
    @GetMapping("/admin/user")
    public ResponseEntity<List<User>> listUsers() throws Exception{
        return ResponseEntity.ok(userService.list());
    }
}
