package com.lavender.service;


import com.lavender.pojo.Permission;
import com.lavender.pojo.ResultModel;
import com.lavender.pojo.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService extends BaseService<UserModel>{

    ResultModel login(String name, String password);

    ResultModel password(UserModel model);


    ResultModel updatePassword(UserModel userModel);

    ResultModel checkUsername(String username);

//    UserModel getUserByName(String name);
//
//
//    List<Permission> getPermissionByUserId(Long userId);

}
