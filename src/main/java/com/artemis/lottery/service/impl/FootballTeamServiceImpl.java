package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.FootballTeam;
import com.artemis.lottery.repository.FootballTeamRepository;
import com.artemis.lottery.service.BuildData;
import com.artemis.lottery.service.FootballTeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

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
     * 初始化球队 保存随机生成的球队信息，然后为前端提供球队信息
     *
     * @return 球队信息
     */
    @Override
    public FootballTeam initFootballTeam() {

        FootballTeam footballTeam = BuildData.buildTeam();

        // log.debug("随机球队信息{}", footballTeam);
        save(footballTeam);
        return footballTeam;
    }

    /**
     * 很简单，分页查询1条，取一个
     *
     * @return 球队
     */
    @Nullable
    @Override
    public FootballTeam findFootballTeam() {
        Page<FootballTeam> page = page(0, 1);
        return page.getContent().stream().findFirst().orElse(null);
    }

    @Override
    public Page<FootballTeam> page(int page, int pageSize) {
        return r.findAll(PageRequest.of(page, pageSize));
    }
}
