package com.artemis.lottery.repository;

import com.artemis.lottery.domain.LoginUsers;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author zhengenshen
 * @date 2018-05-16 17:46
 */

public interface LoginUsersRepository extends CrudRepository<LoginUsers, Integer> {

    Optional<LoginUsers> findByBcPhoneAndPassword(String bcPhone, String password);
}
