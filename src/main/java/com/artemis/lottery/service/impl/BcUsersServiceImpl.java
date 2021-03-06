package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.BcUsers;
import com.artemis.lottery.repository.BcUsersRepository;
import com.artemis.lottery.service.BcUsersService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:30
 */

@Service
public class BcUsersServiceImpl extends AbstractBaseService<BcUsersRepository, BcUsers> implements BcUsersService {

    @Override
    public BcUsers findByUser(String username) {
        return r.findByBcPhone(username).orElseThrow(EntityNotFoundException::new);
    }
}
