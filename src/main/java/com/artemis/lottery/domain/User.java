package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户
 *
 * @author zhengenshen
 * @date 2018-05-11 11:00
 */
@Data
@Entity
@Table(name = "T_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String passwordHash;
    private String passwordSalt;
    private String nickname;
    private Integer score;
    private Long createTime;

    public User() {
    }

    public User(String username, String passwordHash, String passwordSalt, String nickname) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.passwordSalt = passwordSalt;
        this.nickname = nickname;
        this.createTime = System.currentTimeMillis();
    }
}
