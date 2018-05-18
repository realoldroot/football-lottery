package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.*;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:23
 */

@Data
@Entity
@Table(name = "pubintegrals")
public class PubIntegrals {

    @Id
    @Column(name = "pubintegralid")
    private Integer id;
    @Column(name = "bcuserid")
    private Integer bcUserId;
    @Column(name = "gameloft")
    private String gameLoft;
    @Column(name = "gamecredits")
    private Integer gameCredits;
    @Column(name = "presentexp")
    private Integer PresentExp;

}
