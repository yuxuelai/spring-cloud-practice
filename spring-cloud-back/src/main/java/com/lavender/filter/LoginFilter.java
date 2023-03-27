package com.lavender.filter;

import com.alibaba.fastjson.JSONObject;
import com.lavender.pojo.ResultModel;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpServletResponse response=(HttpServletResponse)servletResponse;

        String path = request.getServletPath ();

        if(path.startsWith ("/back/*")){
            ServletContext application = request.getServletContext ();

            Object attribute = application.getAttribute ("username");

            if (attribute == null) {
                response.getWriter().println(JSONObject.toJSONString(ResultModel.getResult ("未登录")));
                response.sendRedirect("/html/login.html");
            } else {
// 放行
                filterChain.doFilter(request, response);
            }
        } else {
            filterChain.doFilter(request, response);
        }

        }

//        Long username = (Long) request.getServletContext ().getAttribute ("username");
//        System.out.println (username);
////            二次判断
//        if(username ==null){
////              没有则重定向到登录页
//            response.addHeader("Access-Control-Expose-Headers", "REDIRECT,CONTEXTPATH");
//            response.addHeader ("REDIRECT", "REDIRECT"); //告诉ajax这是重定向
//            response.addHeader("CONTEXTPATH", "http://localhost:8082/html/login.html"); //重定向地址
//
//            response.getWriter ().println ("请先登录！~");
//
////            response.setHeader ("location","/html/login.html");
////            response.sendRedirect ("http://localhost:8082/html/login.html");
//
//        }else{
//            //                已经登录了就放行
//            filterChain.doFilter (request,response);
//        }
//
//
//    }
    @Override
    public void destroy() {

    }
}
