package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.service.ChoiceTeamService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * 选择团队
 *
 * @author zhengenshen
 * @date 2018-05-08 14:15
 */
@Service
public class ChoiceTeamServiceImpl extends AbstractBaseService<ChoiceTeamRepository, ChoiceTeam> implements ChoiceTeamService {

    @Override
    public Page<ChoiceTeam> page(int page, int pageSize) {
        return r.findAll(PageRequest.of(page, pageSize));
    }
}
