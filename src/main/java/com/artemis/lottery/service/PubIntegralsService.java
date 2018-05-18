package com.artemis.lottery.service;

import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.domain.RespUser;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:32
 */

public interface PubIntegralsService extends BaseService<PubIntegrals> {

    PubIntegrals findByBcUser(String username);

    RespUser userInfo(String username);
}
