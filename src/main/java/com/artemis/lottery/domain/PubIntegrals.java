package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:23
 */

@Data
@Entity
@Table(name = "pubintegrals")
public class PubIntegrals {

    @Id
    @Column(name = "pubIntegralID")
    private Integer id;
    @Column(name = "bcUserID")
    private Integer bcUserId;
    @Column(name = "gameLoft")
    private String gameLoft;
    @Column(name = "gameCredits")
    private Integer gameCredits;
    @Column(name = "PresentExp")
    private Integer PresentExp;

}
