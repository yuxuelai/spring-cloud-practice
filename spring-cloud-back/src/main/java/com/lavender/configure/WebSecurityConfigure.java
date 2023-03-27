package com.lavender.configure;

import com.lavender.filter.JwtAuthorizationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import javax.servlet.Filter;

/**
 *写一个自定义配置类
 * 配置资源认证规则
 * 1。继承WebSecurityConfigureAdapter
 * 2.重写configure
 * 3.链式授权 放行
 *
 */

@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtAuthorizationTokenFilter jwtAuthorizationTokenFilter;


    // 重写configure 方法
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.cors ();
        httpSecurity.csrf ().disable ();
        httpSecurity.authorizeHttpRequests ().antMatchers (HttpMethod.OPTIONS, "/**").permitAll();
        httpSecurity.authorizeHttpRequests ().antMatchers (HttpMethod.POST, "/**").permitAll();
        httpSecurity.authorizeHttpRequests ().antMatchers (HttpMethod.GET, "/**").permitAll();
        httpSecurity.authorizeHttpRequests ().antMatchers (HttpMethod.PUT, "/**").permitAll();

        httpSecurity.authorizeHttpRequests ()
                    // 这里设置为 “/index” 不用验证直接放行
                    .antMatchers (
//                            "/back/**",
                            "/login",
//                            "/**",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/swagger-resources/**",
                            "v2/**"
                            ).permitAll ()
//                    .antMatchers ("/back/**").not().permitAll()
//                    .antMatchers ("/back/user/**").hasAnyAuthority ("delete")
                    .anyRequest ().authenticated ();
        httpSecurity.userDetailsService (userDetailsService).headers ().cacheControl ();
                    // 添加JWT 登录授权过滤器
        httpSecurity.addFilterBefore (jwtAuthorizationTokenFilter, UsernamePasswordAuthenticationFilter.class);


    }


    @Bean
    public PasswordEncoder passwordEncoder(){


        return new BCryptPasswordEncoder ();
    }

}
