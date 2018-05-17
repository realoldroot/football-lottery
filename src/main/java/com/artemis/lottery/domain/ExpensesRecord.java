package com.artemis.lottery.domain;

import lombok.Data;

import javax.persistence.Id;

/**
 * 消费记录表
 *
 * @author zhengenshen
 * @date 2018-05-17 17:31
 */

@Data
public class ExpensesRecord {

    @Id
    private Long id;

    //期号
    private Long no;

    // 支出
    private Integer expenditure;

    //剩余
    private Integer surplus;

}
