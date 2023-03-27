package com.lavender.service;

import com.lavender.pojo.Permission;
import com.lavender.pojo.UserModel;

import java.util.List;

public interface UserDetailService {

    UserModel getUserByName(String name);


    List<Permission> getPermissionByUserId(Long userId);
}
