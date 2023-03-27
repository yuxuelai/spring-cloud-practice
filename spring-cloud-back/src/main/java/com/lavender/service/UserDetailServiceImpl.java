package com.lavender.service;

import com.lavender.mapper.UserMapper;
import com.lavender.pojo.Permission;
import com.lavender.pojo.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * @author Administrator
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService ,UserDetailService{

//    @Qualifier("userServiceImpl")
//    @Autowired

    String redisKey= "USER_INFO";

    @Resource
    UserMapper userMapper;

    @Resource
    UserDetailService userDetailService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UserModel user = userDetailService.getUserByName (username);
        List<Permission> permissionList = userDetailService.getPermissionByUserId (user.getId ());
        HashSet<Permission> permissions = new HashSet<> (permissionList);

        user.setAuthorities (permissions);
//        System.out.println (user.toString ());

        return user;
    }


    @Override
    public UserModel getUserByName(String name) {
        List<UserModel> users =userMapper.getUserByName (name);
        Assert.isTrue (users.size ()==1,"您输入的账户不存在，或者有多个相同的账户");
        return users.get (0);
    }

    @Override
    public List<Permission> getPermissionByUserId(Long userId) {
        return userMapper.getPermissionByUserId (userId);
    }
}
