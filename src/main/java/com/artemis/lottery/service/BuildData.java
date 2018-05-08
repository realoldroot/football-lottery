package com.artemis.lottery.service;

import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.domain.TeamEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;

import java.util.*;

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


    private static List<String> buildPlayerNumber() {

        Set<String> set = new HashSet<>();
        Random random = new Random();

        while (set.size() < 11) {
            set.add(TEAM_A + RandomUtils.nextInt(1, 30));
        }

        while (set.size() < 22) {
            set.add(TEAM_B + RandomUtils.nextInt(1, 30));
        }

        return new ArrayList<>(set);
    }

    public static FootballTeam buildTeam() {

        FootballTeam fb = new FootballTeam();
        fb.setId(System.currentTimeMillis());
        fb.setPlayers(buildPlayerNumber());
        fb.setTeams(new String[]{TeamEnum.TEAM_A.getName(), TeamEnum.TEAM_B.getName()});

        return fb;
    }


}
