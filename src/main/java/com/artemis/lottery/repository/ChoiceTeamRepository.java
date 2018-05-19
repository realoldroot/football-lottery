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

    /**
     * 根据期号，球队和球员查询 中奖人
     *
     * @param no            期号
     * @param teamName      中奖球队名称
     * @param playerNumbers 中奖球员
     * @return 中奖人
     */
    List<ChoiceTeam> findByNoAndTeamNameAndPlayerNumbers(Long no, String teamName, Set<String> playerNumbers);

    Page<ChoiceTeam> findByUsername(String username, Pageable pageable);
}
