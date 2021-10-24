package com.itheima.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {
    private static BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
    public static String encodePassword(String password){
        return bCryptPasswordEncoder.encode(password);
    }

    public static void main(String[] args) {
        String password="123";
        String pwd = encodePassword(password);
        //$2a$10$XFi2zuMAFFJBhtSm8ynkFOTGABN0rUkCTGU8ocv.6Q6Dn9Rw936TO
        System.out.println(pwd);
        System.out.println(pwd.length());
    }
}
