package com.artemis.lottery.service;

import java.util.List;

/**
 * @author zhengenshen
 * @date 2018-05-08 14:13
 */

public interface BaseService<T> {

    void save(T t);

    void saveAll(List<T> t);
}
