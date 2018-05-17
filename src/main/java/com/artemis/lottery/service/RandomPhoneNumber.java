package com.artemis.lottery.service;

import java.util.*;

/**
 * 随机手机号
 *
 * @author zhengenshen
 * @date 2018-05-17 14:21
 */

public class RandomPhoneNumber {


    private static String[] telFirst = ("134,135,136,137,138,139,150,151,152,157,158,159," +
            "130,131,132,155,156,133,153,182,183,187,188,180,181,189").split(",");


    /**
     * 随机手机号
     *
     * @param number 随机几个
     * @return 随机的手机号
     */
    public static List<String> build(int number) {

        Set<String> set = new HashSet<>();
        for (int i = 0; i < Math.abs(number); i++) {
            int index = getNum(0, telFirst.length - 1);
            String first = telFirst[index];
            // String second = String.valueOf(getNum(1, 888) + 10000).substring(1);
            String third = String.valueOf(getNum(1, 9100) + 10000).substring(1);
            String tel = first + "***" + third;
            set.add(tel);
        }
        return new ArrayList<>(set);
    }

    private static int getNum(int start, int end) {
        return (int) (Math.random() * (end - start + 1) + start);
    }

    public static void main(String[] args) {

        RandomPhoneNumber.build(10).forEach(System.out::println);
    }
}
