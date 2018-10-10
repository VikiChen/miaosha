package com.viki.miaosha.util;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5Util {

    public static String md5(String src){
        return DigestUtils.md5Hex(src);
    }

    private static final String salt="1a2b3c4d";
    public static String inputPassToFormPass(String inputPass){
      String ste=""+salt.charAt(0)+salt.charAt(2)+inputPass+salt.charAt(5)+salt.charAt(4);
      return md5(ste);
    }

    public static String formPassToDBPass(String formPass,String salt){
        String ste=""+salt.charAt(0)+salt.charAt(2)+formPass+salt.charAt(5)+salt.charAt(4);
        return md5(ste);
    }

    public static String inputPassToDBPass(String inputForm,String salt){
        String formPass =inputPassToFormPass(inputForm);
        String dbPass=formPassToDBPass(formPass,salt);
        return dbPass;
    }

    public static void main(String[] args) {
       System.out.println(formPassToDBPass("d3b1294a61a07da9b49b6e22b2cbd7f9","qwertyuioe")); //
    }
}
