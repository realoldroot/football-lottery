package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.stream.Stream;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:27
 */

@Data
@Entity
@Table(name = "bcusers")
public class BcUsers {

    @Id
    @Column(name = "bcUserID")
    private Integer id;
    @Column(name = "bcPhone")
    private String bcPhone;
    @Column(name = "gameLoft")
    private Stream gameLoft;
}
