package com.donald.wj_back.dto;

import com.donald.wj_back.dto.base.OutputConverter;
import com.donald.wj_back.pojo.AdminRole;
import com.donald.wj_back.pojo.User;

import java.util.List;


public class UserDTO implements OutputConverter<UserDTO, User> {

    private Integer id;

    private String username;

    private String phone;

    private String email;

    private boolean enabled;

    private List<AdminRole> roles;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<AdminRole> getRoles() {
        return roles;
    }

    public void setRoles(List<AdminRole> roles) {
        this.roles = roles;
    }
}