package com.artemis.lottery.repository;

import com.artemis.lottery.domain.PubIntegrals;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:26
 */

public interface PubIntegralsRepository extends CrudRepository<PubIntegrals, Integer> {

    Optional<PubIntegrals> findByBcUserId(Integer bcUserId);

    @Query(value = "select pubintegrals.* from pubintegrals where exists(select 1 from bcusers where bcusers.bcUserID=pubintegrals.bcUserID and bcPhone = ?1)", nativeQuery = true)
    Optional<PubIntegrals> findByBcUser(String bcUser);
}
