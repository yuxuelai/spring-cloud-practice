package com.lavender.filter;

import com.lavender.utils.JwtUtil;
import org.springframework.cloud.gateway.config.conditional.ConditionalOnEnabledGlobalFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 *
 * 鉴权过滤器
 * @author Administrator
 *
 */

@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

//    private static final String Authorization = "token";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//      1 .获取请求
        ServerHttpRequest request = exchange.getRequest ();

        //2 获取响应
        ServerHttpResponse response = exchange.getResponse ();

//        System.out.println ("lavender ufbknjknjkjh");
        // 3 如果时登录请求则放行
        if(request.getURI ().getPath ().contains ("/login")){
            return chain.filter (exchange);

        }
        // 获取请求头
        HttpHeaders headers = request.getHeaders ();
        // 5 请求头中获取令牌
        String token = headers.getFirst ("Authorization");

        // 6 判断请求头中是否有令牌
        if(StringUtils.isEmpty (token)){
            // 7  响应放入返回的状态码
            response.setStatusCode (HttpStatus.UNAUTHORIZED);
            // 8 返回
            return response.setComplete();

        }
        // 9 如果请求头中有令牌 则解析令牌
        try{
            JwtUtil.getClaimsFromToken (token);

        } catch (Exception e) {
            e.printStackTrace ();
            // 10 解析jwt令牌出错 说明令牌过期或者伪造等不合法的情况出现
            response.setStatusCode (HttpStatus.UNAUTHORIZED);

            return response.setComplete ();
        }

        // 12 放行
        return chain.filter (exchange);

    }


    @Override
    public int getOrder() {

        return 0;

    }

//    @Override
//    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//
//        System.out.println ("enter the global filter");
//
//        return chain.filter (exchange);
//    }
}
