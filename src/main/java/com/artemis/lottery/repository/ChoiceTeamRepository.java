package com.artemis.lottery.repository;

import com.artemis.lottery.domain.ChoiceTeam;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 选择团队
 *
 * @author zhengenshen
 * @date 2018-05-08 14:11
 */

public interface ChoiceTeamRepository extends MongoRepository<ChoiceTeam, Long> {

    ChoiceTeam findByUsername(String username);
}
