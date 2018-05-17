package com.artemis.lottery;

import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.service.PubIntegralsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zhengenshen
 * @date 2018-05-17 9:42
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PubIntegralsTest {

    @Autowired
    private PubIntegralsService service;

    @Test
    public void query() {

        PubIntegrals byBcUser = service.findByBcUser("17600116321");
        System.out.println(byBcUser);
    }
}
