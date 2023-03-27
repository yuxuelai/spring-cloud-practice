package com.lavender.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.lavender.mapper.UserMapper;
import com.lavender.pojo.Permission;
import com.lavender.pojo.RespBean;
import com.lavender.pojo.ResultModel;
import com.lavender.pojo.UserModel;
import com.lavender.service.UserDetailServiceImpl;
import com.lavender.service.UserService;
import com.lavender.utils.JwtUtils;
import com.lavender.utils.Md5;
import com.lavender.utils.RedisUtils;
import com.lavender.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author Administrator
 */
@Service
public class UserServiceImpl implements UserService {

    String redisKey= "USER_INFO";

    @Resource
    UserDetailsService userDetailsService;


    @Resource
    UserService userService;

    @Resource
    private JwtUtils jwtUtils;

    @Value ("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtils redisUtils;

    @Autowired
    private UserMapper userMapper;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder ();


    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }



    @Override
    public ResultModel login(String username, String password) {

//        UserDetails userDetails;
        //1. 首先到redis里读取 如果没有 从数据库中读取
//        ValueOperations<String,UserDetails> valueOperations = redisTemplate.opsForValue ();
        String userInfo = redisUtils.get (redisKey+username);
        if(userInfo==null){
//            log.info("redis 没有该id:{}的用户数据，准备从数据库取",id);
            // 获取数据库的数据
            List<UserModel> userByName = userMapper.getUserByName (username);

            if(userByName!=null){

//                System.out.println ("用户id:{}数据已经找到，放到redis里");
                // 存储userDetail到redis中
                redisUtils.set (redisKey+username, JSONObject.toJSONString (userByName));

            }else{

                System.out.println ("用户id:{}数据库也没有找到，容易造成缓存穿透");
            }
        }

        // 获取userDetail
        UserDetails userDetails = userDetailsService.loadUserByUsername (username);

        // 1 判断用户是否被禁用
        if(userDetails.isEnabled ()){
            // 2前端获取的密码通过 passwordEncoder 和 数据库中的密码对比
            if(bCryptPasswordEncoder.matches (password,userDetails.getPassword ())){

                // 3 更新security登录用户对象
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken (userDetails,null,userDetails.getAuthorities ());
                // 4 将authenticationToken 放到 spring securityContext中

                SecurityContextHolder.getContext ().setAuthentication (authenticationToken);

                // 5 生成token
                String token = jwtUtils.generateToken (userDetails);
                // 将token存入redis中
                redisUtils.set ("token",token,30, TimeUnit.MINUTES);

                // 6 将token和头部信息存放到map中 登陆成功后带给前端
                HashMap<String,String> tokenMap = new HashMap<> ();
                tokenMap.put ("token",token);
                tokenMap.put ("tokenHead",tokenHead);
                return ResultModel.getResult (tokenMap,1);

            }
            return ResultModel.error ();
        }
        return ResultModel.error ();


    }

    @Override
    public ResultModel password(UserModel model) {

        return null;
    }

    @Override
    public ResultModel updatePassword(UserModel userModel) {
        // 对密码进行加密
        String mingwen = userModel.getPassword ();
        String anwen = bCryptPasswordEncoder.encode (mingwen);
        userModel.setPassword (anwen);
        if(userModel==null){

            return ResultModel.getResult (-1);
        }else{

            System.out.println (ResultModel.getResult (userMapper.updatePassword (userModel)+"--------"));
            return ResultModel.getResult (userMapper.updatePassword (userModel));

        }



    }

    @Override
    public ResultModel checkUsername(String username) {

        List<UserModel> byUsername = userMapper.findByUsername (username);
//        System.out.println (byUsername);
        if (byUsername.size ()==0){

            return ResultModel.success ();
        }

        return ResultModel.error ();

    }




    @Override
    public ResultModel enable(UserModel userModel) {
        return null;
    }

    @Override
    public ResultModel add(UserModel userModel) {
        // 进行密码安全处理 加密
//        UserDao userDao = new UserImpl ();
        String mingwen = userModel.getPassword ();
//        System.out.println (mingwen);
        String anwen = bCryptPasswordEncoder.encode (mingwen);
        userModel.setPassword (anwen);
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker ();
        userModel.setId (snowflakeIdWorker.nextId ());

        return ResultModel.getResult (userMapper.add(userModel));
    }


    @Override
    public ResultModel update(UserModel userModel) {
        // 1. 对密码加密 表单没有传入密码 所以不用设置
        return ResultModel.getResult (userMapper.update (userModel));

    }

    @Override
    public ResultModel delete(UserModel  userModel) {
        return ResultModel.getResult (userMapper.delete (userModel.getId ()));

    }

    @Override
    public ResultModel findById(UserModel userModel) {


        return ResultModel.getResult (userMapper.findById (userModel.getId ()));


    }

    @Override
    public ResultModel findAll(UserModel userModel) {

        return ResultModel.getResult (userMapper.findAll ());
    }

    @Override
    public ResultModel count(UserModel userModel) {
        return null;
    }

    @Override
    public ResultModel find(UserModel userModel) {
        return null;
    }



}


