package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Donald
 * @data 11/05/2020 17:27
 */
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user){
        Subject subject = SecurityUtils.getSubject();
        String username = user.getUsername();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username,user.getPassword());
        usernamePasswordToken.setRememberMe(true);
        try {
            subject.login(usernamePasswordToken);
            return ResponseEntity.ok(username);
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return new ResponseEntity(new Error("Username does not exist"), HttpStatus.NOT_FOUND);
        }catch (IncorrectCredentialsException e){
            e.printStackTrace();
            return new ResponseEntity(new Error("Wrong password"),HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("register")
    public ResponseEntity<User> register(@RequestBody User user){
        String username = user.getUsername();
        String password = user.getPassword();
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);

        boolean exist = userService.isExist(username);
        if(exist){
            return new ResponseEntity(new Error("Username already exists"),HttpStatus.BAD_REQUEST);
        }
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();

        int times =2;
        String encodePassword = new SimpleHash("md5",password,salt,times).toString();

        user.setSalt(salt);
        user.setPassword(encodePassword);
        userService.add(user);
        return ResponseEntity.ok(user);

    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        String message = "Logout Successfully";
        return ResponseEntity.ok(message);
    }

    @GetMapping("/authentication")
    public String authentication() {
        return "Successful identity verification";
    }
}
