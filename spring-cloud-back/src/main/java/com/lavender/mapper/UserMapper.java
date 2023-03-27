package com.lavender.mapper;

import com.lavender.pojo.Permission;
import com.lavender.pojo.UserModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UserMapper {

    // 新增用户
    int add(UserModel userModel);

    // 删除用户
    int delete(@Param ("id")Long id);

    // 更新用户
    int update(UserModel userModel);

    // 查询用户通过id

    UserModel findById(@Param ("id")Long id);

    // 查询全部用户
    List<UserModel> findAll();


    UserModel findByUsernameAndPassword(@Param ("username") String username,@Param ("password") String password);

    List<UserModel> findByUsername(@Param ("username")String username);

    UserModel updatePassword(UserModel userModel);

    List<UserModel> getUserByName(String name);

    List<Permission> getPermissionByUserId(Long userId);




}
