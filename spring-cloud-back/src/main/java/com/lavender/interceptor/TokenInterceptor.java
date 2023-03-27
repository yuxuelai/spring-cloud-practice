package com.lavender.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.lavender.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    RedisUtils redisUtils;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,Object handler){

        if("OPTIONS".equals (request.getMethod ())){
            response.setStatus (HttpServletResponse.SC_OK);
            return true;

        }
        response.setCharacterEncoding ("utf-8");
        String token = response.getHeader ("Authorization");
        if(token!=null){

            log.info ("想要的token"+token);
            if(redisUtils.get ("token").equals (token)){
                log.info ("通过拦截器");
                return true;

            }else{
                log.info ("已经存在一个token 未通过拦截器");

            }

        }
        response.setContentType ("application/json;charset=utf-8");
        try{
            JSONObject json = new JSONObject ();
            json.put ("message","token校验失败");
            json.put ("code","500");
            response.getWriter ().append (json.toString ());

        } catch (IOException e) {
            return false;
        }


        return false;
    }



}
