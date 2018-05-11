package com.artemis.lottery.service.impl;

import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @author zhengenshen
 * @date 2018-05-08 14:16
 */

public abstract class AbstractBaseService<R extends CrudRepository, T> implements BaseService<T> {

    @Autowired
    protected R r;

    @Autowired
    private FootballTeamRepository repository;

    @Override
    public void save(T t) {
        r.save(t);
    }

    @Override
    public void saveAll(List<T> t) {
        r.saveAll(t);
    }

}
