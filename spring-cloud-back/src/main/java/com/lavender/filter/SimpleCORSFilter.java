package com.lavender.filter;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : [huawei]
 * @version : [v1.0]
 * @className : SimpleCORSFilter
 * @description : [描述说明该类的功能]
 * @createTime : [2023/2/23 19:28]
 */

@WebFilter(filterName = "SimpleCORSFilter", urlPatterns = {"/*"})
@Component
@Configuration
public class SimpleCORSFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest)req;

        // 此处 setHeader、addHeader 方法都可用。但 addHeader时写多个会报错：“...,but only one is allowed”
//        response.setHeader("Access-Control-Allow-Origin", "*");

//        response.addHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        // 解决预请求（发送2次请求），此问题也可在 nginx 中作相似设置解决。
        response.setHeader("Access-Control-Allow-Headers", "img,x-requested-with,Cache-Control,Pragma,Content-Type,username,userId,Token, Authorization,Content-Type");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        String method = request.getMethod();
        if (method.equalsIgnoreCase("OPTIONS")) {
            response.getOutputStream().write("Success".getBytes("utf-8"));
        } else {
            filterChain.doFilter(request, response);
        }

    }

    @Override
    public void destroy() {

    }
}
