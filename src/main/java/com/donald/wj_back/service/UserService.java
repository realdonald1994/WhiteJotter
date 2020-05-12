package com.donald.wj_back.service;

import com.donald.wj_back.pojo.User;

/**
 * @author Donald
 * @data 11/05/2020 19:37
 */
public interface UserService {


    boolean isExist(String username);

    User getByName(String username);

    User get(String username,String password);

    void add(User user);
}
