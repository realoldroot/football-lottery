package com.artemis.lottery.common;

import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Decoder;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA加解密
 *
 * @author zhengenshen
 * @date 2018-05-14 9:43
 */

public class RSATools {

    public static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9tTYOtvyDwq/5olB20/+5kEsx8wMvqJpM+C9SF783nZi3k1Z/VJB9yatdYqgLVvHG8BWuIoqWR+/7qwz5lsSrGR6WaHSlPDm361ffR3Ng9fqeM+wNSOjzt5UV/qwWHzT2R+JcP9r015tlj7TcxDB+NnDdS1Numtyb3p7qV44GAwIDAQAB";

    private static final String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL21Ng62/IPCr/miUHbT/7mQSzHzAy+omkz4L1IXvzedmLeTVn9UkH3Jq11iqAtW8cbwFa4iipZH7/urDPmWxKsZHpZodKU8ObfrV99Hc2D1+p4z7A1I6PO3lRX+rBYfNPZH4lw/2vTXm2WPtNzEMH42cN1LU26a3JvenupXjgYDAgMBAAECgYAYKK2brh32QaLbbZpFp7XXUdLhp4ruVNBhoPGVyMRnNsCuECthx8km27nhCKFR4F6l8QkxxeBURxD+wcfDOToOP6R/VJrGXjl53y/tvyp8/P5HSFTYbZaGZEhJbWBTQ4IUv8OUmyvy2InFuKtVIECXGYlmrMqfo9AEdlho9oSTMQJBAPGlQPMCovoR/loYnXtxIAN5N/rK2ZTwwiFesB1P0DoxFvts9Eaj4quDZDOruUPsjVG0m1yPjwcAqtY8E7Fj8nUCQQDI+iHVFOp8j+OCGb7Zb8C/nDMKkEcTReuwJC/YGsvhidh+cjRrTZWvS6a8n9+5wYmknNdtxkCtFUrVUZ8KW5eXAkEAy26I+Ex+8jIVxLm2UsbQ4bu6QftmI7pwZHF3qKWZsnPDWlXGbHXqDORnhgyKwsEx09hNdyYwjf+k0wjut0diRQJBAIqndvvo6DVzl8REOtUMMnB/NP+OtlpnnMjyzkdLhWlejov+AogdCK3dhXUQ32rqSSnWQxIq+qfuzqgJHa4prpUCQBcUNEvJq3u8mld3t2/2yPkzdU7szz0AdXQJA+dz8hUFcEJp6zLBVc3a6+i5fRn6NrJP2FGIxMDnV8dppszVQTg=";

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 加密方法
     *
     * @param source 源数据
     */
    public static String encrypt(String source, String key) throws Exception {

        PublicKey publicKey = getPublicKey(key);

        /* 得到Cipher对象来实现对源数据的RSA加密 */
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] b = source.getBytes();
        /* 执行加密操作 */
        byte[] b1 = null;
        try {
            b1 = cipher.doFinal(b);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Base64.encodeBase64String(b1);
    }


    private static PublicKey getPublicKey(String key) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePublic(keySpec);
    }


    /**
     * 解密算法
     *
     * @param cryptograph 密文
     * @param key         秘钥
     * @return 明文
     * @throws Exception 解密异常
     */
    private static String decrypt(String cryptograph, String key) throws Exception {

        PrivateKey privateKey = getPrivateKey(key);

        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] b1 = Base64.decodeBase64(cryptograph);

        byte[] b = cipher.doFinal(b1);
        return new String(b);
    }

    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = new BASE64Decoder().decodeBuffer(key);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 解密密码
     *
     * @param password 密文
     * @return 明文
     * @throws Exception e
     */
    public static String decode(String password) throws Exception {
        return decrypt(password, PRIVATE_KEY);
    }

    /**
     * 加密密码
     *
     * @param password 明文
     * @return 密文
     * @throws Exception e
     */
    public static String encode(String password) throws Exception {
        return decrypt(password, PUBLIC_KEY);
    }


    public static String byteToString(byte[] bytes) {
        StringBuilder strHexString = new StringBuilder();
        for (byte bt : bytes) {
            String hex = Integer.toHexString(0xff & bt);
            if (hex.length() == 1) {
                strHexString.append('0');
            }
            strHexString.append(hex);
        }
        return strHexString.toString();
    }

}
