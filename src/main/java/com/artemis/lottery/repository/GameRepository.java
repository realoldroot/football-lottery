package com.artemis.lottery.repository;

import com.artemis.lottery.domain.Game;
import org.springframework.data.repository.CrudRepository;

/**
 * @author zhengenshen
 * @date 2018-05-16 18:22
 */

public interface GameRepository extends CrudRepository<Game, Integer> {
}
