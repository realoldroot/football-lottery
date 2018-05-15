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

    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCLQ+YZMYATbNDl5jKJ0s4vd+99/u0DYC8" +
            "zq+EzXHFkSbKhGF8EoqRly6dsbbfcetTN0711x6o5pYnpoYQNWi98KdxvfX8R/NcmyuBkUeNBHNCM85F5lvFH7XSivyliFAhV9jYQcMo" +
            "Jn+K3bm1otOVTujTprnIH8ZncI9nyNyxIFQIDAQAB";

    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAItD5hkxgBNs0OXmMonSzi" +
            "93733+7QNgLzOr4TNccWRJsqEYXwSipGXLp2xtt9x61M3TvXXHqjmliemhhA1aL3wp3G99fxH81ybK4GRR40Ec0IzzkXmW8UftdKK/KW" +
            "IUCFX2NhBwygmf4rdubWi05VO6NOmucgfxmdwj2fI3LEgVAgMBAAECgYAeke8GhLkqim5oqNlbK60a+mbV74mzff+ehhJPJfG2I/9UIX" +
            "dqGxGfTs8XnBYKDeR8sggavJN/R/k4Eb5c9/kIIVllq1WMBflTSWA9l1b1+CpFqESXmxClPBpffwst6jtetCT+Tgm+NNXc28AtgZoRqz" +
            "YK0cjH6YD9YAcSAJARmQJBAOzDDUqyKhws3SWNGr0ikY+eNZae+Gu8QvwlhbxBAkI21bV6oNJaAQDwj3Eq/bvWfquhSHK68iLKHFfDE9" +
            "VXEMsCQQCWlMrI7wM4KEo9BN13M/l85F3Ll7dg8Zsw4UoLFNhNgcMrxmPI3Dkw734NnMtj+nO7DT2ns+xN/qQyXhWEkU6fAkBoezcNZI" +
            "xSvLWm0ZsgJfYoc7/gRS0Jlh3VWdWnmuSdRcePe5sm2NMikAYtAbTRRLnHkbf/8WHTWm+hhxPkjLR7AkAhc5aFAsvlStYLLiyA0bQH8b" +
            "eAhgi/6Yx/VF0djxjqRvxzmhQKyBmQbHPFVGK+qCHTmQJsYgUJ4prZcZs4IwujAkB3M0308xiN5Ylvy5iYA4HrOkul6eR48qUExqFWCa" +
            "iP5Ya98kas9ogT6e0sq3HGo5BLeY0ITn9C792jvVIhya0d";

    /**
     * 指定加密算法为RSA
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 加密方法
     *
     * @param source 源数据
     */
    private static String encrypt(String source, String key) throws Exception {

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
}
