package com.stark.encrypt;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Stark on 2017/11/15.
 * 使用md5单向加密
 */
public class MD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        String psw = "123456";

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        //加密后的字符串
        String newstr = base64en.encode(md5.digest(psw.getBytes("utf-8")));
        System.out.println(newstr);

    }
}
