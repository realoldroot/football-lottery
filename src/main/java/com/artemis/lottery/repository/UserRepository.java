package com.artemis.lottery.repository;

import com.artemis.lottery.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * 用户
 *
 * @author zhengenshen
 * @date 2018-05-11 11:06
 */

public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> findByUsernameAndPassword(String username, String password);
}
