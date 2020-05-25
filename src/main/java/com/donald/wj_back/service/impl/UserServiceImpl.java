package com.donald.wj_back.service.impl;

import com.donald.wj_back.dao.UserDao;
import com.donald.wj_back.dto.UserDTO;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.pojo.User;
import com.donald.wj_back.service.AdminRoleService;
import com.donald.wj_back.service.AdminUserRoleService;
import com.donald.wj_back.service.UserService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
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
    @Autowired
    private AdminRoleService adminRoleService;
    @Autowired
    private AdminUserRoleService adminUserRoleService;

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
        user.setEnabled(true);
        userDao.save(user);
    }

    @Override
    public List<User> list() {
        List<User> users = userDao.findAll();
//        List<UserDTO> userDTOS = users
//                .stream().map(user -> (UserDTO) new UserDTO().convertFrom(user)).collect(Collectors.toList());
//        return userDTOS;
        List<AdminRole> roles;
        for (User user : users) {
            roles = adminRoleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        }
        return users;
    }

    @Override
    public void editUser(User user) {
        User userInDb = userDao.findByUsername(user.getUsername());
        userInDb.setPhone(user.getPhone());
        userInDb.setEmail(user.getEmail());
        userDao.save(userInDb);
        adminUserRoleService.saveRoleChanges(user.getId(),user.getRoles());
    }

    @Override
    public User restPassword(User requestUser) {
        User userInDb = userDao.findByUsername(requestUser.getUsername());
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        int times = 2;
        userInDb.setSalt(salt);
        String encodedPassword = new SimpleHash("md5", "123", salt, times).toString();
        userInDb.setPassword(encodedPassword);
        return userDao.save(userInDb);
    }

    @Override
    public void updateStatus(User requestUser) {
        User userInDb = userDao.findByUsername(requestUser.getUsername());
        userInDb.setEnabled(requestUser.isEnabled());
        userDao.save(userInDb);
    }

    @Override
    public void deleteUser(int id) {
        userDao.deleteById(id);
    }


}
