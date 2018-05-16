package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:19
 */

@Data
@Entity
@Table(name = "games")
public class Game {

    @Id
    @Column(name = "gameID")
    private Integer id;
    @Column(name = "gameLoft")
    private String loft;
    @Column(name = "gameName")
    private String name;
    @Column(name = "gameImg")
    private String Img;
    @Column(name = "isShare")
    private Integer isShare;
    @Column(name = "gameUrl")
    private String url;
    @Column(name = "gameType")
    private String type;

}
