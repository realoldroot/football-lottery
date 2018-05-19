package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author zhengenshen
 * @date 2018-05-18 17:33
 */

@Entity
@Data
public class Controller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 宣称奖池金额
     */
    private float settingAmount;

    /**
     * 当前卖出金额
     */
    private float currentAmount;

    /**
     * 虚拟中奖用户
     */
    private Integer userCount;
}
