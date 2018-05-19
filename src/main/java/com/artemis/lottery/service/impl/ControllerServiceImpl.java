package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.Controller;
import com.artemis.lottery.repository.ControllerRepository;
import com.artemis.lottery.service.ControllerService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

/**
 * @author zhengenshen
 * @date 2018-05-18 17:37
 */
@Service
public class ControllerServiceImpl extends AbstractBaseService<ControllerRepository, Controller> implements ControllerService {

    @Override
    public Controller find() {
        return r.findById(1).orElseThrow(EntityNotFoundException::new);
    }

}
