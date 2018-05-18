package com.artemis.lottery.repository;

import com.artemis.lottery.domain.ChoiceTeam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 选择团队
 *
 * @author zhengenshen
 * @date 2018-05-08 14:11
 */

public interface ChoiceTeamRepository extends MongoRepository<ChoiceTeam, Long> {

    List<ChoiceTeam> findByUsername(String username);

    Optional<ChoiceTeam> findByUsernameAndNo(String username, Long no);

    List<ChoiceTeam> findByNoAndTeamNameAndPlayerNumbers(Long no, String teamName, Set<String> playerNumbers);

    Page<ChoiceTeam> findByUsername(String username, Pageable pageable);
}
