package com.lavender.filter;

import com.lavender.utils.JwtUtils;
import com.lavender.utils.RedisUtils;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    String redisKey= "USER_INFO";

    @Value ("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Resource
    private JwtUtils jwtUtils;

    @Autowired
    RedisUtils redisUtils;

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        StringBuffer requestURL = request.getRequestURL ();
        String authHeader = request.getHeader ("Authorization");

//        if(authHeader==null){
////            logger.info ("logging....");
//            filterChain.doFilter(request,response);
//
//        }
//
        if(null!=authHeader){
            // 截取authToken
//            .substring (7,authHeader.length ())
            String authToken = authHeader;
//            System.out.println ("authToken===>"+authToken);
            // 获取username
            String username = jwtUtils.getUsernameFromToken (authToken);
//            System.out.println ("username===>"+username);

            if(null!=username&& null== SecurityContextHolder.getContext ().getAuthentication ()){
                // 登录
                UserDetails userDetails = userDetailsService.loadUserByUsername (username);
                if(redisUtils.get (redisKey)==null){
                    String refreshToken = jwtUtils.generateToken (userDetails);
                    // 存入redis 续期
                    redisUtils.set ("token",refreshToken,34, TimeUnit.MINUTES);

                }
                // 验证用户对象是否有效 如果有效 将他重新放到用户对象里面
                if(jwtUtils.validateToken (authToken,userDetails)){
                    UsernamePasswordAuthenticationToken authenticationToken =
                            new UsernamePasswordAuthenticationToken (userDetails, null, userDetails.getAuthorities ());

//                    System.out.println (authenticationToken);
                    authenticationToken.setDetails (new WebAuthenticationDetailsSource ().buildDetails (request));
                    SecurityContextHolder.getContext ().setAuthentication (authenticationToken);

                }

            }

        }
        filterChain.doFilter (request,response);
//        htzrcyjlsickbhbi
    }
}

//