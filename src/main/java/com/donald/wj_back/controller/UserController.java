package com.donald.wj_back.controller;

import com.donald.wj_back.dto.UserDTO;
import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PutMapping("/admin/user")
    public ResponseEntity<String> editUser(@RequestBody User updateUser){
        userService.editUser(updateUser);
        String message = "Modify user successfully";
        return ResponseEntity.ok(message);
    }

    @PutMapping("/admin/user/password")
    public ResponseEntity<String> resetPassword(@RequestBody @Valid User requestUser){
        userService.restPassword(requestUser);
        return ResponseEntity.ok("reset password successfully");
    }
}
