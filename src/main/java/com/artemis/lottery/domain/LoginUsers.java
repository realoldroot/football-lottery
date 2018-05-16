package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhengenshen
 * @date 2018-05-16 17:42
 */

@Data
@Entity
@Table(name = "Loginusers")
public class LoginUsers {

    @Id
    private int loginId;
    @Column(name = "bcphone")
    private String bcPhone;
    @Column(name = "password")
    private String password;
}
