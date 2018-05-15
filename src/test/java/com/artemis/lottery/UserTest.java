package com.artemis.lottery;

import com.artemis.lottery.common.RSATools;
import com.artemis.lottery.domain.User;
import com.artemis.lottery.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.MessageDigest;

/**
 * 用户测试
 *
 * @author zhengenshen
 * @date 2018-05-11 11:09
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void save() throws Exception {
        User user = new User();
        user.setUsername("18310860399");
        user.setPasswordSalt("123");
        user.setPasswordHash("321");
        user.setCreateTime(System.currentTimeMillis());

        String salt = "wanday.artemis3d.com#WBV@#%s#wanday.lock";
        String saltPassword = String.format("", "123123");
        byte[] byteBuffer = MessageDigest.getInstance("SHA-512").digest(saltPassword.getBytes());
        String a = RSATools.encrypt(RSATools.byteToString(byteBuffer).substring(0, 115), RSATools.PUBLIC_KEY);
        System.out.println(a);
        userService.register("18310860399", a, 123123, "tom");
    }



    @Test
    public void find() {

    }
}
