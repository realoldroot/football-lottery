package com.artemis.lottery;

import com.artemis.lottery.service.BonusPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhengenshen
 * @date 2018-05-18 15:55
 */


@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {


    @Autowired
    private BonusPool bonusPool;


    @Test
    public void add() {
        bonusPool.add(100);
        get();
    }

    @Test
    public void get() {
        System.out.println(bonusPool.get());
    }

}
