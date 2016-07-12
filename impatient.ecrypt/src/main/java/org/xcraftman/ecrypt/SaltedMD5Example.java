package org.xcraftman.ecrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * Created by xianguanghuang on 2016/7/12. For Note and Demo purpose
 * <p>
 * 特点 : 为原来的Message 生成digest 前添加 salt
 * 防止暴力破解
 *        要注意的是每次对Message 做加密之前一定要同时保存salt 否则加密后的结果是不正确的。
 * <p>
 * <p>
 * 产生salt 的时候推荐使用SecureRandom + SHA1PRNG,
 * 由于 Java 原来的Random 算法在运行到一定的时候下一个随机值是可以预测的， 这种情况叫做：
 * Predictability of Linear Congruential Generators
 */
public class SaltedMD5Example {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
        String passwordToHash = "password";
        byte[] salt = getSalt();

        String securePassword = getSecurePassword(passwordToHash, salt);
        System.out.println(securePassword); //Prints 83ee5baeea20b6c21635e4ea67847f66

        String regeneratedPassowrdToVerify = getSecurePassword(passwordToHash, salt);
        System.out.println(regeneratedPassowrdToVerify); //Prints 83ee5baeea20b6c21635e4ea67847f66
    }

    private static String getSecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(salt);
            //Get the hash's bytes
            byte[] bytes = md.digest(passwordToHash.getBytes());
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
        return generatedPassword;
    }

    //Add salt
    private static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
        //Always use a SecureRandom generator
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        //Create array for salt
        byte[] salt = new byte[16];
        //Get a random salt
        sr.nextBytes(salt);
        //return salt
        return salt;
    }
}
