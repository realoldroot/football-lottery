package com.artemis.lottery.repository;

import com.artemis.lottery.domain.BcUsers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:28
 */

public interface BcUsersRepository extends CrudRepository<BcUsers, Integer> {

    Optional<BcUsers> findByBcPhone(String bcPhone);
}
