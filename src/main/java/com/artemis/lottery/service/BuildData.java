package com.artemis.lottery.service;

import com.artemis.lottery.domain.FootballTeam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 初始化数据
 *
 * @author zhengenshen
 * @date 2018-05-08 10:32
 */

@Slf4j
public class BuildData {

    private static final String TEAM_A = "r_";

    private static final String TEAM_B = "b_";


    public static Set<String> buildPlayerNumber() {

        Set<String> set = new HashSet<>();
        Random random = new Random();

        while (set.size() < 11) {
            set.add(TEAM_A + RandomUtils.nextInt(1, 30));
        }

        while (set.size() < 22) {
            set.add(TEAM_B + RandomUtils.nextInt(1, 30));
        }

        return set;
    }

    public static FootballTeam buildTeam() {

        FootballTeam fb = new FootballTeam();
        fb.setId(System.currentTimeMillis());
        fb.setPlayerNumbers(buildPlayerNumber());

        return fb;
    }


}
