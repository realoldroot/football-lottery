package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:27
 */

@Data
@Entity
@Table(name = "bcusers")
public class BcUsers {

    @Id
    @Column(name = "bcuserid")
    private Integer id;
    @Column(name = "bcphone")
    private String bcPhone;
    @Column(name = "gameloft")
    private String gameLoft;
}
