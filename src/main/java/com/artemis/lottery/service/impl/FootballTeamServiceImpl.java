package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.FootballTeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 球队
 *
 * @author zhengenshen
 * @date 2018-05-08 11:08
 */
@Slf4j
@Service
public class FootballTeamServiceImpl extends AbstractBaseService<FootballTeamRepository, FootballTeam> implements FootballTeamService {

    /**
     * 查询没有开奖的
     *
     * @return 球队
     */
    @Override
    public FootballTeam findFootballTeam() {
        return r.findByStatus(0);
    }

    @Override
    public FootballTeam findById(Long id) {
        return r.findById(id).orElse(null);
    }

    @Override
    public FootballTeam findCurrentTeam() {
        String yyyyMMddHHmm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        long id = Long.parseLong(yyyyMMddHHmm) / 10 * 10;
        log.debug(id + "");
        return r.findById(id).orElseThrow(() -> new EntityNotFoundException("没有这一期"));
    }

    @Override
    public FootballTeam findNextTeam() {
        String yyyyMMddHHmm = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
        long id = (Long.parseLong(yyyyMMddHHmm) / 10 + 1) * 10;
        log.debug(id + "");
        return r.findById(id).orElseThrow(() -> new EntityNotFoundException("没有这一期"));
    }

}
