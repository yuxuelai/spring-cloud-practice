package com.lavender.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.jsonwebtoken.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;


@Component
public class JwtUtils {

    static String secretKey = "lavender-serendipity";

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    public  String generateToken(UserDetails userDetails) {
        //1 payload部分 claims

        JwtBuilder jwtBuilder = Jwts.builder ();


        jwtBuilder.setSubject (userDetails.getUsername ())

                .setExpiration (new Date (System.currentTimeMillis () + (30 * 60 * 1800)))
                .setIssuedAt (new Date (System.currentTimeMillis ()));

        // 2 生成签名
        jwtBuilder.signWith (SignatureAlgorithm.HS512, secretKey);
        String jwt = jwtBuilder.compact ();
        return jwt;


    }

    // 从token中得到username
    public  String getUsernameFromToken(String jwt) {
        String username = Jwts.parser ().setSigningKey (secretKey)
                .parseClaimsJws (jwt).getBody ().getSubject ();

        return username;
    }

    // 从token中获取payload 荷载
    private Claims getClaimsFromToken(String token) {

    Claims claims = null;
    try {

        claims = Jwts.parser ()
                .setSigningKey (secretKey)
                .parseClaimsJws (token).getBody ();
    }catch(Exception e) {
        e.printStackTrace ();


    }
    return claims;

}
    public boolean validateToken(String token,UserDetails userDetails){

        String username = getUsernameFromToken(token);
        return username.equals (userDetails.getUsername ());

//        try {
//            JwtBuilder jwtBuilder = Jwts.builder ();
//            JwtBuilder verify = jwtBuilder.signWith (SignatureAlgorithm.HS512, secretKey);
//            JWTVerifier verifier = JWT.require ((Algorithm) verify).build ();
//            String username = getUsernameFromToken (token);
//            DecodedJWT jwt = verifier.verify (token);
//
//            return true;
//
//        }catch(Exception e){
//            return false;
//
//
//        }

    }




    // 验证是否过期
    public boolean isExpires(String jwt){
        Date date=Jwts.parser ()
                .setSigningKey (secretKey)
                .parseClaimsJws (jwt)
                .getBody ().getExpiration ();


        return date.before (new Date());


    }

    // 从token中获取过期时间
    private Date getExpiredDateFromToken(String token){
        Claims claims = getClaimsFromToken (token);
        return claims.getExpiration ();

    }

    // 根据荷载生成JWT Token
    private String generateToken(Map<String,Object> claims){
        return Jwts.builder ()
                .setClaims (claims)
                .setExpiration (generateExpirationDate ())
                .signWith (SignatureAlgorithm.HS512,secretKey)
                .compact ();


    }

    // 生成 Token失效时间

    private Date generateExpirationDate(){

        return new Date(System.currentTimeMillis ()+604800*1000);


    }}


