package com.artemis.lottery;

import com.artemis.lottery.common.SHATools;

/**
 * @author zhengenshen
 * @date 2018-05-17 11:02
 */

public class SignTest {

    public static void main(String[] args) {
        String token = "c10a31c346c0f6e9327b1749502d20e9f8ab7a5c4ec12aedcc9287ee058c2297";
        String username = "17600116321";
        String timestamp = "1526526195294";

        String sign = SHATools.SHA256(username + token + timestamp);

        System.out.println(sign);

    }
}
