package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.repository.PubIntegralsRepository;
import com.artemis.lottery.service.BcUsersService;
import com.artemis.lottery.service.PubIntegralsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:32
 */

@Service
public class PubIntegralsServiceImpl extends AbstractBaseService<PubIntegralsRepository, PubIntegrals> implements PubIntegralsService {

    @Autowired
    private BcUsersService bcUsersService;


    @Override
    public PubIntegrals findByBcUser(String username) {
        return r.findByBcUser(username).orElseThrow(EntityNotFoundException::new);
    }
}
