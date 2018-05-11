package com.artemis.lottery;

import com.artemis.lottery.domain.User;
import com.artemis.lottery.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    private UserRepository repository;

    @Test
    public void save() {
        User user = new User();
        user.setUsername("18310860399");
        user.setPasswordHash("123");
        user.setPasswordSalt("321");
        user.setNickname("甄");
        user.setCreateTime(System.currentTimeMillis());
        repository.save(user);
    }
}
