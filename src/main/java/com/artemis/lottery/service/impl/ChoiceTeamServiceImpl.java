package com.artemis.lottery.service.impl;

import com.artemis.lottery.domain.ChoiceTeam;
import com.artemis.lottery.domain.PubIntegrals;
import com.artemis.lottery.repository.ChoiceTeamRepository;
import com.artemis.lottery.service.ChoiceTeamService;
import com.artemis.lottery.service.PubIntegralsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 选择团队
 *
 * @author zhengenshen
 * @date 2018-05-08 14:15
 */
@Service
public class ChoiceTeamServiceImpl extends AbstractBaseService<ChoiceTeamRepository, ChoiceTeam> implements ChoiceTeamService {

    @Autowired
    private PubIntegralsService pubIntegralsService;

    /**
     * 重写保存方法
     *
     * @param choiceTeam
     */
    @Override
    public void save(ChoiceTeam choiceTeam) {

        PubIntegrals pi = pubIntegralsService.findByBcUser(choiceTeam.getUsername());


        super.save(choiceTeam);
    }
}
