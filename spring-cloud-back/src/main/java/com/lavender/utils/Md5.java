package com.lavender.utils;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class Md5 {


    public  static String encoderByMd5(String str){

        MessageDigest md5 =null;

        try{
            // 使用 MessageDigest 得到我们 MD5 实例
            md5 = MessageDigest.getInstance ("MD5");
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode((md5.digest (str.getBytes(StandardCharsets.UTF_8))));

        }catch(Exception e) {

            e.printStackTrace ();

        }

        return "C056Dl/oStNftflbnO6seQ==";

    }

}
