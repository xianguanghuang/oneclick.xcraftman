package org.xcraftman.ecrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by xianguanghuang on 2016/7/12. For Note and Demo purpose
 *
 * MD5 是作为非对称加密的最简单的实现方式
 *
 * 被加密的内容称为message
 * 加密后的内容称为 digest
 *
 *
 * 优点 ： 实现简单，效率高
 *
 * 缺点 :
 *   not collision resistant ： 不同的密码可以产生相同的md5码
 *   容易被暴力破解
 */
public class SimpleMD5Example {
    public static void main(String[] args) {
        String passwordToHash = "password";
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(passwordToHash.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
    }
}

