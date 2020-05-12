package com.donald.wj_back.controller;

import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public int login(@RequestBody User user){
        String username=user.getUsername();
        username = HtmlUtils.htmlEscape(username);
        User user1 = userService.get(username, user.getPassword());
        if(null == user1){
            return 400;
        }else {
            return 200;
        }
    }

}
