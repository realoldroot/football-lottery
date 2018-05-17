package com.artemis.lottery.service;

import com.artemis.lottery.domain.BcUsers;

/**
 * ${DESCRIPTION}
 *
 * @author zhengenshen
 * @date 2018-05-16 18:30
 */
public interface BcUsersService extends BaseService<BcUsers> {

    BcUsers findByUser(String username);
}
