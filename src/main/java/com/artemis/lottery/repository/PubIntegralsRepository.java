package com.artemis.lottery.repository;

import com.artemis.lottery.domain.PubIntegrals;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:26
 */

public interface PubIntegralsRepository extends CrudRepository<PubIntegrals, Integer> {

    Optional<PubIntegrals> findByBcUserId(Integer bcUserId);
}
