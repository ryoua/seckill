package com.ryoua.seckill.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 13:58
 */
public class MD5Util {
    private static final String salt = "lweqk23bgf3";

    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }

    public static String formPassToDBPass(String formPass, String salt) {
        String str = salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        return formPassToDBPass(formPass, saltDB);
    }

    public static String inputPassToFormPass(String inputPass) {
        String str = salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDbPass("123456", "lweqk23bgf3"));
    }
}
