package com.lavender.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class JwtUtil {

    // 有效期 一个小时
    public static final Long JWT_TTL=3600000L;

    // 设置秘钥明文
    static String secretKey = "lavender-serendipity";

//    public static final  String JWT_KEY="lavender-serendipity";

    /**
     *
     * 生成加密后的秘钥
     */

    public static SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JwtUtil.secretKey);
        SecretKey key = new SecretKeySpec (encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    /**
     * 解析
     *
     * @param
     * @return
     * @throws Exception
     */
//    public static Claims parseJWT(String jwt) throws Exception {
//        SecretKey secretKey = generalKey();
//        return Jwts.parser()
//                .setSigningKey(secretKey)
//                .parseClaimsJws(jwt)
//                .getBody();
//    }

    public static Claims getClaimsFromToken(String token) {

        Claims claims = null;
        try {

            claims = Jwts.parser ()
                    .setSigningKey (secretKey)
                    .parseClaimsJws (token).getBody ();
        }catch(Exception e) {
            e.printStackTrace ();


        }
        return claims;




}}
