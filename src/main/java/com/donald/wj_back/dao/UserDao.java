package com.donald.wj_back.dao;

import com.donald.wj_back.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Donald
 * @data 11/05/2020 19:34
 */
public interface UserDao extends JpaRepository<User,Integer> {
    /**
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     *
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username,String password);
}
