package com.lavender.controller;


import com.alibaba.fastjson.JSONObject;
import com.lavender.mapper.OperatingMapper;
import com.lavender.pojo.OperatingModel;
import com.lavender.pojo.ResultModel;
import com.lavender.pojo.UserModel;
import com.lavender.service.UserDetailService;
import com.lavender.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.concurrent.ExecutorService;

import static java.time.LocalTime.now;

@Slf4j
@Api(tags = "the arrangement of user")
@Controller
@CrossOrigin(originPatterns = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class UserController {

    private static String TOPIC_NAME = "lavender";

    @Autowired
    @Qualifier("userServiceImpl")
    private UserService userService;

    @Autowired
    OperatingMapper operatingMapper;

    @Autowired
    private UserDetailService userDetailService;

    @Resource
    private RabbitTemplate rabbitTemplate;

    String time=now()+"";
    String message="";

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String userLogin(@RequestParam("username") String username, @RequestParam("password") String password,HttpServletRequest request) {

//        request.getServletContext ().setAttribute ("username", System.currentTimeMillis () + 360000);

        // 调用service login方法去对比数据库的username 和 password
        ResultModel login = userService.login (username, password);
        System.out.println (login.toString ());

        if (login.getData () != "") {
            // 将用户名存入session
//            req.getSession (true).getServletContext ();
//            System.out.println ("管理员：" + username + "进入了管理系统");
//            System.out.println ("token内容" + login.getData ());
//            log.info ("管理员：" + username + "进入了管理系统");
//            log.info ("token内容" + login.getData ());
            rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员：" + username + "登入了管理系统");
            log.info("sendLavenderMessages=>, message:{}", message);
//            OperatingModel operatingModel = new OperatingModel ();
//            operatingModel.setMessage (message);
//            operatingMapper.add (operatingModel);
            request.getServletContext ().setAttribute ("token", login.getData ().toString ());
            return JSONObject.toJSONString (login);

        } else {

            return JSONObject.toJSONString (ResultModel.error ());


        }


    }

    /**
     * 获取当前用户信息
     * @param principal
     * @return
     */
    @RequestMapping("/admin/info")
    public UserModel getUserInfo(Principal principal){
        if(principal!=null){
            String username = principal.getName ();
            UserModel userByName = userDetailService.getUserByName (username);
            // 为了安全性 将password设置为null
            userByName.setPassword (null);

            return userByName;

        }
        return null;


    }

    /**
     * 退出登录
     * @return
     */
    @PostMapping("/logout")
    public ResultModel logout(){

        return ResultModel.success ();

    }

    /**
     * 查询全部用户
     * @param userModel
     * @return
     */
    @RequestMapping("/back/user/all")
    @PreAuthorize("hasAuthority('delete')")
    @ResponseBody
    public String userFindAll(UserModel userModel){


        ResultModel all = userService.findAll (userModel);

        return JSONObject.toJSONString (all);


    }


    @RequestMapping("/back/user/id")
    @ResponseBody
    public String userFindById(UserModel userModel){

        ResultModel byId = userService.findById (userModel);

        return JSONObject.toJSONString (byId);


    }


    @ApiOperation(value = "添加用户",response = UserModel.class,httpMethod = "POST")
    @PreAuthorize("hasAuthority('add')")
    @RequestMapping("/back/user/add")
    @ResponseBody
    public String userAdd(UserModel userModel,String username){
//        ResultModel add = userService.add (userModel);

        ResultModel resultModel = userService.checkUsername (username);
        if (ResultModel.success ().equals (resultModel)){
            ResultModel add = userService.add (userModel);
//            EmailRunnable emailRunnable = new EmailRunnable (userModel);
//            emailRunnable.run ();
            rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员：" + "增加了新的管理员"+ username);
//            OperatingModel operatingModel = new OperatingModel ();
//            operatingModel.setMessage (message);
//            operatingMapper.add (operatingModel);
            log.info("sendLavenderMessages=>, message:{}", message);
            return JSONObject.toJSONString (add);

        }else {
            return JSONObject.toJSONString (ResultModel.error ());
        }


//        return JSONObject.toJSONString (add);
    }


    @RequestMapping("/back/user/delete")
    @PreAuthorize("hasAuthority('delete')")
    @ResponseBody
    public String userDelete(UserModel userModel){

        ResultModel delete = userService.delete (userModel);
        // 发送消息到队列
        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message= "北京时间："+time+"  管理员号为：" + userModel.getId () + "卸甲归田了");
//        OperatingModel operatingModel = new OperatingModel ();
//        operatingModel.setMessage (message);
//        operatingMapper.add (operatingModel);
        log.info("sendLavenderMessages=>, message:{}", message);

        return JSONObject.toJSONString (delete);

    }


    @RequestMapping("/back/user/update")
    @PreAuthorize("hasAuthority('update')")
    @ResponseBody
    public String userEdit(UserModel userModel){
        ResultModel update = userService.update (userModel);

        rabbitTemplate.convertAndSend (TOPIC_NAME,"lavender.admin.*",message="北京时间："+time+"  超级管理员更新了 管理员：" + userModel.getUsername ()+"的信息" );
//        OperatingModel operatingModel = new OperatingModel ();
//        operatingModel.setMessage (message);
//        operatingMapper.add (operatingModel);
        log.info("sendLavenderMessages=>, message:{}", message);
        return  JSONObject.toJSONString (update);


    }


}
