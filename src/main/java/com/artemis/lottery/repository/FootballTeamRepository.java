package com.artemis.lottery.repository;

import com.artemis.lottery.domain.FootballTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 球队
 *
 * @author zhengenshen
 * @date 2018-05-08 11:05
 */

public interface FootballTeamRepository extends MongoRepository<FootballTeam, Long> {

    FootballTeam findByStatus(int status);

    Page<FootballTeam> findByStatus(int status, Pageable Pageable);

}
