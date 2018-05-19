package com.artemis.lottery.service;

import com.artemis.lottery.domain.Controller;

/**
 * @author zhengenshen
 * @date 2018-05-18 17:37
 */

public interface ControllerService extends BaseService<Controller> {

    /**
     * 查询 预先设置好的奖金池大小。
     *
     */
    Controller find();
}
