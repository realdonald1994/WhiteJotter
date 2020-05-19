package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.UserDao;
import com.donald.wj_back.dto.UserDTO;
import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Donald
 * @data 11/05/2020 19:37
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean isExist(String username) {
        User user = getByName(username);
        return null!=user;
    }

    @Override
    public User getByName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User get(String username, String password) {
        return userDao.findByUsernameAndPassword(username,password);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(User user) {
        userDao.save(user);
    }

    @Override
    public List<User> list() {
        List<User> users = userDao.findAll();
//        List<UserDTO> userDTOS = users
//                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());
//        return userDTOS;
        return users;
    }
}
