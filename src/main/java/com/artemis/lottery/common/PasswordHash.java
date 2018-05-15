package com.artemis.lottery.common;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

/**
 * @author zhengenshen
 * @date 2018-05-14 9:59
 */

public class PasswordHash {

    private static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    // 下面的常量可以改变而不破坏现有的哈希值

    /**
     * 盐的长度
     */
    private static final int SALT_BYTE_SIZE = 24;
    /**
     * 生成密文的长度
     */
    private static final int HASH_BYTE_SIZE = 24;
    /**
     * 迭代次数
     */
    private static final int PBKDF2_ITERATIONS = 1000;
    private static final int ITERATION_INDEX = 0;
    private static final int SALT_INDEX = 1;
    private static final int PBKDF2_INDEX = 2;


    /**
     * Returns a salted PBKDF2 hash of the password.
     * *
     *
     * @param password the password to hash
     * @return a salted PBKDF2 hash of the password
     */
    private static String createHash(char[] password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTE_SIZE];
        random.nextBytes(salt);

        //hash password

        byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

        return toHex(hash) + ":" + toHex(salt);
    }

    /**
     * 计算密码的PBKDF2 hash
     * *
     *
     * @param password   密码 hash
     * @param salt       盐
     * @param iterations 迭代计数（慢度因子）
     * @param bytes      以字节计算的哈希长度
     * @return PBDKF2 密码 hash
     */
    private static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) throws NoSuchAlgorithmException, InvalidKeySpecException {

        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 8);
        SecretKeyFactory factory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
        return factory.generateSecret(spec).getEncoded();
    }

    /**
     * 将字节数组转换为十六进制字符串.
     * *
     *
     * @param array 要转换的字节数组
     * @return 一个长度为* 2的字符串，编码字节数组
     */
    private static String toHex(byte[] array) {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if (paddingLength > 0) {
            return String.format("%0" + paddingLength + "d", 0) + hex;
        } else {
            return hex;
        }


    }

    /**
     * 使用 hash 验证密码。
     * *
     *
     * @param password 检查密码correctHash有效密码的散列
     * @return 如果密码正确，则为true，否则为false
     */
    public static boolean validatePassword(String password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException {

        return validatePassword(password.toCharArray(), correctHash);
    }


    /**
     * 使用散列验证密码
     * *
     *
     * @param password    要检查的密码
     * @param correctHash 有效密码的散列
     * @return 如果密码正确，则为true，否则为false
     */
    private static boolean validatePassword(char[] password, String correctHash) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] params = correctHash.split(":");

        int iteration = Integer.parseInt(params[ITERATION_INDEX]);

        byte[] salt = fromHex(params[SALT_INDEX]);

        byte[] hash = fromHex(params[PBKDF2_INDEX]);

        byte[] testHash = pbkdf2(password, salt, iteration, hash.length);

        return slowEquals(hash, testHash);
    }

    /**
     * 在长度恒定的时间内比较两个字节数组。
     *
     * @param a 第一个字节数组
     * @param b 第二个字节数组
     * @return 如果两个字节数组相同，则为true;否则为false
     */
    private static boolean slowEquals(byte[] a, byte[] b) {
        int diff = a.length ^ b.length;
        for (int i = 0; i < a.length && i < b.length; i++) {
            diff |= a[i] ^ b[i];
        }
        return diff == 0;
    }

    /**
     * 将一串十六进制字符转换为一个字节数组。
     * *
     */
    private static byte[] fromHex(String hex) {
        byte[] binary = new byte[hex.length() / 2];
        for (int i = 0; i < binary.length; i++) {
            binary[i] = (byte) Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return binary;

    }


    public static boolean validatePassword(String password, String hash, String salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        byte[] byteSalt = fromHex(salt);
        byte[] byteHash = fromHex(hash);
        byte[] testHash = pbkdf2(password.toCharArray(), byteSalt, PBKDF2_ITERATIONS, byteHash.length);
        return slowEquals(byteHash, testHash);
    }

    public static String[] createHash(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        String hash = createHash(password.toCharArray());
        return hash.split(":");
    }

}
