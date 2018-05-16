package com.artemis.lottery.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:06
 */

public class MD5Tools {

    public static String crypt(String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("String to encript cannot be null or zero length");
        }
        StringBuilder hexString = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte[] hash = md.digest();
            for (byte aHash : hash) {
                if ((0xff & aHash) < 0x10) {
                    hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
                } else {
                    hexString.append(Integer.toHexString(0xFF & aHash));
                }
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hexString.toString().toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(MD5Tools.crypt("123456"));

    }
}
