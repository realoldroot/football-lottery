package com.artemis.lottery.domain;

import java.util.Arrays;

/**
 * @author zhengenshen
 * @date 2018-05-08 16:33
 */

public enum TeamEnum {


    TEAM_A("red", 0),


    TEAM_B("blue", 1);

    private String name;
    private int value;

    TeamEnum(String name, int value) {
        this.name = name;
        this.value = value;
    }


    public static int getValue(String name) {
        return Arrays.stream(TeamEnum.values())
                .filter(v -> v.name.equals(name))
                .findFirst().get().value;
    }

    public static String getName(int value) {
        return Arrays.stream(TeamEnum.values())
                .filter(v -> v.value == value)
                .findFirst().get().name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
